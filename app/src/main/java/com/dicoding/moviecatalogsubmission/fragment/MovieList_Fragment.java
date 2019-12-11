package com.dicoding.moviecatalogsubmission.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
import com.dicoding.moviecatalogsubmission.model.modelAPI.GenreResponse;
import com.dicoding.moviecatalogsubmission.model.modelAPI.GenresItem;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

public class MovieList_Fragment extends Fragment {
    View view;
    RecyclerView rvMovie;
    Context context;
    private RecyclerView.Adapter moviesAdapter;
    private List<ResultMovies> movieArrayList = new ArrayList<>();
    private List<GenresItem> genreResponseArrayList = new ArrayList<>();
    private ViewModel movieViewModel;


    private ShimmerFrameLayout mShimmerViewContainer;
    int resId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.movielist_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovie = view.findViewById(R.id.rvMovies);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
        resId = R.anim.layout_animation_falldown;

        context = getActivity();

        getResultMoviesViewModel();

        setupRecycleView();
    }

    private void getResultMoviesViewModel() {
        movieViewModel = ViewModelProviders.of(this).get(ViewModel.class);
        movieViewModel.init();
        movieViewModel.getMoviesRepository().observe(this, valueMovies -> {

            if (valueMovies != null) {
                Log.e("Masuk", "Number of movie with  = " + valueMovies.getTotalResults());
                List<ResultMovies> resultMoviesList = valueMovies.getResults();
                movieArrayList.addAll(resultMoviesList);
                moviesAdapter.notifyDataSetChanged();
                mShimmerViewContainer.stopShimmer();
                mShimmerViewContainer.setVisibility(View.GONE);
            } else {
                Toast.makeText(getActivity(), "Check Internet Connection & Reload App", Toast.LENGTH_SHORT).show();
            }

        });
    }


 /*   private void getResultGenre() {
        movieViewModel = ViewModelProviders.of(this).get(ViewModel.class);
        movieViewModel.init();
        movieViewModel.getGenreRepository().observe(this, genreResponse -> {
            List<GenresItem> genresItemList = genreResponse.getGenres();
            genreResponseArrayList.addAll(genresItemList);

            Log.d("Genre =>", String.valueOf(genresItemList));
        });
    }*/


    private void setupRecycleView() {
        if (moviesAdapter == null) {
            moviesAdapter = new RecycleMovieAdapter(context, movieArrayList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            rvMovie.setLayoutManager(layoutManager);

            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(context, resId);
            rvMovie.setLayoutAnimation(animation);

            rvMovie.setAdapter(new RecycleMovieAdapter(context, movieArrayList));
            rvMovie.setItemAnimator(new DefaultItemAnimator());
            rvMovie.setNestedScrollingEnabled(true);
        } else {
            moviesAdapter.notifyDataSetChanged();
        }
    }

 /*   private void getResultMovies() {
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
    }*/

}
