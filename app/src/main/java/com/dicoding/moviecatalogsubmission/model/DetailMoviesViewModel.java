package com.dicoding.moviecatalogsubmission.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dicoding.moviecatalogsubmission.MoviesRepoClass.MoviesRepository;
import com.dicoding.moviecatalogsubmission.model.modelAPI.DetailMovieResponse;

public class DetailMoviesViewModel extends ViewModelMovie {
    private MutableLiveData<DetailMovieResponse> detailMovieResponseMutableLiveData;
    private MoviesRepository moviesRepository;
    private String api_key = "54d3f8cecc84d7140e160061e4602e45";
    private Integer idMovieExtras = 0;

    public void init() {
        if ((detailMovieResponseMutableLiveData != null)) {
            Log.e("Kondisi Init", "Init Detail");
            return;
        }
        moviesRepository = MoviesRepository.getInstance();
        //detailMovieResponseMutableLiveData = moviesRepository.getDetailMovieResponse(idMovieExtras, api_key);

    }

   /* public LiveData<DetailMovieResponse> getDetailMovieRepository(Integer idMovieExtras) {
        //detailMovieResponseMutableLiveData = moviesRepository.getDetailMovieResponse(idMovieExtras, api_key);
        setId(idMovieExtras);
        return detailMovieResponseMutableLiveData;
    }

    void setId(final Integer idMovies){
        detailMovieResponseMutableLiveData = moviesRepository.getDetailMovieResponse(idMovies, api_key);
    }*/
}