package com.dicoding.moviecatalogsubmission;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.dicoding.moviecatalogsubmission.apihelper.BaseAPIService;
import com.dicoding.moviecatalogsubmission.apihelper.UtilsAPI;
import com.dicoding.moviecatalogsubmission.model.modelAPI.MoviesItem;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Detail2Activity extends AppCompatActivity {
    public static final String EXTRA_MOVIE2 = "extra_movie2";
    private ImageView back;
    private Integer idMovieExtras = 512200;
    private BaseAPIService baseAPIService;
    private ImageView ivPosterDetail, ivBacdropDetail;
    private TextView tvMovieTittle, tvOverview, tvDateDetail, tvRatingBarDetail;
    private RatingBar ratingBar;
    String imagePath = "https://image.tmdb.org/t/p/w780";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);

        baseAPIService = UtilsAPI.getApiService();

        ivPosterDetail = findViewById(R.id.tv_mvPosterDetail);
        ivBacdropDetail = findViewById(R.id.iv_movieBackdrop);
        tvMovieTittle = findViewById(R.id.tv_movieTittleDetail);
        tvOverview = findViewById(R.id.tv_mvOverviewDecDetail);
        tvDateDetail = findViewById(R.id.tv_mvDateDetail);
        tvRatingBarDetail = findViewById(R.id.tv_mvRatingScore);
        ratingBar = findViewById(R.id.ratingBar_mvDetail);


        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        MoviesItem moviesItem = getIntent().getParcelableExtra(EXTRA_MOVIE2);

        assert moviesItem != null;
        Glide.with(this)
                .load(imagePath + moviesItem.getBackdropPath())
                .into(ivBacdropDetail);

        Glide.with(this)
                .load(imagePath + moviesItem.getPosterPath())
                .into(ivPosterDetail);

        tvMovieTittle.setText(moviesItem.getTitle());
        tvOverview.setText(moviesItem.getOverview());
        tvDateDetail.setText(moviesItem.getReleaseDate());
        tvRatingBarDetail.setText(String.valueOf(moviesItem.getVoteAverage()));
        float voteAverage = ((moviesItem.getVoteAverage()*5) / 10);
        ratingBar.setRating(voteAverage);

        //getDetailMovies();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
