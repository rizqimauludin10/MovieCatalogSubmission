package com.dicoding.moviecatalogsubmission.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dicoding.moviecatalogsubmission.Repository.FavoriteRepository;
import com.dicoding.moviecatalogsubmission.model.modelAPI.MoviesItem;
import com.dicoding.moviecatalogsubmission.model.modelAPI.TVShowsItem;

import java.util.List;

public class FavMovieViewModel extends AndroidViewModel {
    private FavoriteRepository favoriteRepository;
    private LiveData<List<MoviesItem>> listLiveData;
    private LiveData<List<TVShowsItem>> listTvLiveData;

    public FavMovieViewModel(@NonNull Application application) {
        super(application);
        favoriteRepository = new FavoriteRepository(application);
        listLiveData = favoriteRepository.getListLiveData();
        listTvLiveData = favoriteRepository.getListTvLiveData();
    }

    public void insert(MoviesItem moviesItem){
        favoriteRepository.insert(moviesItem);
    }

    public void insertTv(TVShowsItem tvShowsItem){
        favoriteRepository.insertTv(tvShowsItem);
    }

   /* public void selectById(Integer id){
        //favoriteRepository.selectbyId(id);
        favoriteRepository.selectedByid(id);
    }*/

   public LiveData<MoviesItem> selectById(int id){
       return favoriteRepository.selectedByid(id);
   }


    public LiveData<List<MoviesItem>> getListLiveData() {
        return listLiveData;
    }

    public LiveData<List<TVShowsItem>> getListTvLiveData(){
       return listTvLiveData;
    }
}
