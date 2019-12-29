package com.dicoding.moviecatalogsubmission;

import android.content.Context;
import android.content.Intent;
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
import com.dicoding.moviecatalogsubmission.utils.DateFormated;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Detail2Activity extends AppCompatActivity {
    public static final String EXTRA_MOVIE2 = "extra_movie2";
    private BaseAPIService baseAPIService;
    private TextView tvRuntime;
    private TextView tvGenres;
    private Integer idDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);

        DateFormated dateFormated = new DateFormated(this);

        baseAPIService = UtilsAPI.getApiService();

        ImageView ivPosterDetail = findViewById(R.id.tv_mvPosterDetail);
        ImageView ivBacdropDetail = findViewById(R.id.iv_movieBackdrop);
        TextView tvMovieTittle = findViewById(R.id.tv_mvTittleDetail);
        TextView tvOverview = findViewById(R.id.tv_mvOverviewDecDetail);
        TextView tvDateDetail = findViewById(R.id.tv_mvDateDetail);
        TextView tvRatingBarDetail = findViewById(R.id.tv_mvRatingScore);
        RatingBar ratingBar = findViewById(R.id.ratingBar_mvDetail);
        tvRuntime = findViewById(R.id.tv_mvRuntimeDetail);
        tvGenres = findViewById(R.id.tv_mvGenreDetail);
        ImageView ivBackHome = findViewById(R.id.backmv);


        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        MoviesItem moviesItem = getIntent().getParcelableExtra(EXTRA_MOVIE2);

        assert moviesItem != null;
        idDetail = moviesItem.getId();

        String imagePath = BuildConfig.IMAGE_PATH_API;
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
        String date = moviesItem.getReleaseDate();
        tvDateDetail.setText(dateFormated.setDateFormat(date));
        tvRatingBarDetail.setText(String.valueOf(moviesItem.getVoteAverage()));
        float voteAverage = ((moviesItem.getVoteAverage() * 5) / 10);
        ratingBar.setRating(voteAverage);

        getDetailMovies();


        ivBackHome.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    public void getDetailMovies() {
        String api_key = BuildConfig.TMDB_API_KEY;
        baseAPIService.getDetailMovie(
                idDetail,
                api_key)
                .enqueue(new Callback<DetailMovieResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<DetailMovieResponse> call, @NotNull Response<DetailMovieResponse> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            int runtime = response.body().getRuntime();

                            int hours = runtime / 60;
                            int minutes = runtime % 60;

                            tvRuntime.setText((hours + "h" + " " + minutes + "min"));

                            DetailMovieResponse m = response.body();
                            List<GenresItem> genresItemList = response.body().getGenres();


                            for (int i = 0; i < genresItemList.size(); i++) {
                                String getGenres = m.getGenres().get(i).getName();
                                tvGenres.append(getGenres + " | ");
                                Log.e("Genres", "Movie Genres =>  " + m.getGenres().get(i).getName());

                            }
                        }

                    }

                    @Override
                    public void onFailure(@NotNull Call<DetailMovieResponse> call, @NotNull Throwable t) {

                    }
                });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
