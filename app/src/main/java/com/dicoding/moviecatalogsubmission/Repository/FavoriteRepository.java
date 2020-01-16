package com.dicoding.moviecatalogsubmission.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.dicoding.moviecatalogsubmission.database.FavDatabase;
import com.dicoding.moviecatalogsubmission.database.FavMovieDao;
import com.dicoding.moviecatalogsubmission.model.Entity.MoviesItem;
import com.dicoding.moviecatalogsubmission.model.Entity.TVShowsItem;

import java.util.List;

public class FavoriteRepository {
    private FavMovieDao favMovieDao;
    private LiveData<List<MoviesItem>> listLiveData;
    private LiveData<List<TVShowsItem>> listTvLiveData;
    private FavDatabase favDatabase;

    public FavoriteRepository(Application application) {
        favDatabase = FavDatabase.getInstance(application);
        favMovieDao = favDatabase.favMovieDao();
        listLiveData = favMovieDao.getAllMovieFav();
        listTvLiveData = favMovieDao.getAllTvFav();
    }

    public LiveData<List<MoviesItem>> getListLiveData() {
        return listLiveData;
    }

    public void insert(MoviesItem moviesItem) {
        new InsertMovieAsycTask(favMovieDao).execute(moviesItem);
    }

    public void insertTv(TVShowsItem tvShowsItem) {
        new InsertTVAsycTask(favMovieDao).execute(tvShowsItem);
    }

    public LiveData<List<TVShowsItem>> getListTvLiveData() {
        return listTvLiveData;
    }

    public void deleteById(int id){
        new DeleteMovieAsycTask(favMovieDao).execute(id);
    }

    public void deleteTvById(int id){
        new DeleteTvAsycTask(favMovieDao).execute(id);
    }

    public LiveData<MoviesItem> selectedByid(int id) {
        Log.e("Fav", "Movie Fav Repository" + " " + id);
        return favDatabase.favMovieDao().selectedById(id);
    }

    public LiveData<TVShowsItem> selectedTvById(int id){
        Log.e("Fav", "TV Fav Repository" + " " + id);
        return favDatabase.favMovieDao().selectedTvById(id);
    }

    private static class InsertMovieAsycTask extends AsyncTask<MoviesItem, Void, Void> {
        private FavMovieDao favMovieDao;

        private InsertMovieAsycTask(FavMovieDao favMovieDao) {
            this.favMovieDao = favMovieDao;
        }

        @Override
        protected Void doInBackground(MoviesItem... detailMovieResponses) {
            favMovieDao.insert(detailMovieResponses[0]);
            return null;
        }
    }

    private static class InsertTVAsycTask extends AsyncTask<TVShowsItem, Void, Void> {
        private FavMovieDao favMovieDao;

        private InsertTVAsycTask(FavMovieDao favMovieDao) {
            this.favMovieDao = favMovieDao;
        }

        @Override
        protected Void doInBackground(TVShowsItem... tvShowsItems) {
            favMovieDao.insertTv(tvShowsItems[0]);
            return null;
        }
    }


    private static class DeleteMovieAsycTask extends AsyncTask<Integer, Void, Void>{
        private FavMovieDao favMovieDao;

        private DeleteMovieAsycTask(FavMovieDao favMovieDao){
            this.favMovieDao = favMovieDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            favMovieDao.deleteByid(integers[0]);
            return null;
        }
    }

    private static class DeleteTvAsycTask extends AsyncTask<Integer, Void, Void>{
        private FavMovieDao favMovieDao;

        private DeleteTvAsycTask(FavMovieDao favMovieDao){
            this.favMovieDao = favMovieDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            favMovieDao.deleteTvByid(integers[0]);
            return null;
        }
    }
}
