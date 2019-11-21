package com.dicoding.moviecatalogsubmission;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dicoding.moviecatalogsubmission.model.Movie;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        collapsingToolbarLayout = findViewById(R.id.collapse);
        appBarLayout = findViewById(R.id.appbar);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        if (movie!=null) {
            ImageView ivPosterExtra = findViewById(R.id.iv_posterDetail);
            Glide.with(this)
                    .load(movie.getMoviePoster())
                    .transform(new BlurTransformation(5,3))
                    .into(ivPosterExtra);

            ImageView ivPosterExtra2 = findViewById(R.id.iv_posterDetail2);
            Glide.with(this)
                    .load(movie.getMoviePoster())
                    .into(ivPosterExtra2);

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
