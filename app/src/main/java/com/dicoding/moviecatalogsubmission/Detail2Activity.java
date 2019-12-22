package com.dicoding.moviecatalogsubmission;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.dicoding.moviecatalogsubmission.apihelper.BaseAPIService;
import com.dicoding.moviecatalogsubmission.apihelper.UtilsAPI;
import com.dicoding.moviecatalogsubmission.model.modelAPI.DetailMovieResponse;
import com.dicoding.moviecatalogsubmission.model.modelAPI.GenresItem;
import com.dicoding.moviecatalogsubmission.model.modelAPI.MoviesItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Detail2Activity extends AppCompatActivity {
    public static final String EXTRA_MOVIE2 = "extra_movie2";
    private ImageView back;
    private BaseAPIService baseAPIService;
    private ImageView ivPosterDetail, ivBacdropDetail;
    private TextView tvMovieTittle, tvOverview, tvDateDetail, tvRatingBarDetail, tvRuntime, tvGenres;
    private RatingBar ratingBar;
    Integer idDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);

        baseAPIService = UtilsAPI.getApiService();

        ivPosterDetail = findViewById(R.id.tv_tvPosterDetail);
        ivBacdropDetail = findViewById(R.id.iv_movieBackdrop);
        tvMovieTittle = findViewById(R.id.tv_tvTittleDetail);
        tvOverview = findViewById(R.id.tv_tvOverviewDecDetail);
        tvDateDetail = findViewById(R.id.tv_tvDateDetail);
        tvRatingBarDetail = findViewById(R.id.tv_tvRatingScore);
        ratingBar = findViewById(R.id.ratingBar_tvDetail);
        tvRuntime = findViewById(R.id.tv_tvRuntimeDetail);
        tvGenres = findViewById(R.id.tv_tvGenreDetail);


        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        MoviesItem moviesItem = getIntent().getParcelableExtra(EXTRA_MOVIE2);

        assert moviesItem != null;
        idDetail = moviesItem.getId();

        String imagePath = "https://image.tmdb.org/t/p/w780";
        Glide.with(this)
                .load(imagePath + moviesItem.getBackdropPath())
                .transition(DrawableTransitionOptions.withCrossFade(800))
                .into(ivBacdropDetail);

        Glide.with(this)
                .load(imagePath + moviesItem.getPosterPath())
                .transition(DrawableTransitionOptions.withCrossFade(800))
                .into(ivPosterDetail);

        tvMovieTittle.setText(moviesItem.getTitle());
        tvOverview.setText(moviesItem.getOverview());
        tvDateDetail.setText(moviesItem.getReleaseDate());
        tvRatingBarDetail.setText(String.valueOf(moviesItem.getVoteAverage()));
        float voteAverage = ((moviesItem.getVoteAverage()*5) / 10);
        ratingBar.setRating(voteAverage);

        getDetailMovies();
    }

    public void getDetailMovies() {
        String api_key = "54d3f8cecc84d7140e160061e4602e45";
        baseAPIService.getDetailMovie(
                idDetail,
                api_key)
                .enqueue(new Callback<DetailMovieResponse>() {
                    @Override
                    public void onResponse(Call<DetailMovieResponse> call, Response<DetailMovieResponse> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            int runtime = response.body().getRuntime();
                            tvRuntime.setText(String.valueOf(runtime));


                            DetailMovieResponse m = response.body();
                            List<GenresItem> genresItemList = response.body().getGenres();


                            for (int i=0; i< genresItemList.size(); i++) {
                                String getGenres = m.getGenres().get(i).getName();
                                tvGenres.append(getGenres + " | ");
                                //tvGenres.setBackgroundResource(R.drawable.genres);
                                Log.e("Genres", "Movie Genres =>  " + m.getGenres().get(i).getName());

                            }


                        }

                    }

                    @Override
                    public void onFailure(Call<DetailMovieResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
