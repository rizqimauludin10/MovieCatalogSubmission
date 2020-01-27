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
    private MoviesRepository moviesRepository;

    public void init() {
        if ((valueMoviesMutableLiveData != null) &&
                (tvShowResponseMutableLiveData != null)) {

            return;
        }

        moviesRepository = MoviesRepository.getInstance();
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

    public LiveData<MovieResponse> getSearch(String key, String query) {
        return moviesRepository.getMovieSearchRepo(key, query);
    }

    public LiveData<TVShowResponse> getTvSearch(String key, String query) {
        return moviesRepository.getTvSearchRepo(key, query);
    }


}
