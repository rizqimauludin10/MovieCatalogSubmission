package com.dicoding.moviecatalogsubmission.database;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.dicoding.moviecatalogsubmission.model.modelAPI.DetailMovieResponse;
import com.dicoding.moviecatalogsubmission.model.modelAPI.MoviesItem;
import com.dicoding.moviecatalogsubmission.model.modelAPI.TVShowsItem;

import java.util.List;

@Dao
public interface FavMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MoviesItem... moviesItems);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTv(TVShowsItem... tvShowsItems);

    @Query("SELECT * FROM favmovie WHERE id =:uid")
    LiveData<MoviesItem> selectedById(int uid);

    @Query("SELECT * FROM favmovie")
    LiveData<List<MoviesItem>> getAllMovieFav();

    @Query("SELECT * FROM favtv")
    LiveData<List<TVShowsItem>> getAllTvFav();

    @Query("DELETE FROM favmovie WHERE id = :uid")
    void deleteByid(long uid);

    @Query("DELETE FROM favmovie")
    void deleteAllMovieFav();


}
