package com.dicoding.moviecatalogsubmission.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.moviecatalogsubmission.BuildConfig;
import com.dicoding.moviecatalogsubmission.R;
import com.dicoding.moviecatalogsubmission.SettingActivity;
import com.dicoding.moviecatalogsubmission.adapter.RecycleMovieAdapter;
import com.dicoding.moviecatalogsubmission.model.Entity.MoviesItem;
import com.dicoding.moviecatalogsubmission.model.ViewModelMovie;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MovieListFragment extends Fragment implements SearchView.OnQueryTextListener {
    private RecyclerView rvMovie;
    private Context context;
    private RecyclerView.Adapter moviesAdapter;
    private List<MoviesItem> movieArrayList = new ArrayList<>();
    private ShimmerFrameLayout mShimmerViewContainer;
    private Toolbar toolbar;
    private androidx.appcompat.widget.SearchView searchView = null;
    private String api_key = BuildConfig.TMDB_API_KEY;
    private ViewModelMovie viewModelMovie;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movielist_fragment, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvMovie = view.findViewById(R.id.rvMovies);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
        toolbar = view.findViewById(R.id.toolbarr);
        context = getActivity();

        viewModelMovie = ViewModelProviders.of(this).get(ViewModelMovie.class);

        setToolbarTitle(getResources().getString(R.string.toolbar_tittle));


        getResultMoviesViewModel();
        setupRecycleView();
    }

    private void setToolbarTitle(String title) {
        toolbar.setTitle(title);
        toolbar.setTitleTextColor((ContextCompat.getColor(Objects.requireNonNull(getActivity()), R.color.black2)));
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        MenuItem mSearch = menu.findItem(R.id.searchView);
        SearchManager searchManager = (SearchManager) Objects.requireNonNull(getActivity()).getSystemService(Context.SEARCH_SERVICE);

        if (mSearch != null){
            searchView = (androidx.appcompat.widget.SearchView) mSearch.getActionView();
        }
        if (searchView != null){
            assert searchManager != null;
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.search));
            searchView.setIconified(false);
            searchView.setSubmitButtonEnabled(true);
            searchView.setOnQueryTextListener(this);
        }


        assert mSearch != null;
        mSearch.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Log.i("MenuItemAction", "Expand");
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Log.i("MenuItemAction", "Collapse");
                movieArrayList.clear();
                getResultMoviesViewModel();
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.e("Menu", "Toolbar");
        int id = item.getItemId();

        if (id == R.id.setting) {
            Intent intent = new Intent(getActivity(), SettingActivity.class);
            startActivity(intent);
            Objects.requireNonNull(getActivity()).finish();
        } /*if(id == R.id.searchView){
            *//*TransitionManager.beginDelayedTransition(Objects.requireNonNull(getActivity()).findViewById(R.id.toolbarr));
            MenuItemCompat.expandActionView(item);*//*
        }*/

        return super.onOptionsItemSelected(item);
    }


    private void setupRecycleView() {
        if (moviesAdapter == null) {
            Log.e("Masuk", "Movies Recycle View");
            moviesAdapter = new RecycleMovieAdapter(context, movieArrayList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            rvMovie.setLayoutManager(layoutManager);
            rvMovie.setItemAnimator(new DefaultItemAnimator());
            rvMovie.setHasFixedSize(true);
            rvMovie.setAdapter(moviesAdapter);
        } else {
            moviesAdapter.notifyDataSetChanged();
        }
    }

    private void getResultSearch(String key, String query){
        viewModelMovie.getSearch(key, query).observe(this, movieResponse -> {
            if (movieResponse != null){
                Log.e("Masuk Search", "Number of movie with  = " + movieResponse.getTotalResults());
                List<MoviesItem> moviesItems = movieResponse.getResults();
                movieArrayList.clear();
                movieArrayList.addAll(moviesItems);
                moviesAdapter.notifyDataSetChanged();
            }
        });
    }

    private void getResultMoviesViewModel() {
        viewModelMovie.init();
        viewModelMovie.getMoviesRepository().observe(this, movieResponse -> {
            if (movieResponse != null) {
                Log.e("Masuk", "Number of movie with  = " + movieResponse.getTotalResults());
                List<MoviesItem> moviesItemList = movieResponse.getResults();
                movieArrayList.addAll(moviesItemList);
                moviesAdapter.notifyDataSetChanged();
                mShimmerViewContainer.stopShimmer();
                mShimmerViewContainer.setVisibility(View.GONE);
            } else {
                Toast.makeText(getActivity(), "Check Internet Connection & Reload App", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        getResultSearch(api_key, newText);
        return true;
    }
}
