package com.dicoding.moviecatalogsubmission.MoviesRepoClass;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.dicoding.moviecatalogsubmission.apihelper.BaseAPIService;
import com.dicoding.moviecatalogsubmission.apihelper.UtilsAPI;
import com.dicoding.moviecatalogsubmission.model.ValueMovies;
import com.dicoding.moviecatalogsubmission.model.modelAPI.GenreResponse;

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

    public MutableLiveData<ValueMovies> getMovies(String key) {
        final MutableLiveData<ValueMovies> resultMoviesMutableLiveData = new MutableLiveData<>();
        baseApiService.getValueMovies(key)
                .enqueue(new Callback<ValueMovies>() {
                    @Override
                    public void onResponse(Call<ValueMovies> call, Response<ValueMovies> response) {
                        if (response.isSuccessful()) {
                            resultMoviesMutableLiveData.setValue(response.body());
                        } else {
                            resultMoviesMutableLiveData.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ValueMovies> call, Throwable t) {
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

    public MutableLiveData<GenreResponse> getGenre(String key) {
        final MutableLiveData<GenreResponse> genreResponseMutableLiveData = new MutableLiveData<>();
        baseApiService.getGenresResponse(key)
                .enqueue(new Callback<GenreResponse>() {
                    @Override
                    public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                        if (response.isSuccessful()) {
                            genreResponseMutableLiveData.setValue(response.body());
                        } else {

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
