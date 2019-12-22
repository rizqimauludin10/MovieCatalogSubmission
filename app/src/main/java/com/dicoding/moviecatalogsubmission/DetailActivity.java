package com.dicoding.moviecatalogsubmission;

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
import com.dicoding.moviecatalogsubmission.model.modelAPI.GenresItem;
import com.dicoding.moviecatalogsubmission.model.modelAPI.TVDetailResponse;
import com.dicoding.moviecatalogsubmission.model.modelAPI.TVShowsItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    private BaseAPIService baseAPIService;
    Integer idTvDetail;
    ImageView ivPoster, ivBackdrop;
    TextView tvTittle, tvDate, tvGenre, tvRuntime, tvRate, tvOverview;
    RatingBar tvRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        baseAPIService = UtilsAPI.getApiService();

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ivPoster = findViewById(R.id.tv_tvPosterDetail);
        ivBackdrop = findViewById(R.id.iv_tvBackdrop);
        tvTittle = findViewById(R.id.tv_tvTittleDetail);
        tvDate = findViewById(R.id.tv_tvDateDetail);
        tvGenre = findViewById(R.id.tv_tvGenreDetail);
        tvRuntime = findViewById(R.id.tv_tvRuntimeDetail);
        tvRatingBar = findViewById(R.id.ratingBar_tvDetail);
        tvRate = findViewById(R.id.tv_tvRatingScore);
        tvOverview = findViewById(R.id.tv_tvOverviewDecDetail);

        TVShowsItem tvShowsItem = getIntent().getParcelableExtra(EXTRA_MOVIE);

        String imagePath = "https://image.tmdb.org/t/p/w780";
        assert tvShowsItem != null;

        idTvDetail = tvShowsItem.getId();
        Glide.with(this)
                .load(imagePath + tvShowsItem.getBackdropPath())
                .transition(DrawableTransitionOptions.withCrossFade(800))
                .into(ivBackdrop);

        Glide.with(this)
                .load(imagePath + tvShowsItem.getPosterPath())
                .transition(DrawableTransitionOptions.withCrossFade(800))
                .into(ivPoster);

        tvTittle.setText(tvShowsItem.getName());
        tvDate.setText(tvShowsItem.getFirstAirDate());
        float voteAverage = ((tvShowsItem.getVoteAverage() * 5) / 10);
        tvRatingBar.setRating(voteAverage);
        tvRate.setText(String.valueOf(tvShowsItem.getVoteAverage()));
        tvOverview.setText(tvShowsItem.getOverview());

        getTvDetail();
    }

    public void getTvDetail() {
        String api_key = "54d3f8cecc84d7140e160061e4602e45";
        baseAPIService.getDetailTv(
                idTvDetail,
                api_key)
                .enqueue(new Callback<TVDetailResponse>() {
                    @Override
                    public void onResponse(Call<TVDetailResponse> call, Response<TVDetailResponse> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;

                            TVDetailResponse tvDetailResponse = response.body();

                            List<Integer> runtime = response.body().getEpisodeRunTime();
                            //tvRuntime.setText((CharSequence) runtime);

                            List<GenresItem> genresItemList = response.body().getGenres();

                            for (int i=0; i< genresItemList.size(); i++) {
                                String getGenres = tvDetailResponse.getGenres().get(i).getName();
                                tvGenre.append(getGenres + " | ");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<TVDetailResponse> call, Throwable t) {

                    }
                });

    }
}
