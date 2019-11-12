package com.dicoding.moviecatalogsubmission.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
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

    public Movie() {

    }
    
    protected Movie(Parcel parcel){
        this.movieTittle = parcel.readString();
        this.movieDesc = parcel.readString();
        this.moviePoster = parcel.readInt();
        this.movieDate = parcel.readString();
        this.movieRate = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.movieTittle);
        dest.writeString(this.movieDesc);
        dest.writeInt(this.moviePoster);
        dest.writeString(this.movieDate);
        dest.writeString(this.movieRate);
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
    };
}
