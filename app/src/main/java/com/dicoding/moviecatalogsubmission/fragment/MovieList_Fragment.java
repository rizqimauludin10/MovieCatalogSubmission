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
import com.dicoding.moviecatalogsubmission.adapter.RecycleMovieAdapter;
import com.dicoding.moviecatalogsubmission.apihelper.BaseAPIService;
import com.dicoding.moviecatalogsubmission.apihelper.UtilsAPI;
import com.dicoding.moviecatalogsubmission.model.ResultMovies;
import com.dicoding.moviecatalogsubmission.model.ValueMovies;
import com.dicoding.moviecatalogsubmission.model.ViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

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
    private ViewModel movieViewModel;

    private ShimmerFrameLayout mShimmerViewContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.movielist_fragment, container, false);

        rvMovie = (RecyclerView) view.findViewById(R.id.rvMovies);

        baseApiService = UtilsAPI.getApiService();

        context = getActivity();

        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);

        getResultMoviesViewModel();
        setupRecycleView();

        //getResultMovies();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void getResultMoviesViewModel() {
        movieViewModel = ViewModelProviders.of(this).get(ViewModel.class);
        movieViewModel.init();
        movieViewModel.getMoviesRepository().observe(this, movieResponse -> {
            Log.e("Masuk", "Number of movie with  = " + movieResponse.getTotalResults());
            List<ResultMovies> resultMoviesList = movieResponse.getResults();
            movieArrayList.addAll(resultMoviesList);
            moviesAdapter.notifyDataSetChanged();

            mShimmerViewContainer.stopShimmer();
            mShimmerViewContainer.setVisibility(View.GONE);
        });
    }

    private void setupRecycleView() {
        if (moviesAdapter == null) {
            moviesAdapter = new RecycleMovieAdapter(context, movieArrayList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            rvMovie.setLayoutManager(layoutManager);
            rvMovie.setAdapter(new RecycleMovieAdapter(context, movieArrayList));
            rvMovie.setItemAnimator(new DefaultItemAnimator());
            rvMovie.setNestedScrollingEnabled(true);
        } else {
            moviesAdapter.notifyDataSetChanged();
        }
    }

    private void getResultMovies() {
        baseApiService.getValueMovies(
                api_key
        ).enqueue(new Callback<ValueMovies>() {
            @Override
            public void onResponse(@NotNull Call<ValueMovies> call, Response<ValueMovies> response) {
                Log.e("Masuk", "Number of movie with  = " + response.body().getTotalResults());
                if (response.isSuccessful()) {

                    movieArrayList = response.body().getResults();
                    rvMovie.setAdapter(new RecycleMovieAdapter(context, movieArrayList));
                    moviesAdapter.notifyDataSetChanged();

                    mShimmerViewContainer.stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);
                } else {
                    mShimmerViewContainer.stopShimmer();
                    Toast.makeText(getActivity(), "Failed get data", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(@NotNull Call<ValueMovies> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(getActivity(), "Not Internet Connection", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    String a = t.getMessage();
                }
            }
        });
    }

}
