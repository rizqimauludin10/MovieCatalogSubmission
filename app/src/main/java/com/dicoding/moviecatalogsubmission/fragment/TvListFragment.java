package com.dicoding.moviecatalogsubmission.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.moviecatalogsubmission.R;
import com.dicoding.moviecatalogsubmission.adapter.RecycleViewTvAdapter;
import com.dicoding.moviecatalogsubmission.model.ViewModelMovie;
import com.dicoding.moviecatalogsubmission.model.modelAPI.TVShowsItem;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

public class TvListFragment extends Fragment {

    private RecyclerView rvTv;
    private Context context;
    private RecyclerView.Adapter tvAdapter;
    private List<TVShowsItem> tvShowArrayList = new ArrayList<>();
    private ShimmerFrameLayout mShimmerViewContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tvlist_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvTv = view.findViewById(R.id.rvTv);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
        context = getActivity();

        getResultTVShowViewModel();

        setupRecycleView();
    }

    private void setupRecycleView() {
        if (tvAdapter == null) {
            Log.e("Masuk", "TV Recycle View");
            tvAdapter = new RecycleViewTvAdapter(context, tvShowArrayList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            rvTv.setLayoutManager(layoutManager);
            rvTv.setAdapter(new RecycleViewTvAdapter(context, tvShowArrayList));
            rvTv.setItemAnimator(new DefaultItemAnimator());
            rvTv.setNestedScrollingEnabled(true);
        } else {
            tvAdapter.notifyDataSetChanged();
        }
    }

    private void getResultTVShowViewModel() {
        ViewModelMovie viewModelMovie = ViewModelProviders.of(this).get(ViewModelMovie.class);
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
}