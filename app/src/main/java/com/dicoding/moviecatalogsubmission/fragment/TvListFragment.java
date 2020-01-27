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
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.moviecatalogsubmission.BuildConfig;
import com.dicoding.moviecatalogsubmission.R;
import com.dicoding.moviecatalogsubmission.SettingActivity;
import com.dicoding.moviecatalogsubmission.adapter.RecycleViewTvAdapter;
import com.dicoding.moviecatalogsubmission.model.ViewModelMovie;
import com.dicoding.moviecatalogsubmission.model.Entity.TVShowsItem;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TvListFragment extends Fragment implements SearchView.OnQueryTextListener {

    private RecyclerView rvTv;
    private Context context;
    private RecyclerView.Adapter tvAdapter;
    private List<TVShowsItem> tvShowArrayList = new ArrayList<>();
    private Toolbar toolbar;
    private androidx.appcompat.widget.SearchView searchView = null;
    private ShimmerFrameLayout mShimmerViewContainer;
    private String api_key = BuildConfig.TMDB_API_KEY;
    private ViewModelMovie viewModelMovie;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tvlist_fragment, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvTv = view.findViewById(R.id.rvTv);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
        toolbar = view.findViewById(R.id.tvToolbar);
        context = getActivity();

        viewModelMovie = ViewModelProviders.of(this).get(ViewModelMovie.class);

        setToolbarTitle(getResources().getString(R.string.toolbar_tittle));

        getResultTVShowViewModel();
        setupRecycleView();
    }

    private void setToolbarTitle(String title) {
        toolbar.setTitle(title);
        toolbar.setTitleTextColor((ContextCompat.getColor(Objects.requireNonNull(getActivity()), R.color.black2)));
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        Log.e("SEARCH", "TESSS");
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
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                tvShowArrayList.clear();
                getResultTVShowViewModel();
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.setting) {
            Intent intent = new Intent(getActivity(), SettingActivity.class);
            startActivity(intent);
            Objects.requireNonNull(getActivity()).finish();
        }

        return false;
    }

    private void setupRecycleView() {
        if (tvAdapter == null) {
            Log.e("Masuk", "TV Recycle View");
            tvAdapter = new RecycleViewTvAdapter(context, tvShowArrayList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            rvTv.setLayoutManager(layoutManager);
            rvTv.setItemAnimator(new DefaultItemAnimator());
            rvTv.setHasFixedSize(true);
            rvTv.setAdapter(tvAdapter);
        } else {
            tvAdapter.notifyDataSetChanged();
        }
    }

    private void getResultTVShowViewModel() {
        viewModelMovie.init();
        viewModelMovie.getTVShowRepository().observe(this, tvShowResponse -> {
            if (tvShowResponse != null) {
                Log.e("Masuk", "Number of TV with  = " + tvShowResponse.getTotalResults());
                List<TVShowsItem> tvShowsItems = tvShowResponse.getResults();
                tvShowArrayList.addAll(tvShowsItems);
                tvAdapter.notifyDataSetChanged();
                mShimmerViewContainer.stopShimmer();
                mShimmerViewContainer.setVisibility(View.GONE);
            } else {
                Toast.makeText(getActivity(), "Check Internet Connection & Reload App", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getTvSearchResult(String key, String query){
        viewModelMovie.getTvSearch(key, query).observe(this, tvShowResponse -> {
            if (tvShowResponse != null){
                List<TVShowsItem> tvShowsItems = tvShowResponse.getResults();
                tvShowArrayList.clear();
                tvShowArrayList.addAll(tvShowsItems);
                tvAdapter.notifyDataSetChanged();
            }
        });

    }



    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        getTvSearchResult(api_key, newText);
        return true;
    }
}
