package com.dicoding.moviecatalogsubmission.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.dicoding.moviecatalogsubmission.Detail2Activity;
import com.dicoding.moviecatalogsubmission.R;
import com.dicoding.moviecatalogsubmission.model.ResultMovies;

import java.util.List;

import static android.view.View.OnClickListener;

public class RecycleMovieAdapter extends RecyclerView.Adapter<RecycleMovieAdapter.MovieHolder> {

    List<ResultMovies> movieList;
    Context context;
    String imagePath = "https://image.tmdb.org/t/p/w500";

    public RecycleMovieAdapter(Context context, List<ResultMovies> movieList) {
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
        final ResultMovies movie = movieList.get(position);
        Glide.with(context).load(imagePath + movie.getPosterPath()).into(holder.ivPoster);
        holder.tvTittle.setText(movie.getTitle());
        holder.tvDesc.setText(movie.getOverview());
        //holder.tvRate.setText(movie.getVoteAverage());
        //holder.ratingBar.setRating(movie.getVoteAverage());
        holder.itemClick.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Detail2Activity.class);
                //intent.putExtra(Detail2Activity.EXTRA_MOVIE2, movieList.get(position));
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
        private TextView tvRate;
        private RatingBar ratingBar;
        private LinearLayout itemClick;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.iv_moviePoster);
            tvTittle = itemView.findViewById(R.id.tv_movieTittle);
            tvDesc = itemView.findViewById(R.id.tv_movieDesc);
            tvRate = itemView.findViewById(R.id.tv_movieRate);
            ratingBar = itemView.findViewById(R.id.ratingBar2);
            itemClick = itemView.findViewById(R.id.itemClick);
        }
    }
}
