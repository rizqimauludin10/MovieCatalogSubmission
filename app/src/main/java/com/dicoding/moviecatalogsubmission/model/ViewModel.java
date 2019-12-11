package com.dicoding.moviecatalogsubmission.model;

import android.app.Activity;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dicoding.moviecatalogsubmission.MoviesRepoClass.MoviesRepository;
import com.dicoding.moviecatalogsubmission.model.modelAPI.GenreResponse;

import java.util.List;

public class ViewModel extends androidx.lifecycle.ViewModel {
    private MutableLiveData<ValueMovies> valueMoviesMutableLiveData;
    private MutableLiveData<GenreResponse> genreResponseMutableLiveData;
    private MoviesRepository moviesRepository;
    private String api_key = "54d3f8cecc84d7140e160061e4602e45";

    public void init() {
        if (valueMoviesMutableLiveData != null && genreResponseMutableLiveData != null) {
            return;
        }

        moviesRepository = MoviesRepository.getInstance();
        valueMoviesMutableLiveData = moviesRepository.getMovies(api_key);
        genreResponseMutableLiveData = moviesRepository.getGenre(api_key);
    }

    public LiveData<ValueMovies> getMoviesRepository() {
        return valueMoviesMutableLiveData;
    }

    public LiveData<GenreResponse> getGenreRepository() {
        return genreResponseMutableLiveData;
    }
}
