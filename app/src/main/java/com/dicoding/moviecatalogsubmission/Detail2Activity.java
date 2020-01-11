package com.dicoding.moviecatalogsubmission;

import android.content.Context;
import android.content.Intent;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.dicoding.moviecatalogsubmission.apihelper.BaseAPIService;
import com.dicoding.moviecatalogsubmission.apihelper.UtilsAPI;
import com.dicoding.moviecatalogsubmission.model.FavMovieViewModel;
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
    private Button btFav;
    private Integer idDetail;
    RatingBar ratingBar;
    private boolean favorite = false;
    private DetailMovieResponse detailMovieResponse;
    DateFormated dateFormated;
    FavMovieViewModel favMovieViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);

        dateFormated = new DateFormated(this);

        baseAPIService = UtilsAPI.getApiService();

        ImageView ivPosterDetail = findViewById(R.id.tv_mvPosterDetail);
        ImageView ivBacdropDetail = findViewById(R.id.iv_movieBackdrop);
        TextView tvMovieTittle = findViewById(R.id.tv_mvTittleDetail);
        TextView tvOverview = findViewById(R.id.tv_mvOverviewDecDetail);
        TextView tvDateDetail = findViewById(R.id.tv_mvDateDetail);
        TextView tvRatingBarDetail = findViewById(R.id.tv_mvRatingScore);
        ratingBar = findViewById(R.id.ratingBar_mvDetail);
        tvRuntime = findViewById(R.id.tv_mvRuntimeDetail);
        tvGenres = findViewById(R.id.tv_mvGenreDetail);
        btFav = findViewById(R.id.bt_addMvFav);
        ImageView ivBackHome = findViewById(R.id.backmv);


        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        favMovieViewModel = ViewModelProviders.of(this).get(FavMovieViewModel.class);

        MoviesItem moviesItem = getIntent().getParcelableExtra(EXTRA_MOVIE2);

        assert moviesItem != null;
        idDetail = moviesItem.getId();

        String imagePath = BuildConfig.IMAGE_PATH_API;
        Glide.with(getApplicationContext())
                .load(imagePath + moviesItem.getBackdropPath())
                .transition(DrawableTransitionOptions.withCrossFade(200))
                .into(ivBacdropDetail);

        Glide.with(getApplicationContext())
                .load(imagePath + moviesItem.getPosterPath())
                .transition(DrawableTransitionOptions.withCrossFade(200))
                .into(ivPosterDetail);

        tvMovieTittle.setText(moviesItem.getTitle());
        tvOverview.setText(moviesItem.getOverview());
        String date = moviesItem.getReleaseDate();
        tvDateDetail.setText(dateFormated.setDateFormat(date));
        tvRatingBarDetail.setText(String.valueOf(moviesItem.getVoteAverage()));
        double voteAverage = ((moviesItem.getVoteAverage() * 5) / 10);
        ratingBar.setRating((float) voteAverage);


        getDetailMovies();


        ivBackHome.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });


        btFav.setOnClickListener(v -> {
            favorite = true;
            //check();
            favMovieViewModel.insert(moviesItem);
            Toast.makeText(this, "Masuk Ke Database", Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "Id" + idDetail, Toast.LENGTH_SHORT).show();
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

                            idDetail = detailMovieResponse.getId();
                            int runtime = detailMovieResponse.getRuntime();


                            int hours = runtime / 60;
                            int minutes = runtime % 60;

                            tvRuntime.setText((hours + "h" + " " + minutes + "min"));

                            List<GenresItem> genresItemList = detailMovieResponse.getGenres();

                            for (int i = 0; i < genresItemList.size(); i++) {
                                String getGenres = detailMovieResponse.getGenres().get(i).getName();
                                tvGenres.append(getGenres + " | ");
                                Log.e("Genres", "Movie Genres =>  " + detailMovieResponse.getGenres().get(i).getName());

                            }
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
    }

    public void check() {
        /*favMovieViewModel.selectById(idDetail).observe(this, new Observer<MoviesItem>() {
            @Override
            public void onChanged(MoviesItem moviesItem) {
                Log.e("Check Id", "Check Id Fav Movie =>" + moviesItem.getId());
            }
        });*/
        String test =  String.valueOf(favMovieViewModel.selectById(idDetail));
        Log.e("Check Id", "Check Id Fav Movie =>" + test);
        //btFav.setEnabled(false);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
