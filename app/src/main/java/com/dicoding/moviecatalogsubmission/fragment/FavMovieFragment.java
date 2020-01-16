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
import com.dicoding.moviecatalogsubmission.adapter.FavMovieAdapater;
import com.dicoding.moviecatalogsubmission.model.FavMovieViewModel;
import com.dicoding.moviecatalogsubmission.model.Entity.MoviesItem;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class FavMovieFragment extends Fragment {

    private FavMovieAdapater favMovieAdapater;
    private RecyclerView rvFavMovie;
    private List<MoviesItem> moviesItemArrayList = new ArrayList<>();
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_fav_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity();

        rvFavMovie = view.findViewById(R.id.rvFavMovies);

        getFavData();
        setupRecycleView();
    }

    private void getFavData() {
        FavMovieViewModel favMovieViewModel = ViewModelProviders.of(this).get(FavMovieViewModel.class);
        favMovieViewModel.getListLiveData().observe(this, moviesItems ->
                favMovieAdapater.setListMovies(moviesItems)
        );
    }

    private void setupRecycleView() {
        if (favMovieAdapater == null) {
            Log.e("Masuk Fav", "Movies Recycle View");
            favMovieAdapater = new FavMovieAdapater(context, moviesItemArrayList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            rvFavMovie.setLayoutManager(layoutManager);

            rvFavMovie.setAdapter(favMovieAdapater);
            rvFavMovie.setItemAnimator(new DefaultItemAnimator());
            rvFavMovie.setNestedScrollingEnabled(true);
        } else {
            favMovieAdapater.notifyDataSetChanged();
            showSnackbarMessage();
        }
    }

    private void showSnackbarMessage() {
        Snackbar.make(rvFavMovie, "Data Kosong", Snackbar.LENGTH_SHORT).show();
    }

}
