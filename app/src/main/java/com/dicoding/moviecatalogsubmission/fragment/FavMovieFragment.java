package com.dicoding.moviecatalogsubmission.fragment;

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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.moviecatalogsubmission.R;
import com.dicoding.moviecatalogsubmission.adapter.FavMovieAdapater;
import com.dicoding.moviecatalogsubmission.adapter.RecycleMovieAdapter;
import com.dicoding.moviecatalogsubmission.database.FavoriteHelper;
import com.dicoding.moviecatalogsubmission.model.modelAPI.DetailMovieResponse;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.dicoding.moviecatalogsubmission.database.DatabaseContract.CONTENT_URI;

public class FavMovieFragment extends Fragment {

    private FavMovieAdapater favMovieAdapater;
    private RecyclerView rvFavMovie;
    FavoriteHelper favoriteHelper;
    ArrayList<DetailMovieResponse> detailMovieResponse = new ArrayList<>();

    private Cursor cursor;
    private Context context;

     @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_fav_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvFavMovie = view.findViewById(R.id.rvFavMovies);
        favoriteHelper = FavoriteHelper.getInstance(context);
        //favoriteHelper.open();

        setupRecycleView();
        //cursor = favoriteHelper.queryAll();
        detailMovieResponse = favoriteHelper.queryAll();

        Log.d("FavFav", detailMovieResponse.toString());
        //new LoadFavoriteAsync().execute();
    }

    private void setupRecycleView() {
        if (favMovieAdapater == null) {
            Log.e("Masuk Fav", "Movies Recycle View");
            favMovieAdapater = new FavMovieAdapater(cursor, context);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            rvFavMovie.setLayoutManager(layoutManager);

            rvFavMovie.setAdapter(new FavMovieAdapater(cursor, context));
            rvFavMovie.setItemAnimator(new DefaultItemAnimator());
            rvFavMovie.setNestedScrollingEnabled(true);
        } else {
            favMovieAdapater.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //new LoadFavoriteAsync().execute();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        favoriteHelper.close();
    }
/*

    private class LoadFavoriteAsync extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            List<DetailMovieResponse> detailMovieResponses = favoriteHelper.queryAll();

           return (Cursor) detailMovieResponses;

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
    }
*/

    private void showSnackbarMessage() {
        Snackbar.make(rvFavMovie, "Data Kosong", Snackbar.LENGTH_SHORT).show();
    }

}
