package com.dicoding.moviecatalogsubmission;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.dicoding.moviecatalogsubmission.model.TVShow;

public class Detail3Activity extends AppCompatActivity {
    public static final String EXTRA_MOVIE3 = "extra_movie3";
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail3);

        ImageView ivPosterExtra2 = findViewById(R.id.imageView22);
        TextView tvTittleExtra = findViewById(R.id.textVieww);
        RatingBar ratingBar = findViewById(R.id.ratingBarr);
        TextView tvRateExtra = findViewById(R.id.textView33);
        TextView tvDescExtra = findViewById(R.id.textView22);
        back = findViewById(R.id.back_detail);

        TVShow tvShow = getIntent().getParcelableExtra(EXTRA_MOVIE3);

        Glide.with(this)
                .load(tvShow.getTvPoster())
                .into(ivPosterExtra2);
        String text2 = tvShow.getTvTittle() + " " + tvShow.getTvDate();
        tvTittleExtra.setText(text2);
        ratingBar.setRating(tvShow.getTvRate());
        tvDescExtra.setText(tvShow.getTvDesc());
        tvRateExtra.setText(tvShow.getTvRate2());


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Detail3Activity.this, Main2Activity.class);
                startActivity(i);
            }
        });
    }
}
