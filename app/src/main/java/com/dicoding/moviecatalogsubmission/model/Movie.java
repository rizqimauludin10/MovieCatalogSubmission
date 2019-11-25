package com.dicoding.moviecatalogsubmission.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private String movieTittle;
    private int moviePoster;
    private String movieDate;
    private String movieDesc;
    private String movieRate2;
    private float movieRate;

    public String getMovieRate2() {
        return movieRate2;
    }

    public void setMovieRate2(String movieRate2) {
        this.movieRate2 = movieRate2;
    }

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

    public float getMovieRate() {
        return movieRate;
    }

    public void setMovieRate(float movieRate) {
        this.movieRate = movieRate;
    }

    public Movie() {

    }
    
    protected Movie(Parcel parcel){
        this.movieTittle = parcel.readString();
        this.movieDesc = parcel.readString();
        this.moviePoster = parcel.readInt();
        this.movieDate = parcel.readString();
        this.movieRate2 = parcel.readString();
        this.movieRate = parcel.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.movieTittle);
        dest.writeString(this.movieDesc);
        dest.writeInt(this.moviePoster);
        dest.writeString(this.movieDate);
        dest.writeString(this.movieRate2);
        dest.writeFloat(this.movieRate);
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
