package com.dicoding.moviecatalogsubmission.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.dicoding.moviecatalogsubmission.database.FavDatabase;
import com.dicoding.moviecatalogsubmission.database.FavMovieDao;
import com.dicoding.moviecatalogsubmission.fragment.TvListFragment;
import com.dicoding.moviecatalogsubmission.model.modelAPI.DetailMovieResponse;
import com.dicoding.moviecatalogsubmission.model.modelAPI.MoviesItem;
import com.dicoding.moviecatalogsubmission.model.modelAPI.TVShowsItem;

import java.util.List;

public class FavoriteRepository {
    private FavMovieDao favMovieDao;
    private LiveData<List<MoviesItem>> listLiveData;
    private LiveData<List<TVShowsItem>> listTvLiveData;
    private FavDatabase database;

    public FavoriteRepository(Application application) {
        FavDatabase favDatabase = FavDatabase.getInstance(application);
        favMovieDao = favDatabase.favMovieDao();
        listLiveData = favMovieDao.getAllMovieFav();
        listTvLiveData = favMovieDao.getAllTvFav();
    }

    public LiveData<List<MoviesItem>> getListLiveData(){
        return listLiveData;
    }

    public void insert(MoviesItem moviesItem){
        new InsertMovieAsycTask(favMovieDao).execute(moviesItem);
    }

    public void insertTv(TVShowsItem tvShowsItem){
        new InsertTVAsycTask(favMovieDao).execute(tvShowsItem);
    }

    public LiveData<List<TVShowsItem>> getListTvLiveData(){
        return listTvLiveData;
    }

 /*   public void selectbyId(Integer id){
        new SelectByIdAsycTask(favMovieDao).execute(id);
    }
*/
    public LiveData<MoviesItem> selectedByid(int id){
        return database.favMovieDao().selectedById(id);
    }

    private static class InsertMovieAsycTask extends AsyncTask<MoviesItem, Void, Void>{
        private FavMovieDao favMovieDao;

        private InsertMovieAsycTask(FavMovieDao favMovieDao){
            this.favMovieDao = favMovieDao;
        }

        @Override
        protected Void doInBackground(MoviesItem... detailMovieResponses) {
            favMovieDao.insert(detailMovieResponses[0]);
            return null;
        }
    }

    private static class InsertTVAsycTask extends AsyncTask<TVShowsItem, Void, Void>{
        private FavMovieDao favMovieDao;

        private InsertTVAsycTask(FavMovieDao favMovieDao){
            this.favMovieDao = favMovieDao;
        }

        @Override
        protected Void doInBackground(TVShowsItem... tvShowsItems) {
            favMovieDao.insertTv(tvShowsItems[0]);
            return null;
        }
    }

/*    private static class SelectByIdAsycTask extends AsyncTask<Integer, Void, Void>{
        private FavMovieDao favMovieDao;

        private SelectByIdAsycTask(FavMovieDao favMovieDao){
            this.favMovieDao = favMovieDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            favMovieDao.selectedById(integers[0]);
            return null;
        }
    }*/

 /*   private static class DeleteMovieAsycTask extends AsyncTask<Void, Void, Void>{
        private FavMovieDao favMovieDao;

        private DeleteMovieAsycTask(FavMovieDao favMovieDao){
            this.favMovieDao = favMovieDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            favMovieDao.deleteByid(0);
            return null;
        }
    }*/
}
