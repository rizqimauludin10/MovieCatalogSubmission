package com.dicoding.moviecatalogsubmission.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.moviecatalogsubmission.R;
import com.dicoding.moviecatalogsubmission.adapter.FavMovieAdapater;
import com.dicoding.moviecatalogsubmission.database.FavoriteHelper;
import com.dicoding.moviecatalogsubmission.model.FavMovieViewModel;
import com.dicoding.moviecatalogsubmission.model.modelAPI.DetailMovieResponse;
import com.dicoding.moviecatalogsubmission.model.modelAPI.MoviesItem;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class FavMovieFragment extends Fragment {

    private FavMovieAdapater favMovieAdapater;
    private RecyclerView rvFavMovie;
    private FavoriteHelper favoriteHelper;
    private ArrayList<DetailMovieResponse> detailMovieResponse = new ArrayList<>();

    private Cursor cursor = null;
    private Context context;

    FavMovieViewModel favMovieViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_fav_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvFavMovie = view.findViewById(R.id.rvFavMovies);
        favMovieAdapater = new FavMovieAdapater(context);
        /*favoriteHelper = FavoriteHelper.getInstance(getContext());
        favoriteHelper.open();*/
        //setupRecycleView();
        rvFavMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFavMovie.setAdapter(favMovieAdapater);
        rvFavMovie.setHasFixedSize(true);
        favMovieViewModel = ViewModelProviders.of(this).get(FavMovieViewModel.class);
        favMovieViewModel.getListLiveData().observe(this, new Observer<List<MoviesItem>>() {
                    @Override
                    public void onChanged(List<MoviesItem> moviesItems) {
                        favMovieAdapater.setListMovies(moviesItems);
                        //Log.d("FavFav", String.valueOf(moviesItems));
                    }
                });






        //cursor = favoriteHelper.queryAll();
        //detailMovieResponse = favoriteHelper.queryAll();


        //new LoadFavoriteAsync().execute();
    }

/*    private void setupRecycleView() {
        if (favMovieAdapater == null) {
            Log.e("Masuk Fav", "Movies Recycle View");
            favMovieAdapater = new FavMovieAdapater();
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            rvFavMovie.setLayoutManager(layoutManager);

            rvFavMovie.setAdapter(favMovieAdapater);
            rvFavMovie.setItemAnimator(new DefaultItemAnimator());
            rvFavMovie.setNestedScrollingEnabled(true);
        } else {
            favMovieAdapater.notifyDataSetChanged();
        }
    }*/

    @Override
    public void onResume() {
        super.onResume();
        //new LoadFavoriteAsync().execute();
        //new LoadFav().execute();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //favoriteHelper.close();
    }

   /* private class LoadFav extends AsyncTask<Void, Void, ArrayList> {

        @Override
        protected ArrayList doInBackground(Void... voids) {
            detailMovieResponse = favoriteHelper.queryAll();
            //Log.d("FavFav", String.valueOf(detailMovieResponse = favoriteHelper.queryAll()));
            return detailMovieResponse;
        }

        @Override
        protected void onPostExecute(ArrayList arrayList) {
            super.onPostExecute(arrayList);

            //cursor =  arrayList;
            favMovieAdapater.setListMovies(cursor);
            favMovieAdapater.notifyDataSetChanged();

            *//*if (cursor.getCount() == 0) {
                showSnackbarMessage();
            }*//*
        }
    }*/

   /* @SuppressLint("StaticFieldLeak")
    private class LoadFavoriteAsync extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            //favoriteHelper.queryAll();

            //cursor = favoriteHelper.queryAll();

            return null;
        }

        @Override
        protected void onPostExecute(Cursor list) {
            super.onPostExecute(list);
            cursor = list;
            favMovieAdapater.setListMovies(cursor);
            favMovieAdapater.notifyDataSetChanged();

            if (cursor.getCount() == 0) {
                showSnackbarMessage();
            }

        }
    }*/

    private void showSnackbarMessage() {
        Snackbar.make(rvFavMovie, "Data Kosong", Snackbar.LENGTH_SHORT).show();
    }

}
