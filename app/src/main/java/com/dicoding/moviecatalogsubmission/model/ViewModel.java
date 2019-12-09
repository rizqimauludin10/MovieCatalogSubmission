package com.dicoding.moviecatalogsubmission.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dicoding.moviecatalogsubmission.MoviesRepoClass.MoviesRepository;

import java.util.List;

public class ViewModel extends androidx.lifecycle.ViewModel {
    private MutableLiveData<ValueMovies> valueMoviesMutableLiveData;
    private MoviesRepository moviesRepository;
    private String api_key = "54d3f8cecc84d7140e160061e4602e45";

    public void init() {
        if (valueMoviesMutableLiveData != null) {
            return;
        }

        moviesRepository = MoviesRepository.getInstance();
        valueMoviesMutableLiveData = moviesRepository.getMovies(api_key);
    }

    public LiveData<ValueMovies> getMoviesRepository() {
        return valueMoviesMutableLiveData;
    }
}
