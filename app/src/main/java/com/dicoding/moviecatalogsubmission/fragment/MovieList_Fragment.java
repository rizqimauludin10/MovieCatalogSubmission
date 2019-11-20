package com.dicoding.moviecatalogsubmission.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.moviecatalogsubmission.R;
import com.dicoding.moviecatalogsubmission.adapter.RecycleMovieAdapter;
import com.dicoding.moviecatalogsubmission.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieList_Fragment extends Fragment {
    View view;
    RecyclerView rvMovie;
    Context context;
    private RecyclerView.Adapter moviesAdapter;
    private List<Movie> movieArrayList = new ArrayList<>();

    private String[] dataTittle;
    private String[] dataDate;
    private String[] dataDesc;
    private String[] dataRate;
    private TypedArray dataPoster;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.movielist_fragment, container, false);

        rvMovie = (RecyclerView) view.findViewById(R.id.rvMovies);
        context = getActivity();

        moviesAdapter = new RecycleMovieAdapter(context, movieArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rvMovie.setLayoutManager(layoutManager);
        rvMovie.setItemAnimator(new DefaultItemAnimator());

        prepare();
        addItem();


        return view;
    }

    private void addItem() {
        movieArrayList = new ArrayList<>();

        for (int i = 0; i < dataTittle.length; i++) {
            Movie movie = new Movie();
            movie.setMoviePoster(dataPoster.getResourceId(i, -1));
            movie.setMovieTittle(dataTittle[i]);
            movie.setMovieDate(dataDate[i]);
            movie.setMovieDesc(dataDesc[i]);
            movie.setMovieRate(dataRate[i]);
            movieArrayList.add(movie);
        }
        rvMovie.setAdapter(new RecycleMovieAdapter(context, movieArrayList));
    }

    private void prepare() {
        dataPoster = getResources().obtainTypedArray(R.array.data_photo);
        dataTittle = getResources().getStringArray(R.array.data_name);
        dataDesc = getResources().getStringArray(R.array.data_description);
        dataRate = getResources().getStringArray(R.array.data_rate);
        dataDate = getResources().getStringArray(R.array.data_date);

    }


}
