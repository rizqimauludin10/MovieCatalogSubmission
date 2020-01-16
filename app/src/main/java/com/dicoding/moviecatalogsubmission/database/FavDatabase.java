package com.dicoding.moviecatalogsubmission.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.dicoding.moviecatalogsubmission.model.Entity.MoviesItem;
import com.dicoding.moviecatalogsubmission.model.Entity.TVShowsItem;

@Database(
        entities = {MoviesItem.class, TVShowsItem.class},
        version = 1,
        exportSchema = false)

public abstract class FavDatabase extends RoomDatabase {

    private static FavDatabase instance;

    public abstract FavMovieDao favMovieDao();

    public static synchronized FavDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FavDatabase.class, "moviecatalog_database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
