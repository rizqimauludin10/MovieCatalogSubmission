package com.dicoding.moviecatalogsubmission.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.dicoding.moviecatalogsubmission.BuildConfig;
import com.dicoding.moviecatalogsubmission.Detail2Activity;
import com.dicoding.moviecatalogsubmission.R;
import com.dicoding.moviecatalogsubmission.model.modelAPI.DetailMovieResponse;
import com.dicoding.moviecatalogsubmission.model.modelAPI.MoviesItem;
import com.dicoding.moviecatalogsubmission.utils.CustomOnItemClickListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FavMovieAdapater extends RecyclerView.Adapter<FavMovieAdapater.FavMovieHolder> {

    private List<MoviesItem> movieList = new ArrayList<>();
    private Context context;

    public FavMovieAdapater(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FavMovieAdapater.FavMovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_favorite, parent, false);
        return new FavMovieAdapater.FavMovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavMovieHolder holder, int position) {
       final MoviesItem moviesItem = movieList.get(position);

        String imagePath = BuildConfig.IMAGE_PATH_API_500;
        /*Glide.with(context)
                .load(imagePath + moviesItem.getPosterPath())
                .transition(DrawableTransitionOptions.withCrossFade(400))
                .into(holder.poster);*/

        holder.tittle.setText(moviesItem.getTitle());
        holder.date.setText(moviesItem.getReleaseDate());
        holder.itemClick.setOnClickListener(new CustomOnItemClickListener(position, (view, position1) -> {
            int id = moviesItem.getId();
            /*Intent intent = new Intent(context, Detail2Activity.class);
            intent.putExtra(Detail2Activity.EXTRA_MOVIE2, position1);
            Log.e("Detail Movie Fav", "Movie Detail Id= " + id);
            context.startActivity(intent);*/
        }));


    }

    @Override
    public int getItemCount() {
      /* if (movieList == null){
           return 0;
       }*/
       return movieList.size();
    }

    public void setListMovies(List<MoviesItem> mListMovies){
        this.movieList = mListMovies;
        notifyDataSetChanged();
    }

/*
    private DetailMovieResponse getItem(int position){
        if (!movieList.moveToPosition(position)){
            throw new IllegalStateException("Position invalid");
        }
        return new DetailMovieResponse(movieList);
    }
*/

    class FavMovieHolder extends RecyclerView.ViewHolder {
        private ImageView poster;
        private TextView tittle;
        private TextView date;
        private LinearLayout itemClick;
        public FavMovieHolder(@NonNull View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.iv_movieFavPoster);
            tittle = itemView.findViewById(R.id.tv_movieFavTittle);
            date = itemView.findViewById(R.id.tv_movieFavDate);
            itemClick = itemView.findViewById(R.id.favmovieClick);
        }
    }
}
