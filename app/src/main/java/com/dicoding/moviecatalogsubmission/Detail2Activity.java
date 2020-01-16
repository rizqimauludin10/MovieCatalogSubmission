package com.dicoding.moviecatalogsubmission;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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
    private ImageButton favIcon;
    private Integer idDetail;
    RatingBar ratingBar;
    private boolean favorite = false;
    private DetailMovieResponse detailMovieResponse;
    DateFormated dateFormated;
    FavMovieViewModel favMovieViewModel;
    MoviesItem moviesItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);

        fullWindow();

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
        favIcon = findViewById(R.id.favIcon);
        ImageView ivBackHome = findViewById(R.id.backmv);


        moviesItem = getIntent().getParcelableExtra(EXTRA_MOVIE2);

        assert moviesItem != null;
        idDetail = moviesItem.getId();

        favMovieViewModel = ViewModelProviders.of(this).get(FavMovieViewModel.class);

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


        //get data dari API
        getDetailMovies();

        check();

        ivBackHome.setOnClickListener(v -> {
            onBackPressed();
        });

        //insert ke database
        favIcon.setOnClickListener(v -> {
            if (!favorite) {
                favMovieViewModel.insert(moviesItem);
                favIcon.setImageResource(R.drawable.ic_favorite);
                Toast.makeText(this, "Menambahkan data ke favorit", Toast.LENGTH_SHORT).show();
            } else {
                favMovieViewModel.deleteId(idDetail);
                favIcon.setImageResource(R.drawable.ic_favorite_border);
                Toast.makeText(this, "Menghapus data favorit", Toast.LENGTH_SHORT).show();
            }
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
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void check() {
        favMovieViewModel.selectById(idDetail).observe(this, moviesItem -> {
            if (moviesItem != null && moviesItem.getId().equals(idDetail)) {
                Log.e("Fav", "Movie Fav Check => Berhasil");
                favIcon.setImageResource(R.drawable.ic_favorite);
                favorite = true;
            } else {
                Log.e("Fav", "Movie Fav Check => Gagal");
            }
        });
    }

    public void fullWindow() {
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

     /*favoriteRepository.selectedByid(idDetail);
        favorite = true;*/
    //Log.e("Fav", "Movie Fav Check =>  " + favorite);
}
