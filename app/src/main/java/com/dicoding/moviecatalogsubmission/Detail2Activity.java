package com.dicoding.moviecatalogsubmission;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.dicoding.moviecatalogsubmission.apihelper.BaseAPIService;
import com.dicoding.moviecatalogsubmission.apihelper.UtilsAPI;
import com.dicoding.moviecatalogsubmission.database.DatabaseContract;
import com.dicoding.moviecatalogsubmission.database.FavoriteHelper;
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
    private Button btFav;
    private ImageView ivPosterDetail, ivBacdropDetail;
    private TextView tvMovieTittle, tvOverview, tvDateDetail, tvRatingBarDetail;
    RatingBar ratingBar;
    private Cursor cursor;
    private FavoriteHelper favoriteHelper;
    private boolean favorite;
    private DetailMovieResponse detailMovieResponse;
    DateFormated dateFormated;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);

        dateFormated = new DateFormated(this);

        baseAPIService = UtilsAPI.getApiService();

        ivPosterDetail = findViewById(R.id.tv_mvPosterDetail);
        ivBacdropDetail = findViewById(R.id.iv_movieBackdrop);
        tvMovieTittle = findViewById(R.id.tv_mvTittleDetail);
        tvOverview = findViewById(R.id.tv_mvOverviewDecDetail);
        tvDateDetail = findViewById(R.id.tv_mvDateDetail);
        tvRatingBarDetail = findViewById(R.id.tv_mvRatingScore);
        ratingBar = findViewById(R.id.ratingBar_mvDetail);
        tvRuntime = findViewById(R.id.tv_mvRuntimeDetail);
        tvGenres = findViewById(R.id.tv_mvGenreDetail);
        btFav = findViewById(R.id.bt_addMvFav);
        ImageView ivBackHome = findViewById(R.id.backmv);


        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        favoriteHelper = FavoriteHelper.getInstance(this);
        favoriteHelper.open();


        MoviesItem moviesItem = getIntent().getParcelableExtra(EXTRA_MOVIE2);

        assert moviesItem != null;
        idDetail = moviesItem.getId();


        getDetailMovies();


        ivBackHome.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        btFav.setOnClickListener(v -> {
            favorite = true;
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseContract.MovieColumns.MOVIE_ID, detailMovieResponse.getId());
            contentValues.put(DatabaseContract.MovieColumns.TITTLE, detailMovieResponse.getTitle());
            contentValues.put(DatabaseContract.MovieColumns.POSTER_PATH, detailMovieResponse.getPosterPath());
            contentValues.put(DatabaseContract.MovieColumns.OVERVIEW, detailMovieResponse.getOverview());
            contentValues.put(DatabaseContract.MovieColumns.VOTE_AVERAGE, detailMovieResponse.getVoteAverage());
            contentValues.put(DatabaseContract.MovieColumns.RELEASE_DATE, detailMovieResponse.getReleaseDate());

            favoriteHelper.insert(detailMovieResponse);

            Toast.makeText(this, "Masuk Ke Database", Toast.LENGTH_SHORT).show();
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
                            detailMovieResponse = response.body();

                            int runtime = detailMovieResponse.getRuntime();
                            idDetail = detailMovieResponse.getId();


                            int hours = runtime / 60;
                            int minutes = runtime % 60;

                            tvRuntime.setText((hours + "h" + " " + minutes + "min"));

                            List<GenresItem> genresItemList = detailMovieResponse.getGenres();


                            for (int i = 0; i < genresItemList.size(); i++) {
                                String getGenres = detailMovieResponse.getGenres().get(i).getName();
                                tvGenres.append(getGenres + " | ");
                                Log.e("Genres", "Movie Genres =>  " + detailMovieResponse.getGenres().get(i).getName());

                            }

                            idDetail = response.body().getId();

                            String imagePath = BuildConfig.IMAGE_PATH_API;
                            Glide.with(getApplicationContext())
                                    .load(imagePath + detailMovieResponse.getBackdropPath())
                                    .transition(DrawableTransitionOptions.withCrossFade(200))
                                    .into(ivBacdropDetail);

                            Glide.with(getApplicationContext())
                                    .load(imagePath + detailMovieResponse.getPosterPath())
                                    .transition(DrawableTransitionOptions.withCrossFade(200))
                                    .into(ivPosterDetail);

                            tvMovieTittle.setText(detailMovieResponse.getTitle());
                            tvOverview.setText(detailMovieResponse.getOverview());
                            String date = detailMovieResponse.getReleaseDate();
                            tvDateDetail.setText(dateFormated.setDateFormat(date));
                            tvRatingBarDetail.setText(String.valueOf(detailMovieResponse.getVoteAverage()));
                            double voteAverage = ((detailMovieResponse.getVoteAverage() * 5) / 10);
                            ratingBar.setRating((float) voteAverage);
                        }

                    }

                    @Override
                    public void onFailure(@NotNull Call<DetailMovieResponse> call, @NotNull Throwable t) {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        favoriteHelper.close();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
