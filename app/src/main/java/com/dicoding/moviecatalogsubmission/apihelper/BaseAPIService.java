package com.dicoding.moviecatalogsubmission.apihelper;

import com.dicoding.moviecatalogsubmission.model.modelAPI.DetailMovieResponse;
import com.dicoding.moviecatalogsubmission.model.modelAPI.MovieResponse;
import com.dicoding.moviecatalogsubmission.model.modelAPI.TVDetailResponse;
import com.dicoding.moviecatalogsubmission.model.modelAPI.TVShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BaseAPIService {

    @GET("movie/popular")
    Call<MovieResponse> getValueMovies(
            @Query("api_key") String api_key
    );

    @GET("tv/popular")
    Call<TVShowResponse> getTVResponse(
            @Query("api_key") String api_key
    );

    @GET("movie/{id}")
    Call<DetailMovieResponse> getDetailMovie(
            @Path("id") Integer id,
            @Query("api_key") String api_key
    );

    @GET("tv/{id}")
    Call<TVDetailResponse> getDetailTv(
            @Path("id") Integer id,
            @Query("api_key") String api_key
    );


}
