package com.dicoding.moviecatalogsubmission;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.dicoding.moviecatalogsubmission.model.modelAPI.GenresItem;
import com.dicoding.moviecatalogsubmission.model.modelAPI.TVDetailResponse;
import com.dicoding.moviecatalogsubmission.model.modelAPI.TVShowsItem;
import com.dicoding.moviecatalogsubmission.utils.DateFormated;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    private BaseAPIService baseAPIService;
    Integer idTvDetail;
    ImageView ivPoster, ivBackdrop, ivBack;
    TextView tvTittle, tvDate, tvGenre, tvRate, tvOverview;
    RatingBar tvRatingBar;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        DateFormated dateFormated = new DateFormated(this);
        baseAPIService = UtilsAPI.getApiService();

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ivPoster = findViewById(R.id.tv_tvPosterDetail);
        ivBackdrop = findViewById(R.id.iv_tvBackdrop);
        tvTittle = findViewById(R.id.tv_tvTittleDetail);
        tvDate = findViewById(R.id.tv_tvDateDetail);
        tvGenre = findViewById(R.id.tv_tvGenreDetail);
        tvRatingBar = findViewById(R.id.ratingBar_tvDetail);
        tvRate = findViewById(R.id.tv_tvRatingScore);
        tvOverview = findViewById(R.id.tv_tvOverviewDecDetail);
        ivBack = findViewById(R.id.backHomeTv);

        TVShowsItem tvShowsItem = getIntent().getParcelableExtra(EXTRA_MOVIE);

        String imagePath = BuildConfig.IMAGE_PATH_API;
        assert tvShowsItem != null;

        idTvDetail = tvShowsItem.getId();
        Glide.with(this)
                .load(imagePath + tvShowsItem.getBackdropPath())
                .transition(DrawableTransitionOptions.withCrossFade(200))
                .into(ivBackdrop);

        Glide.with(this)
                .load(imagePath + tvShowsItem.getPosterPath())
                .transition(DrawableTransitionOptions.withCrossFade(200))
                .into(ivPoster);

        tvTittle.setText(tvShowsItem.getName());
        date = tvShowsItem.getFirstAirDate();
        tvDate.setText(dateFormated.setDateFormat(date));
        float voteAverage = ((tvShowsItem.getVoteAverage() * 5) / 10);
        tvRatingBar.setRating(voteAverage);
        tvRate.setText(String.valueOf(tvShowsItem.getVoteAverage()));
        tvOverview.setText(tvShowsItem.getOverview());

        getTvDetail();

        ivBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    public void getTvDetail() {
        String api_key = BuildConfig.TMDB_API_KEY;
        baseAPIService.getDetailTv(
                idTvDetail,
                api_key)
                .enqueue(new Callback<TVDetailResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<TVDetailResponse> call, @NotNull Response<TVDetailResponse> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;

                            TVDetailResponse tvDetailResponse = response.body();

                            List<GenresItem> genresItemList = response.body().getGenres();

                            for (int i = 0; i < genresItemList.size(); i++) {
                                String getGenres = tvDetailResponse.getGenres().get(i).getName();
                                tvGenre.append(getGenres + " | ");
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<TVDetailResponse> call, @NotNull Throwable t) {

                    }
                });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
