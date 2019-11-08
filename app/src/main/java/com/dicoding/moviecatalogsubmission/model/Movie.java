package com.dicoding.moviecatalogsubmission.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie {
    private String movieTittle;
    private int moviePoster;
    private String movieDate;
    private String movieDesc;
    private String movieRate;

    public String getMovieTittle() {
        return movieTittle;
    }

    public void setMovieTittle(String movieTittle) {
        this.movieTittle = movieTittle;
    }

    public int getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(int moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(String movieDate) {
        this.movieDate = movieDate;
    }

    public String getMovieDesc() {
        return movieDesc;
    }

    public void setMovieDesc(String movieDesc) {
        this.movieDesc = movieDesc;
    }

    public String getMovieRate() {
        return movieRate;
    }

    public void setMovieRate(String movieRate) {
        this.movieRate = movieRate;
    }
/*
    protected Movie(Parcel parcel){
        movieTittle = parcel.readString();
        movieDesc = parcel.readString();
        moviePoster = parcel.readInt();
        movieDate = parcel.readString();
        movieRate = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(movieTittle);
        dest.writeString(movieDesc);
        dest.writeInt(moviePoster);
        dest.writeString(movieDate);
        dest.writeString(movieRate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };*/
}
