package com.dicoding.moviecatalogsubmission.apihelper;

import com.dicoding.moviecatalogsubmission.model.modelAPI.DetailMovieResponse;
import com.dicoding.moviecatalogsubmission.model.modelAPI.MovieResponse;
import com.dicoding.moviecatalogsubmission.model.modelAPI.GenreResponse;
import com.dicoding.moviecatalogsubmission.model.modelAPI.TVShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BaseAPIService {

    @GET("discover/movie")
    Call<MovieResponse> getValueMovies(
            @Query("api_key") String api_key
    );

    @GET("genre/movie/list")
    Call<GenreResponse> getGenresResponse(
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
}
