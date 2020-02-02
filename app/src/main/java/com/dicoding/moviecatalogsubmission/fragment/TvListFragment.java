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
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.moviecatalogsubmission.BuildConfig;
import com.dicoding.moviecatalogsubmission.MainActivity;
import com.dicoding.moviecatalogsubmission.R;
import com.dicoding.moviecatalogsubmission.SettingActivity;
import com.dicoding.moviecatalogsubmission.adapter.RecycleViewTvAdapter;
import com.dicoding.moviecatalogsubmission.model.ViewModelMovie;
import com.dicoding.moviecatalogsubmission.model.Entity.TVShowsItem;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TvListFragment extends Fragment {

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
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.tvlist_fragment, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar = view.findViewById(R.id.tvToolbar);
        rvTv = view.findViewById(R.id.rvTv);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
        context = getActivity();


        viewModelMovie = ViewModelProviders.of(this).get(ViewModelMovie.class);

        setToolbarTitle(getResources().getString(R.string.toolbar_tittle));
        getResultTVShowViewModel();
        setupRecycleView();
    }

    private void setToolbarTitle(String title) {
        toolbar.setTitle(title);
        toolbar.setTitleTextColor((ContextCompat.getColor(Objects.requireNonNull(getActivity()), R.color.black2)));
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        searchMenuHandle();
    }

    private void searchMenuHandle(){
        toolbar.inflateMenu(R.menu.main);
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.searchView) {

                SearchView searchView = (androidx.appcompat.widget.SearchView) item.getActionView();
                //searchView.onActionViewCollapsed();
                searchView.setQueryHint(getResources().getString(R.string.search));
                searchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
                searchView.setIconified(true);
                item.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
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
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        Log.i("onQueryTextSubmitTv", query);
                        getTvSearchResult(api_key, query);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        Log.i("onQueryTextChangeTv", newText);
                        getTvSearchResult(api_key, newText);
                        return true;
                    }
                });
                searchView.setOnQueryTextFocusChangeListener(((v, hasFocus) -> {
                    if (!hasFocus){
                        tvShowArrayList.clear();
                        getResultTVShowViewModel();
                    }
                }));
            } else if(item.getItemId() == R.id.setting) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                Objects.requireNonNull(getActivity()).finish();
            }

            return false;
        });
    }

 /*   @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        Log.e("SEARCH TV", "TEST");
        //inflater.inflate(R.menu.main, menu);

        *//*if (toolbar.getMenu().size() > 0){
            SearchManager searchManager = (SearchManager) Objects.requireNonNull(getActivity()).getSystemService(Context.SEARCH_SERVICE);
            MenuItem menuItem = toolbar.getMenu().getItem(toolbar.getMenu().size()-1);
            searchView = (SearchView) menuItem.getActionView();


            if (searchView != null){
                assert searchManager != null;
                searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
                searchView.setQueryHint(getResources().getString(R.string.search));
                searchView.setOnQueryTextListener(this);

            }

            MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
                @Override
                public boolean onMenuItemActionExpand(MenuItem item) {
                    return false;
                }

                @Override
                public boolean onMenuItemActionCollapse(MenuItem item) {
                    tvShowArrayList.clear();
                    getResultTVShowViewModel();
                    return true;
                }
            });

        }
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
        });*//*

        //super.onCreateOptionsMenu(menu, inflater);
    }*/

/*    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.setting) {
            Intent intent = new Intent(getActivity(), SettingActivity.class);
            startActivity(intent);
            Objects.requireNonNull(getActivity()).finish();
        }

        return super.onOptionsItemSelected(item);
    }*/

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



/*    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.i("onQueryTextSubmitTv", query);
        getTvSearchResult(api_key, query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.i("onQueryTextChangeTv", newText);
        getTvSearchResult(api_key, newText);
        return true;
    }*/
}
