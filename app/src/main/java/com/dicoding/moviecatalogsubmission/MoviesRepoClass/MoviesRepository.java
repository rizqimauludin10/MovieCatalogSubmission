package com.dicoding.moviecatalogsubmission.MoviesRepoClass;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.dicoding.moviecatalogsubmission.apihelper.BaseAPIService;
import com.dicoding.moviecatalogsubmission.apihelper.UtilsAPI;
import com.dicoding.moviecatalogsubmission.model.modelAPI.DetailMovieResponse;
import com.dicoding.moviecatalogsubmission.model.modelAPI.MovieResponse;
import com.dicoding.moviecatalogsubmission.model.modelAPI.GenreResponse;
import com.dicoding.moviecatalogsubmission.model.modelAPI.TVShowResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository {
    private static MoviesRepository moviesRepository;

    public static MoviesRepository getInstance() {
        if (moviesRepository == null) {
            moviesRepository = new MoviesRepository();
        }

        return moviesRepository;
    }


    private BaseAPIService baseApiService;

    public MoviesRepository() {
        baseApiService = UtilsAPI.getApiService();
    }

    public MutableLiveData<MovieResponse> getMovies(String key) {
        final MutableLiveData<MovieResponse> resultMoviesMutableLiveData = new MutableLiveData<>();
        baseApiService.getValueMovies(key)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        if (response.isSuccessful()) {
                            resultMoviesMutableLiveData.setValue(response.body());
                        } else {
                            resultMoviesMutableLiveData.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {
                        resultMoviesMutableLiveData.setValue(null);

                        if (t instanceof IOException) {
                            String b = t.getMessage();
                            Log.e("Error Message => ", b);
                        } else {
                            String a = t.getMessage();
                            Log.e("Error Message => ", a);
                        }

                    }
                });
        return resultMoviesMutableLiveData;
    }

    public MutableLiveData<TVShowResponse> getTVShows(String key) {
        final MutableLiveData<TVShowResponse> tvShowResponseMutableLiveData = new MutableLiveData<>();
        baseApiService.getTVResponse(key)
                .enqueue(new Callback<TVShowResponse>() {
                    @Override
                    public void onResponse(Call<TVShowResponse> call, Response<TVShowResponse> response) {
                        if (response.isSuccessful()) {
                            tvShowResponseMutableLiveData.setValue(response.body());
                        } else {
                            tvShowResponseMutableLiveData.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<TVShowResponse> call, Throwable t) {
                        if (t instanceof IOException) {
                            String b = t.getMessage();
                            Log.e("Error Message => ", b);
                        } else {
                            String a = t.getMessage();
                            Log.e("Error Message => ", a);
                        }
                    }
                });
        return tvShowResponseMutableLiveData;
    }


/*
    public MutableLiveData<DetailMovieResponse> getDetailMovieResponse(Integer id, String key) {
        final MutableLiveData<DetailMovieResponse> detailMovieResponseMutableLiveData = new MutableLiveData<>();
        baseApiService.getDetailResponse(id, key)
                .enqueue(new Callback<DetailMovieResponse>() {
                    @Override
                    public void onResponse(Call<DetailMovieResponse> call, Response<DetailMovieResponse> response) {
                        if (response.isSuccessful()) {
                            detailMovieResponseMutableLiveData.setValue(response.body());
                        } else {
                            detailMovieResponseMutableLiveData.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<DetailMovieResponse> call, Throwable t) {
                        if (t instanceof IOException) {
                            String b = t.getMessage();
                            Log.e("Error Message => ", b);
                        } else {
                            String a = t.getMessage();
                            Log.e("Error Message => ", a);
                        }
                    }
                });

        return detailMovieResponseMutableLiveData;
    }
*/



    public MutableLiveData<GenreResponse> getGenre(String key) {
        final MutableLiveData<GenreResponse> genreResponseMutableLiveData = new MutableLiveData<>();
        baseApiService.getGenresResponse(key)
                .enqueue(new Callback<GenreResponse>() {
                    @Override
                    public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                        if (response.isSuccessful()) {
                            genreResponseMutableLiveData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<GenreResponse> call, Throwable t) {
                        if (t instanceof IOException) {
                            String b = t.getMessage();
                            Log.e("Error Message => ", b);
                        } else {
                            String a = t.getMessage();
                            Log.e("Error Message => ", a);
                        }
                    }
                });
        return genreResponseMutableLiveData;
    }
}
