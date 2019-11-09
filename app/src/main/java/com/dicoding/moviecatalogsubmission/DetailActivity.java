package com.dicoding.moviecatalogsubmission;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.dicoding.moviecatalogsubmission.model.Movie;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        if (movie!=null) {
            TextView tvTittleExtra = findViewById(R.id.tv_tittleDetail);
            tvTittleExtra.setText(movie.getMovieTittle());

            TextView tvDescExtra = findViewById(R.id.tv_descDetail);
            tvDescExtra.setText(movie.getMovieDesc());
        }


    }
}
