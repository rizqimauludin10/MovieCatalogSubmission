package com.dicoding.moviecatalogsubmission;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.dicoding.moviecatalogsubmission.model.Movie;

public class Detail2Activity extends AppCompatActivity {
    public static final String EXTRA_MOVIE2 = "extra_movie2";
    private ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);

        Window window =  getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

/*
        ImageView ivPosterExtra2 = findViewById(R.id.imageView2);
        TextView tvTittleExtra = findViewById(R.id.textView);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        TextView tvRateExtra = findViewById(R.id.textView3);
        TextView tvDescExtra = findViewById(R.id.textView2);
        back = findViewById(R.id.back_detail);


        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE2);

        Log.d("Detail Extra", "Parcelable" + " " + "data Movie");

        Glide.with(this)
                .load(movie.getMoviePoster())
                .into(ivPosterExtra2);
        String text = movie.getMovieTittle() + " " + movie.getMovieDate();
        tvTittleExtra.setText(text);
        ratingBar.setRating(movie.getMovieRate());
        tvRateExtra.setText(movie.getMovieRate2());
        tvDescExtra.setText(movie.getMovieDesc());
        //Log.d("Detail Extra", "ParcelableTest" +" "+ tvShow.getTvTittle());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Detail2Activity.this, Main2Activity.class);
                startActivity(i);
            }
        });*/


    }
}
