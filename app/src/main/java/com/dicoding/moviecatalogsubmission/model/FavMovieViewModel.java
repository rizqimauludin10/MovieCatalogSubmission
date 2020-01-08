package com.dicoding.moviecatalogsubmission.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dicoding.moviecatalogsubmission.Repository.FavoriteRepository;
import com.dicoding.moviecatalogsubmission.model.modelAPI.MoviesItem;

import java.util.List;

public class FavMovieViewModel extends AndroidViewModel {
    private FavoriteRepository favoriteRepository;
    private LiveData<List<MoviesItem>> listLiveData;

    public FavMovieViewModel(@NonNull Application application) {
        super(application);
        favoriteRepository = new FavoriteRepository(application);
        listLiveData = favoriteRepository.getListLiveData();
    }

    public void insert(MoviesItem moviesItem){
        favoriteRepository.insert(moviesItem);
    }

   /* public void selectById(Integer id){
        //favoriteRepository.selectbyId(id);
        favoriteRepository.selectedByid(id);
    }*/

   public LiveData<MoviesItem> selectById(Integer id){
       return favoriteRepository.selectedByid(id);
   }


    public LiveData<List<MoviesItem>> getListLiveData() {
        return listLiveData;
    }
}
