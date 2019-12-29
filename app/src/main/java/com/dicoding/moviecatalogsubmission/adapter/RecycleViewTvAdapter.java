package com.dicoding.moviecatalogsubmission.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.dicoding.moviecatalogsubmission.DetailActivity;
import com.dicoding.moviecatalogsubmission.R;
import com.dicoding.moviecatalogsubmission.model.modelAPI.TVShowsItem;

import java.util.List;

public class RecycleViewTvAdapter extends RecyclerView.Adapter<RecycleViewTvAdapter.TvHolder> {

    private List<TVShowsItem> tvShowList;
    private Context context;

    public RecycleViewTvAdapter(Context context, List<TVShowsItem> tvShowList) {
        this.context = context;
        this.tvShowList = tvShowList;
    }

    @NonNull
    @Override
    public TvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_tv, parent, false);
        return new TvHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvHolder holder, final int position) {
        final TVShowsItem tvShow = tvShowList.get(position);
        float voteAverage = ((tvShow.getVoteAverage()*5) / 10);
        String imagePath = BuildConfig.IMAGE_PATH_API_500;
        Glide.with(context)
                .load(imagePath + tvShow.getPosterPath())
                .transition(DrawableTransitionOptions.withCrossFade(800))
                .into(holder.ivPoster);
        holder.tvTittle.setText(tvShow.getName());
        holder.tvDesc.setText(tvShow.getOverview());
        holder.tvDate.setText(tvShow.getFirstAirDate());
        holder.tvRate.setText(String.valueOf(tvShow.getVoteAverage()));
        holder.ratingBar.setRating(voteAverage);
        holder.itemClick2.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_MOVIE, tvShowList.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return tvShowList.size();
    }

    class TvHolder extends RecyclerView.ViewHolder {

        private ImageView ivPoster;
        private TextView tvTittle;
        private TextView tvDesc, tvDate;
        private TextView tvRate;
        private RatingBar ratingBar;
        private LinearLayout itemClick2;

        TvHolder(@NonNull View itemView) {
            super(itemView);
            Typeface latoBlack = Typeface.createFromAsset(context.getAssets(), "font/latoblack.ttf");
            Typeface latoBold = Typeface.createFromAsset(context.getAssets(), "font/latobold.ttf");
            Typeface latoRegular = Typeface.createFromAsset(context.getAssets(), "font/latoregular.ttf");

            ivPoster = itemView.findViewById(R.id.iv_tvPoster);
            tvTittle = itemView.findViewById(R.id.tv_tvTittle);
            tvDesc = itemView.findViewById(R.id.tv_tvDesc);
            tvDate = itemView.findViewById(R.id.tv_tvDate);
            tvRate = itemView.findViewById(R.id.tv_tvRate);
            ratingBar = itemView.findViewById(R.id.ratingBar3);
            itemClick2 = itemView.findViewById(R.id.itemClick4);

            tvTittle.setTypeface(latoBlack);
            tvDate.setTypeface(latoRegular);
            tvDesc.setTypeface(latoRegular);
            tvRate.setTypeface(latoBold);
        }
    }
}
