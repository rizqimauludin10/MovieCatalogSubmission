package com.dicoding.moviecatalogsubmission.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.moviecatalogsubmission.R;
import com.dicoding.moviecatalogsubmission.adapter.FavTvAdapter;
import com.dicoding.moviecatalogsubmission.model.FavMovieViewModel;
import com.dicoding.moviecatalogsubmission.model.Entity.TVShowsItem;

import java.util.ArrayList;
import java.util.List;

public class FavTvFragment extends Fragment {

    private FavTvAdapter favTvAdapter;
    private RecyclerView rvFavTv;
    private List<TVShowsItem> tvShowsItemArrayList = new ArrayList<>();
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tv_fav_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity();

        rvFavTv = view.findViewById(R.id.rvFavTv);

        getFavData();
        setupRecycleView();
    }

    private void getFavData() {
        FavMovieViewModel favMovieViewModel = ViewModelProviders.of(this).get(FavMovieViewModel.class);
        favMovieViewModel.getListTvLiveData().observe(this, tvShowsItems ->
                favTvAdapter.setTvShowList(tvShowsItems));
    }

    private void setupRecycleView() {
        if (favTvAdapter == null) {
            Log.e("Masuk Fav", "Movies Recycle View");
            favTvAdapter = new FavTvAdapter(tvShowsItemArrayList, context);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            rvFavTv.setLayoutManager(layoutManager);

            rvFavTv.setAdapter(favTvAdapter);
            rvFavTv.setItemAnimator(new DefaultItemAnimator());
            rvFavTv.setNestedScrollingEnabled(true);
        } else {
            favTvAdapter.notifyDataSetChanged();
        }
    }
}
