package com.dicoding.moviecatalogsubmission.database;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {

    static String TABLE_FAVORITE = "mvfavorite";

    public static final class MovieColumns implements BaseColumns {

        public static String MOVIE_ID = "movie_id";
        public static String TITTLE = "tittle_mv";
        public static String POSTER_PATH = "poster_path";
        public static String OVERVIEW = "overview_mv";
        public static String RELEASE_DATE = "release_date";
        public static String VOTE_AVERAGE = "vote_average";
    }

    public static final String AUTHORITY = "com.dicoding.moviecatalogsubmission";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_FAVORITE)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }

    public static double getColumnDouble(Cursor cursor, String columnName) {
        return cursor.getDouble(cursor.getColumnIndexOrThrow(columnName));
    }
}
