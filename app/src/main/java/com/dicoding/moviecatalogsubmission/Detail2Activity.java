package com.dicoding.moviecatalogsubmission;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Detail2Activity extends AppCompatActivity {
    public static final String EXTRA_MOVIE2 = "extra_movie2";
    private ImageView back;
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



}
