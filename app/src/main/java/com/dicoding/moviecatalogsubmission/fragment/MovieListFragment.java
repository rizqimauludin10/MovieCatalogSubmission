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
import com.dicoding.moviecatalogsubmission.model.ViewModelMovie;
import com.dicoding.moviecatalogsubmission.model.modelAPI.MoviesItem;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

public class MovieListFragment extends Fragment {
    private RecyclerView rvMovie;
    private Context context;
    private RecyclerView.Adapter moviesAdapter;
    private List<MoviesItem> movieArrayList = new ArrayList<>();
    private ShimmerFrameLayout mShimmerViewContainer;
    private int resId;
    private int someStateValue;
    private final String SOME_VALUE_KEY = "someValueToSave";


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        //outState.putInt(SOME_VALUE_KEY, someStateValue);
        super.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movielist_fragment, container, false);
       /* if (savedInstanceState != null) {
            someStateValue = savedInstanceState.getInt(SOME_VALUE_KEY);
        }*/
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

    private void setupRecycleView() {
        if (moviesAdapter == null) {
            Log.e("Masuk", "Movies Recycle View");
            moviesAdapter = new RecycleMovieAdapter(context, movieArrayList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            rvMovie.setLayoutManager(layoutManager);
            /*LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(context, resId);
            rvMovie.setLayoutAnimation(animation);*/

            rvMovie.setAdapter(new RecycleMovieAdapter(context, movieArrayList));
            rvMovie.setItemAnimator(new DefaultItemAnimator());
            rvMovie.setNestedScrollingEnabled(true);
        } else {
            moviesAdapter.notifyDataSetChanged();
        }
    }

    private void getResultMoviesViewModel() {
        ViewModelMovie movieViewModelMovie = ViewModelProviders.of(this).get(ViewModelMovie.class);
        movieViewModelMovie.init();
        movieViewModelMovie.getMoviesRepository().observe(this, movieResponse -> {
            if (movieResponse != null) {
                Log.e("Masuk", "Number of movie with  = " + movieResponse.getTotalResults());
                List<MoviesItem> moviesItemList = movieResponse.getResults();
                movieArrayList.addAll(moviesItemList);
                moviesAdapter.notifyDataSetChanged();
                mShimmerViewContainer.stopShimmer();
                mShimmerViewContainer.setVisibility(View.GONE);
            } else {
                Toast.makeText(getActivity(), "Check Internet Connection & Reload App", Toast.LENGTH_SHORT).show();
            }

        });
    }

}
