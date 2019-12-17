package com.dicoding.moviecatalogsubmission;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.dicoding.moviecatalogsubmission.model.DetailMoviesViewModel;

public class Detail2Activity extends AppCompatActivity {
    public static final String EXTRA_MOVIE2 = "extra_movie2";
    private ImageView back;
    private DetailMoviesViewModel movieViewModelMovie;
    private Integer idMovieExtras = 512200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);

        Window window =  getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /*Intent intent = new Intent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            idMovieExtras = bundle.getInt("idMovies");
            Log.e("Id", "Idididid" + idMovieExtras);
        }*/



        //getDetailMovies();
    }

/*
    public void getDetailMovies() {
        movieViewModelMovie = ViewModelProviders.of(this).get(DetailMoviesViewModel.class);
        movieViewModelMovie.init();
        movieViewModelMovie.getDetailMovieRepository(
                idMovieExtras)
                .observe(this, detailMovieResponse -> {
                    if (detailMovieResponse != null) {
                        Log.e("Detail Movie", "Movie Tittle  = " + detailMovieResponse.getTitle());
                    }
                });
    }
*/

}
