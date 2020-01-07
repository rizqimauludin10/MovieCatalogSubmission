package com.dicoding.moviecatalogsubmission.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.dicoding.moviecatalogsubmission.model.modelAPI.DetailMovieResponse;
import com.dicoding.moviecatalogsubmission.model.modelAPI.MoviesItem;

import java.util.ArrayList;

public class FavoriteHelper {
    private static final String DATABASE_TABLE = DatabaseContract.TABLE_FAVORITE;
    private static DatabaseHelper databaseHelper;
    private static FavoriteHelper INSTANCE;
    private static SQLiteDatabase database;
    Context context;

    private FavoriteHelper(Context context) {
        this.context = context;
    }

    public static FavoriteHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FavoriteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public FavoriteHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
        if (database.isOpen()){
            database.close();
        }
    }

    //mengambil semua data dari database
    public long insert(DetailMovieResponse detailMovieResponse) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseContract.MovieColumns.MOVIE_ID, detailMovieResponse.getId());
        contentValues.put(DatabaseContract.MovieColumns.TITTLE, detailMovieResponse.getTitle());
        contentValues.put(DatabaseContract.MovieColumns.POSTER_PATH, detailMovieResponse.getPosterPath());
        contentValues.put(DatabaseContract.MovieColumns.OVERVIEW, detailMovieResponse.getOverview());
        contentValues.put(DatabaseContract.MovieColumns.VOTE_AVERAGE, detailMovieResponse.getVoteAverage());
        contentValues.put(DatabaseContract.MovieColumns.RELEASE_DATE, detailMovieResponse.getReleaseDate());

        return database.insert(DATABASE_TABLE, null, contentValues);
    }

    public ArrayList<DetailMovieResponse> queryAll() {
        ArrayList<DetailMovieResponse> arrayList = new ArrayList<DetailMovieResponse>();
        Cursor cursor = database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                DatabaseContract.MovieColumns._ID + " DESC");
        cursor.moveToFirst();
        DetailMovieResponse detailMovieResponse;
        if (cursor.getCount() > 0) {
            do {
                detailMovieResponse = new DetailMovieResponse();
                detailMovieResponse.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.MOVIE_ID)));
                detailMovieResponse.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.TITTLE)));
                detailMovieResponse.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.POSTER_PATH)));
                detailMovieResponse.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.OVERVIEW)));
                detailMovieResponse.setVoteAverage(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.VOTE_AVERAGE)));
                detailMovieResponse.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.RELEASE_DATE)));

                arrayList.add(detailMovieResponse);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public Cursor isFavorite(int id) {
        String movie_id = "" + id;
        return database.query(
                DATABASE_TABLE,
                null,
                DatabaseContract.MovieColumns.MOVIE_ID + " = ?",
                new String[]{movie_id},
                null,
                null,
                null
        );
    }

    public int update(DetailMovieResponse detailMovieResponse){
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseContract.MovieColumns.MOVIE_ID, detailMovieResponse.getId());
        contentValues.put(DatabaseContract.MovieColumns.TITTLE, detailMovieResponse.getTitle());
        contentValues.put(DatabaseContract.MovieColumns.POSTER_PATH, detailMovieResponse.getPosterPath());
        contentValues.put(DatabaseContract.MovieColumns.OVERVIEW, detailMovieResponse.getOverview());
        contentValues.put(DatabaseContract.MovieColumns.VOTE_AVERAGE, detailMovieResponse.getVoteAverage());
        contentValues.put(DatabaseContract.MovieColumns.RELEASE_DATE, detailMovieResponse.getReleaseDate());

        return database.update(DATABASE_TABLE, contentValues, DatabaseContract.MovieColumns.MOVIE_ID + " = '" + detailMovieResponse.getId() + "'", null);
    }

    public int delete(int id){
        return database.delete(DATABASE_TABLE, DatabaseContract.MovieColumns.MOVIE_ID + " = '" + id + "'", null);
    }
}
