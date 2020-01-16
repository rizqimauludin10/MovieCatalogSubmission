package com.dicoding.moviecatalogsubmission.database;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.dicoding.moviecatalogsubmission.model.Entity.MoviesItem;
import com.dicoding.moviecatalogsubmission.model.Entity.TVShowsItem;

import java.util.List;

@Dao
public interface FavMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MoviesItem... moviesItems);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTv(TVShowsItem... tvShowsItems);

    @Query("SELECT * FROM favmovie WHERE id =:aid")
    LiveData<MoviesItem> selectedById(int aid);

    @Query("SELECT * FROM favtv WHERE id =:aid")
    LiveData<TVShowsItem> selectedTvById(int aid);

    @Query("SELECT * FROM favmovie ORDER BY uid DESC")
    LiveData<List<MoviesItem>> getAllMovieFav();

    @Query("SELECT * FROM favtv ORDER BY uid DESC")
    LiveData<List<TVShowsItem>> getAllTvFav();

    @Query("DELETE FROM favmovie WHERE id =:uid")
    void deleteByid(int uid);

    @Query("DELETE FROM favtv WHERE id =:uid")
    void deleteTvByid(int uid);


}
