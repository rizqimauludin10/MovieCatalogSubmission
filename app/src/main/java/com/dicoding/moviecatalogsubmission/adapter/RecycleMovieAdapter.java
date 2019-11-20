package com.dicoding.moviecatalogsubmission.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dicoding.moviecatalogsubmission.DetailActivity;
import com.dicoding.moviecatalogsubmission.MainActivity;
import com.dicoding.moviecatalogsubmission.R;
import com.dicoding.moviecatalogsubmission.model.Movie;

import java.util.List;

import static android.view.View.*;

public class RecycleMovieAdapter extends RecyclerView.Adapter<RecycleMovieAdapter.MovieHolder> {

    List<Movie> movieList;
    Context context;

    public RecycleMovieAdapter(Context context, List<Movie> movieList1){
        this.context = context;
        this.movieList = movieList1;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        final MovieHolder movieHolder = new MovieHolder(view);
        return movieHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, final int position) {
        final Movie movie = movieList.get(position);
        Glide.with(context).load(movie.getMoviePoster()).into(holder.ivPoster);
        holder.tvTittle.setText(movie.getMovieTittle());
        holder.tvDesc.setText(movie.getMovieDesc());
        holder.tvRate.setText(movie.getMovieRate());
        holder.itemClick.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_MOVIE, movieList.get(position));
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
        private LinearLayout itemClick;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.iv_moviePoster);
            tvTittle = itemView.findViewById(R.id.tv_movieTittle);
            tvDesc = itemView.findViewById(R.id.tv_movieDesc);
            tvRate = itemView.findViewById(R.id.tv_movieRate);
            itemClick = itemView.findViewById(R.id.itemClick);
        }
    }
}
