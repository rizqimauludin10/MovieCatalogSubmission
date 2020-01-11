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
import com.dicoding.moviecatalogsubmission.model.modelAPI.TVShowsItem;

import java.util.List;

public class FavTvAdapter extends RecyclerView.Adapter<FavTvAdapter.FavTvHolder> {

    private List<TVShowsItem> tvShowList;
    private Context context;

    public FavTvAdapter(List<TVShowsItem> tvShowList, Context context) {
        this.tvShowList = tvShowList;
        this.context = context;
    }

    @NonNull
    @Override
    public FavTvAdapter.FavTvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv_favorite, parent, false);
        return new FavTvAdapter.FavTvHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavTvHolder holder, int position) {
        final TVShowsItem tvShow = tvShowList.get(position);
        float voteAverage = ((tvShow.getVoteAverage()*5) / 10);
        String imagePath = BuildConfig.IMAGE_PATH_API_500;
        Glide.with(context)
                .load(imagePath + tvShow.getPosterPath())
                .transition(DrawableTransitionOptions.withCrossFade(400))
                .into(holder.ivPoster);
        holder.tvTittle.setText(tvShow.getName());
        holder.tvDate.setText(tvShow.getFirstAirDate());
        holder.tvRate.setText(String.valueOf(tvShow.getVoteAverage()));
        holder.ratingBar.setRating(voteAverage);
    }

    @Override
    public int getItemCount() {
        if (tvShowList == null){
            return 0;
        }
        return tvShowList.size();
    }

    public void setTvShowList(List<TVShowsItem> tvShowsItems) {
        this.tvShowList = tvShowsItems;
        notifyDataSetChanged();
    }

    class FavTvHolder extends RecyclerView.ViewHolder {

        private ImageView ivPoster;
        private TextView tvTittle;
        private TextView tvDate;
        private TextView tvRate;
        private RatingBar ratingBar;
        private LinearLayout itemClick2;

        public FavTvHolder(@NonNull View itemView){
            super(itemView);
            Typeface latoBlack = Typeface.createFromAsset(context.getAssets(), "font/latoblack.ttf");
            Typeface latoBold = Typeface.createFromAsset(context.getAssets(), "font/latobold.ttf");
            Typeface latoRegular = Typeface.createFromAsset(context.getAssets(), "font/latoregular.ttf");

            ivPoster = itemView.findViewById(R.id.iv_tvFavPoster);
            tvTittle = itemView.findViewById(R.id.tv_tvFavTittle);
            tvDate = itemView.findViewById(R.id.tv_tvFavDate);
            tvRate = itemView.findViewById(R.id.tv_tvRateFav);
            ratingBar = itemView.findViewById(R.id.rbFavTv);
            //itemClick2 = itemView.findViewById(R.id.itemClick4);

            tvTittle.setTypeface(latoBlack);
            tvDate.setTypeface(latoRegular);
            tvRate.setTypeface(latoBold);

        }
    }
}
