package com.dicoding.moviecatalogsubmission.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TVShow implements Parcelable {
    private String tvTittle;
    private int tvPoster;
    private String tvDate;
    private String tvDesc;
    private String tvRate2;
    private float tvRate;

    public TVShow() {
    }

    public String getTvTittle() {
        return tvTittle;
    }

    public void setTvTittle(String tvTittle) {
        this.tvTittle = tvTittle;
    }

    public int getTvPoster() {
        return tvPoster;
    }

    public void setTvPoster(int tvPoster) {
        this.tvPoster = tvPoster;
    }

    public String getTvDate() {
        return tvDate;
    }

    public void setTvDate(String tvDate) {
        this.tvDate = tvDate;
    }

    public String getTvDesc() {
        return tvDesc;
    }

    public void setTvDesc(String tvDesc) {
        this.tvDesc = tvDesc;
    }

    public String getTvRate2() {
        return tvRate2;
    }

    public void setTvRate2(String tvRate2) {
        this.tvRate2 = tvRate2;
    }

    public float getTvRate() {
        return tvRate;
    }

    public void setTvRate(float tvRate) {
        this.tvRate = tvRate;
    }

    protected TVShow(Parcel parcel){
        this.tvTittle = parcel.readString();
        this.tvDesc = parcel.readString();
        this.tvPoster = parcel.readInt();
        this.tvDate = parcel.readString();
        this.tvRate2 = parcel.readString();
        this.tvRate = parcel.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tvTittle);
        dest.writeString(this.tvDesc);
        dest.writeInt(this.tvPoster);
        dest.writeString(this.tvDate);
        dest.writeString(this.tvRate2);
        dest.writeFloat(this.tvRate);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Parcelable.Creator<TVShow> CREATOR = new Parcelable.Creator<TVShow>() {
        @Override
        public TVShow createFromParcel(Parcel source) {
            return new TVShow(source);
        }

        @Override
        public TVShow[] newArray(int size) {
            return new TVShow[size];
        }
    };
}
