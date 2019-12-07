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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.moviecatalogsubmission.R;
import com.dicoding.moviecatalogsubmission.adapter.RecycleMovieAdapter;
import com.dicoding.moviecatalogsubmission.apihelper.BaseAPIService;
import com.dicoding.moviecatalogsubmission.apihelper.UtilsAPI;
import com.dicoding.moviecatalogsubmission.model.Movie;
import com.dicoding.moviecatalogsubmission.model.ResultMovies;
import com.dicoding.moviecatalogsubmission.model.ValueMovies;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieList_Fragment extends Fragment {
    View view;
    RecyclerView rvMovie;
    Context context;
    private RecyclerView.Adapter moviesAdapter;
    private List<ResultMovies> movieArrayList = new ArrayList<>();
    private BaseAPIService baseApiService;
    private String api_key = "54d3f8cecc84d7140e160061e4602e45";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.movielist_fragment, container, false);

        rvMovie = (RecyclerView) view.findViewById(R.id.rvMovies);

        baseApiService = UtilsAPI.getApiService();

        context = getActivity();


        moviesAdapter = new RecycleMovieAdapter(context, movieArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rvMovie.setLayoutManager(layoutManager);
        rvMovie.setItemAnimator(new DefaultItemAnimator());


        getResultMovies();
        //getDetail();


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void getResultMovies() {
        baseApiService.getValueMovies(
                api_key
        ).enqueue(new Callback<ValueMovies>() {
            @Override
            public void onResponse(@NotNull Call<ValueMovies> call, Response<ValueMovies> response) {
                Log.e("Masuk", "Number of movie with  = "+response.body().getTotalResults());
                //Toast.makeText(getActivity(), "Masuk Bos", Toast.LENGTH_SHORT).show();

                movieArrayList = response.body().getResults();
                rvMovie.setAdapter(new RecycleMovieAdapter(context, movieArrayList));
                moviesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NotNull Call<ValueMovies> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(getActivity(), "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    String a = t.getMessage();
                    Log.e("Apa hayo?", a);
                }
            }
        });
    }

  /*  private void getDetail() {
        baseApiService.getDetailMovies().enqueue(new Callback<DetailMovies>() {
            @Override
            public void onResponse(Call<DetailMovies> call, Response<DetailMovies> response) {
                Toast.makeText(getActivity(), "Masuk Bos", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<DetailMovies> call, Throwable t) {
if (t instanceof IOException) {
                    Toast.makeText(getActivity(), "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                    t.getCause();
                }
                else {
                    Toast.makeText(getActivity(), "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    //
                }
            }
        });
    }*/


}
