package com.dicoding.moviecatalogsubmission.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dicoding.moviecatalogsubmission.BuildConfig;
import com.dicoding.moviecatalogsubmission.Repository.MoviesRepository;
import com.dicoding.moviecatalogsubmission.model.Entity.MovieResponse;
import com.dicoding.moviecatalogsubmission.model.Entity.TVShowResponse;

public class ViewModelMovie extends ViewModel {
    private MutableLiveData<MovieResponse> valueMoviesMutableLiveData;
    private MutableLiveData<TVShowResponse> tvShowResponseMutableLiveData;


    public void init() {
        if ((valueMoviesMutableLiveData != null) &&
                (tvShowResponseMutableLiveData != null)) {

            return;
        }

        MoviesRepository moviesRepository = MoviesRepository.getInstance();
        String api_key = BuildConfig.TMDB_API_KEY;
        valueMoviesMutableLiveData = moviesRepository.getMovies(api_key);
        tvShowResponseMutableLiveData = moviesRepository.getTVShows(api_key);

    }

    public LiveData<MovieResponse> getMoviesRepository() {
        return valueMoviesMutableLiveData;
    }

    public LiveData<TVShowResponse> getTVShowRepository() {
        return tvShowResponseMutableLiveData;
    }




}
