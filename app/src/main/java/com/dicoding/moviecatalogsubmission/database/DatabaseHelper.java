package com.dicoding.moviecatalogsubmission.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "dbmoviecatalog";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_MV = String.format("CREATE TABLE %s"
                    + "(%s INTEGER PRIMARY KEY AUTOINCREMENT,"
            + " %s TEXT NOT NULL,"
            + " %s TEXT NOT NULL,"
            + " %s TEXT NOT NULL,"
            + " %s TEXT,"
            + " %s TEXT,"
            + " %s TEXT)",
            DatabaseContract.TABLE_FAVORITE,
            DatabaseContract.MovieColumns._ID,
            DatabaseContract.MovieColumns.MOVIE_ID,
            DatabaseContract.MovieColumns.TITTLE,
            DatabaseContract.MovieColumns.POSTER_PATH,
            DatabaseContract.MovieColumns.OVERVIEW,
            DatabaseContract.MovieColumns.VOTE_AVERAGE,
            DatabaseContract.MovieColumns.RELEASE_DATE
    );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_FAVORITE );
        onCreate(db);
    }
}
