package com.dicoding.moviecatalogsubmission.apihelper;

import com.dicoding.moviecatalogsubmission.model.Movie;
import com.dicoding.moviecatalogsubmission.model.ResultMovies;
import com.dicoding.moviecatalogsubmission.model.ValueMovies;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BaseAPIService {

    @GET("discover/movie")
    Call<ValueMovies> getValueMovies(
            @Query("api_key") String api_key

    );

   /* @GET("movie/474350?api_key="+api_key)
    Call<DetailMovies> getDetailMovies();*/
}
