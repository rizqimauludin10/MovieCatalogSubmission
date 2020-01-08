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

import java.util.List;

@Dao
public interface FavMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MoviesItem... moviesItems);

    @Query("SELECT * FROM favmovie")
    Cursor selectAll();

    @Query("SELECT * FROM favmovie WHERE id = :uid")
    LiveData<MoviesItem> selectedById(Integer uid);

    @Query("DELETE FROM favmovie WHERE id = :uid")
    void deleteByid(long uid);

    @Query("SELECT * FROM favmovie")
    LiveData<List<MoviesItem>> getAllMovieFav();

    @Query("DELETE FROM favmovie")
    void deleteAllMovieFav();


}
