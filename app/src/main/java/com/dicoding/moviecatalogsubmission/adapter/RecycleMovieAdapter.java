package com.dicoding.moviecatalogsubmission.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.dicoding.moviecatalogsubmission.Detail2Activity;
import com.dicoding.moviecatalogsubmission.DetailActivity4;
import com.dicoding.moviecatalogsubmission.R;
import com.dicoding.moviecatalogsubmission.model.modelAPI.MoviesItem;
import com.dicoding.moviecatalogsubmission.model.modelAPI.GenresItem;
import com.dicoding.moviecatalogsubmission.utils.SharedPrefManager;

import java.util.List;

import static android.view.View.OnClickListener;

public class RecycleMovieAdapter extends RecyclerView.Adapter<RecycleMovieAdapter.MovieHolder> {

    List<MoviesItem> movieList;
    List<GenresItem> genresItemList;
    Context context;
    String imagePath = "https://image.tmdb.org/t/p/w500";
    Integer id;

    public RecycleMovieAdapter(Context context, List<MoviesItem> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public RecycleMovieAdapter.MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        final MovieHolder movieHolder = new MovieHolder(view);
        return movieHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, final int position) {
        final MoviesItem movie = movieList.get(position);

        float voteAverage = ((movie.getVoteAverage()*5) / 10);
        Glide.with(context)
                .load(imagePath + movie.getPosterPath())
                .error(R.drawable.poster_bohemian)
                .transition(DrawableTransitionOptions.withCrossFade(800))
                .into(holder.ivPoster);
        holder.tvTittle.setText(movie.getTitle());
        holder.tvDesc.setText(movie.getOverview());
        holder.tvDate.setText(movie.getReleaseDate());
        holder.tvRate.setText(String.valueOf(movie.getVoteAverage()));
        holder.ratingBar.setRating(voteAverage);


        holder.itemClick.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                id = movie.getId();
                Intent intent = new Intent(context, Detail2Activity.class);
                //intent.putExtra(Detail2Activity.EXTRA_MOVIE2, movieList.get(position));

                //Log.e("Detail Movie", "Movie Detail Id= " + id);
                intent.putExtra("idMovies", id);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        private ImageView ivPoster;
        private TextView tvTittle;
        private TextView tvDesc;
        private TextView tvRate, tvDate;
        private RatingBar ratingBar;
        private LinearLayout itemClick;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            Typeface latoBlack = Typeface.createFromAsset(context.getAssets(), "font/latoblack.ttf");
            Typeface latoBold = Typeface.createFromAsset(context.getAssets(), "font/latobold.ttf");
            Typeface latoRegular = Typeface.createFromAsset(context.getAssets(), "font/latoregular.ttf");

            ivPoster = itemView.findViewById(R.id.iv_moviePoster);
            tvTittle = itemView.findViewById(R.id.tv_movieTittle);
            tvDesc = itemView.findViewById(R.id.tv_movieDesc);
            tvDate = itemView.findViewById(R.id.tv_movieDate);
            tvRate = itemView.findViewById(R.id.tv_movieRate);
            ratingBar = itemView.findViewById(R.id.ratingBar2);
            itemClick = itemView.findViewById(R.id.itemClick);

            tvTittle.setTypeface(latoBlack);
            tvDesc.setTypeface(latoRegular);
            tvDate.setTypeface(latoRegular);
            tvRate.setTypeface(latoBold);
        }
    }
}
