package com.dicoding.moviecatalogsubmission.adapter;

import android.content.Context;
import android.graphics.Typeface;
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
import com.dicoding.moviecatalogsubmission.BuildConfig;
import com.dicoding.moviecatalogsubmission.R;
import com.dicoding.moviecatalogsubmission.model.modelAPI.MoviesItem;
import com.dicoding.moviecatalogsubmission.utils.CustomOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class FavMovieAdapater extends RecyclerView.Adapter<FavMovieAdapater.FavMovieHolder> {

    private List<MoviesItem> movieList;
    private Context context;


    public FavMovieAdapater(Context context, List<MoviesItem> movieList) {
        this.movieList = movieList;
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

        double voteAverage = ((moviesItem.getVoteAverage()*5) / 10);
        String imagePath = BuildConfig.IMAGE_PATH_API_500;
        Glide.with(context)
                .load(imagePath + moviesItem.getPosterPath())
                .transition(DrawableTransitionOptions.withCrossFade(400))
                .into(holder.poster);
        holder.tittle.setText(moviesItem.getTitle());
        holder.date.setText(moviesItem.getReleaseDate());
        holder.rate.setText(String.valueOf(moviesItem.getVoteAverage()));
        holder.ratingBar.setRating((float) voteAverage);
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
       if (movieList == null){
           return 0;
       }
        return movieList.size();
    }

    public void setListMovies(List<MoviesItem> mListMovies) {
        this.movieList = mListMovies;
        notifyDataSetChanged();
    }

    class FavMovieHolder extends RecyclerView.ViewHolder {
        private ImageView poster;
        private TextView tittle;
        private TextView date;
        private TextView rate;
        private LinearLayout itemClick;
        private RatingBar ratingBar;

        public FavMovieHolder(@NonNull View itemView) {
            super(itemView);

            Typeface latoBlack = Typeface.createFromAsset(context.getAssets(), "font/latoblack.ttf");
            Typeface latoBold = Typeface.createFromAsset(context.getAssets(), "font/latobold.ttf");
            Typeface latoRegular = Typeface.createFromAsset(context.getAssets(), "font/latoregular.ttf");

            poster = itemView.findViewById(R.id.iv_movieFavPoster);
            tittle = itemView.findViewById(R.id.tv_movieFavTittle);
            date = itemView.findViewById(R.id.tv_movieFavDate);
            itemClick = itemView.findViewById(R.id.favmovieClick);
            ratingBar = itemView.findViewById(R.id.rbFavMv);
            rate = itemView.findViewById(R.id.tv_movieRateFav);

            tittle.setTypeface(latoBlack);
            date.setTypeface(latoRegular);
            rate.setTypeface(latoBold);
        }
    }
}
