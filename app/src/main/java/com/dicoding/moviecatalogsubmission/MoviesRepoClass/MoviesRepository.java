package com.dicoding.moviecatalogsubmission.MoviesRepoClass;

import androidx.lifecycle.MutableLiveData;

import com.dicoding.moviecatalogsubmission.apihelper.BaseAPIService;
import com.dicoding.moviecatalogsubmission.apihelper.UtilsAPI;
import com.dicoding.moviecatalogsubmission.model.ResultMovies;
import com.dicoding.moviecatalogsubmission.model.ValueMovies;

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

    public MutableLiveData<ValueMovies> getMovies (String key) {
        final MutableLiveData<ValueMovies> resultMoviesMutableLiveData = new MutableLiveData<>();
        baseApiService.getValueMovies(key)
                .enqueue(new Callback<ValueMovies>() {
                    @Override
                    public void onResponse(Call<ValueMovies> call, Response<ValueMovies> response) {
                        if (response.isSuccessful()) {
                            resultMoviesMutableLiveData.setValue(response.body());
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<ValueMovies> call, Throwable t) {
                        resultMoviesMutableLiveData.setValue(null);
                    }
                });
        return resultMoviesMutableLiveData;
    }
}
