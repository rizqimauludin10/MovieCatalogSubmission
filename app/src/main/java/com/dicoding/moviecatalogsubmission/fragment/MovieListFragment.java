package com.dicoding.moviecatalogsubmission.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.moviecatalogsubmission.BuildConfig;
import com.dicoding.moviecatalogsubmission.MainActivity;
import com.dicoding.moviecatalogsubmission.R;
import com.dicoding.moviecatalogsubmission.SettingActivity;
import com.dicoding.moviecatalogsubmission.adapter.RecycleMovieAdapter;
import com.dicoding.moviecatalogsubmission.model.Entity.MoviesItem;
import com.dicoding.moviecatalogsubmission.model.ViewModelMovie;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MovieListFragment extends Fragment implements SearchView.OnQueryTextListener {
    private RecyclerView rvMovie;
    private Context context;
    private RecyclerView.Adapter moviesAdapter;
    private List<MoviesItem> movieArrayList = new ArrayList<>();
    private ShimmerFrameLayout mShimmerViewContainer;
    private Toolbar toolbarMv;
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
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbarMv = view.findViewById(R.id.toolbarr);
        rvMovie = view.findViewById(R.id.rvMovies);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
        context = getActivity();

        viewModelMovie = ViewModelProviders.of(this).get(ViewModelMovie.class);

        setToolbarTitle(getResources().getString(R.string.toolbar_tittle));

        getResultMoviesViewModel();
        setupRecycleView();
    }

    private void setToolbarTitle(String title) {
        toolbarMv.setTitle(title);
        toolbarMv.setTitleTextColor((ContextCompat.getColor(Objects.requireNonNull(getActivity()), R.color.black2)));
        ((MainActivity) getActivity()).setSupportActionBar(toolbarMv);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        Log.e("SEARCH Movies", "TEST");
        inflater.inflate(R.menu.main, menu);
        MenuItem mSearch = menu.findItem(R.id.searchView);
        SearchManager searchManager = (SearchManager) Objects.requireNonNull(getActivity()).getSystemService(Context.SEARCH_SERVICE);

        if (mSearch != null) {
            searchView = (androidx.appcompat.widget.SearchView) mSearch.getActionView();
        }
        if (searchView != null) {
            assert searchManager != null;
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.search));
            searchView.setIconified(true);
            searchView.setOnQueryTextFocusChangeListener((v, hasFocus) -> {
                if (!hasFocus) {
                    movieArrayList.clear();
                    getResultMoviesViewModel();
                }
            });
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
        }

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

    private void getResultSearch(String key, String query) {
        viewModelMovie.getSearch(key, query).observe(this, movieResponse -> {
            if (movieResponse != null) {
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
        getResultSearch(api_key, query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        getResultSearch(api_key, newText);
        return true;
    }
}
