package com.dicoding.moviecatalogsubmission.model;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dicoding.moviecatalogsubmission.MoviesRepoClass.MoviesRepository;
import com.dicoding.moviecatalogsubmission.model.modelAPI.DetailMovieResponse;
import com.dicoding.moviecatalogsubmission.model.modelAPI.GenreResponse;
import com.dicoding.moviecatalogsubmission.model.modelAPI.MovieResponse;
import com.dicoding.moviecatalogsubmission.model.modelAPI.TVShowResponse;

public class ViewModelMovie extends ViewModel {
    private MutableLiveData<MovieResponse> valueMoviesMutableLiveData;
    private MutableLiveData<TVShowResponse> tvShowResponseMutableLiveData;
    private MoviesRepository moviesRepository;
    private String api_key = "54d3f8cecc84d7140e160061e4602e45";


    public void init() {
        if ((valueMoviesMutableLiveData != null) &&
                (tvShowResponseMutableLiveData != null)) {

            return;
        }

        moviesRepository = MoviesRepository.getInstance();
        valueMoviesMutableLiveData = moviesRepository.getMovies(api_key);
        tvShowResponseMutableLiveData = moviesRepository.getTVShows(api_key);

    }

    public LiveData<MovieResponse> getMoviesRepository() {
        return valueMoviesMutableLiveData;
    }


    public LiveData<TVShowResponse> getTVShowRepository() {
        return tvShowResponseMutableLiveData;
    }

 /*   public LiveData<DetailMovieResponse> getDetailMovieRepository(Integer idMovieExtras) {
        detailMovieResponseMutableLiveData = moviesRepository.getDetailMovieResponse(idMovieExtras, api_key);
        return detailMovieResponseMutableLiveData;
    }*/

 }
