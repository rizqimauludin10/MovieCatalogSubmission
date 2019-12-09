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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dicoding.moviecatalogsubmission.Detail2Activity;
import com.dicoding.moviecatalogsubmission.Detail3Activity;
import com.dicoding.moviecatalogsubmission.R;
import com.dicoding.moviecatalogsubmission.model.Movie;
import com.dicoding.moviecatalogsubmission.model.TVShow;

import java.util.List;

import static android.view.View.*;

public class RecycleViewTvAdapter extends RecyclerView.Adapter<RecycleViewTvAdapter.TvHolder> {

    List<TVShow> tvShowList;
    Context context;

    public RecycleViewTvAdapter(Context context, List<TVShow> tvShowList) {
        this.context = context;
        this.tvShowList = tvShowList;
    }

    @NonNull
    @Override
    public TvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout4, parent, false);
        final TvHolder tvHolder= new TvHolder(view);
        return tvHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TvHolder holder, final int position) {
        final TVShow tvShow = tvShowList.get(position);
        Glide.with(context).load(tvShow.getTvPoster()).into(holder.ivPoster);
        holder.tvTittle.setText(tvShow.getTvTittle());
        holder.tvDesc.setText(tvShow.getTvDesc());
        holder.tvRate.setText(tvShow.getTvRate2());
        holder.ratingBar.setRating(tvShow.getTvRate());
        holder.itemClick2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Detail3Activity.class);
                intent.putExtra(Detail3Activity.EXTRA_MOVIE3, tvShowList.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tvShowList.size();
    }

    public class TvHolder extends RecyclerView.ViewHolder {

        private ImageView ivPoster;
        private TextView tvTittle;
        private TextView tvDesc;
        private TextView tvRate;
        private RatingBar ratingBar;
        private LinearLayout itemClick2;

        public TvHolder(@NonNull View itemView) {
            super(itemView);
            Typeface latoBlack = Typeface.createFromAsset(context.getAssets(), "font/latoblack.ttf");
            Typeface latoRegular = Typeface.createFromAsset(context.getAssets(), "font/latoregular.ttf");

            ivPoster = itemView.findViewById(R.id.iv_tvPoster);
            tvTittle = itemView.findViewById(R.id.tv_tvTittle);
            tvDesc = itemView.findViewById(R.id.tv_tvDesc);
            tvRate = itemView.findViewById(R.id.tv_tvRate);
            ratingBar = itemView.findViewById(R.id.ratingBar3);
            itemClick2 = itemView.findViewById(R.id.itemClick4);

            tvTittle.setTypeface(latoBlack);
            tvDesc.setTypeface(latoRegular);
        }
    }
}
