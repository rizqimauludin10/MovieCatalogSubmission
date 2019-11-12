package com.dicoding.moviecatalogsubmission.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dicoding.moviecatalogsubmission.R;
import com.dicoding.moviecatalogsubmission.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Movie> movies = new ArrayList<>();

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public MovieAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        }

        ViewHolder viewHolder = new ViewHolder(view);
        Movie movie = (Movie) getItem(position);
        viewHolder.bind(movie);
        return view;
    }

    private class ViewHolder {
        private ImageView ivPoster;
        private TextView tvTittle;
        private TextView tvDesc;
        private TextView tvRate;

        ViewHolder(View view) {
            ivPoster = view.findViewById(R.id.iv_moviePoster);
            tvTittle = view.findViewById(R.id.tv_movieTittle);
            tvDesc = view.findViewById(R.id.tv_movieDesc);
            tvRate = view.findViewById(R.id.tv_movieRate);
        }

        void bind(Movie movie) {
            tvTittle.setText(movie.getMovieTittle());
            tvDesc.setText(movie.getMovieDesc());
            tvRate.setText(movie.getMovieRate());
            ivPoster.setImageResource(movie.getMoviePoster());
        }
    }
}
