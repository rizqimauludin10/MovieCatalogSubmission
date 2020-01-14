package com.dicoding.moviecatalogsubmission.model.modelAPI;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "favmovie")
public class MoviesItem implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    @SerializedName("uid")
    private Integer uid;

    @ColumnInfo(name = "id")
    @SerializedName("id")
    private Integer id;

    @ColumnInfo(name = "title_column")
    @SerializedName("title")
    private String title;

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    private String posterPath;

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;

    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("overview")
    @Expose
    private String overview;

    /*@SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("vote_count")
    @Expose
    private Long voteCount;
    @SerializedName("video")
    @Expose
    private Boolean video;
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;*/

    public MoviesItem() {
    }

    public MoviesItem(Double popularity, Long voteCount, Boolean video, String posterPath,
                      Integer id, Boolean adult, String backdropPath, String originalLanguage,
                      String originalTitle, List<Long> genreIds, String title, Double voteAverage,
                      String overview, String releaseDate) {
        super();
        this.posterPath = posterPath;
        this.id = id;
        this.backdropPath = backdropPath;
       /* this.popularity = popularity;
        this.voteCount = voteCount;
        this.video = video;
        this.adult = adult;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;*/
        this.title = title;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }


    /*public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }*/


/*
    public MoviesItem(Cursor cursor) {

        this.id = DatabaseContract.getColumnInt(cursor, DatabaseContract.MovieColumns.MOVIE_ID);
        this.title = DatabaseContract.getColumnString(cursor, DatabaseContract.MovieColumns.TITTLE);
        this.posterPath = DatabaseContract.getColumnString(cursor, DatabaseContract.MovieColumns.POSTER_PATH);
        this.overview = DatabaseContract.getColumnString(cursor, DatabaseContract.MovieColumns.OVERVIEW);
        this.releaseDate = DatabaseContract.getColumnString(cursor, DatabaseContract.MovieColumns.RELEASE_DATE);
        this.voteAverage= DatabaseContract.getColumnDouble(cursor, DatabaseContract.MovieColumns.VOTE_AVERAGE);
    }
*/


    private MoviesItem(Parcel parcel) {
        this.id = parcel.readInt();
        this.title = parcel.readString();
        this.posterPath = parcel.readString();
        this.backdropPath = parcel.readString();
        this.overview = parcel.readString();
        this.releaseDate = parcel.readString();
        this.voteAverage = parcel.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.posterPath);
        dest.writeString(this.backdropPath);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
        dest.writeDouble(this.voteAverage);
    }

    public static final Creator<MoviesItem> CREATOR = new Creator<MoviesItem>() {
        @Override
        public MoviesItem createFromParcel(Parcel source) {
            return new MoviesItem(source);
        }

        @Override
        public MoviesItem[] newArray(int size) {
            return new MoviesItem[size];
        }
    };
}
