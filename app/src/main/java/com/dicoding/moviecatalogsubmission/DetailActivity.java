package com.dicoding.moviecatalogsubmission;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dicoding.moviecatalogsubmission.model.Movie;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        if (movie!=null) {
            ImageView ivPosterExtra = findViewById(R.id.iv_posterDetail);
            Glide.with(this).load(movie.getMoviePoster()).into(ivPosterExtra);

            String text = movie.getMovieTittle() + " " + movie.getMovieDate();

            TextView tvTittleExtra = findViewById(R.id.tv_tittleDetail);
            tvTittleExtra.setText(text);

            /*TextView tvDateExtra = findViewById(R.id.tv_dateDetail);
            tvDateExtra.setText(movie.getMovieDate());*/

            TextView tvRateExtra = findViewById(R.id.tv_rateDetail);
            tvRateExtra.setText(movie.getMovieRate());

            TextView tvDescExtra = findViewById(R.id.tv_descDetail);
            tvDescExtra.setText(movie.getMovieDesc());
        }


    }
}
