package com.dicoding.moviecatalogsubmission.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.moviecatalogsubmission.R;
import com.dicoding.moviecatalogsubmission.adapter.RecycleMovieAdapter;
import com.dicoding.moviecatalogsubmission.adapter.RecycleViewTvAdapter;
import com.dicoding.moviecatalogsubmission.model.Movie;
import com.dicoding.moviecatalogsubmission.model.TVShow;

import java.util.ArrayList;
import java.util.List;

public class TvList_Fragment extends Fragment {

    View view;
    RecyclerView rvTv;
    Context context;
    private RecyclerView.Adapter tvAdapter;
    private List<TVShow> tvShowArrayList = new ArrayList<>();

    private String[] dataTittle;
    private String[] dataDate;
    private String[] dataDesc;
    private String[] dataRate2;
    private TypedArray dataRate;
    private TypedArray dataPoster;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tvlist_fragment, container, false);

        rvTv = (RecyclerView) view.findViewById(R.id.rvTv);
        context = getActivity();
        tvAdapter = new RecycleViewTvAdapter(context, tvShowArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rvTv.setLayoutManager(layoutManager);
        rvTv.setItemAnimator(new DefaultItemAnimator());

        prepare();
        addItem();

        return view;
    }

    private void addItem() {
        tvShowArrayList = new ArrayList<>();

        for (int i = 0; i < dataTittle.length; i++) {
            TVShow tvShow = new TVShow();
            tvShow.setTvPoster(dataPoster.getResourceId(i, -1));
            tvShow.setTvTittle(dataTittle[i]);
            tvShow.setTvDate(dataDate[i]);
            tvShow.setTvDesc(dataDesc[i]);
            tvShow.setTvRate2(dataRate2[i]);
            tvShow.setTvRate(dataRate.getFloat(i, -1));
            tvShowArrayList.add(tvShow);
        }
        rvTv.setAdapter(new RecycleViewTvAdapter(context, tvShowArrayList));
    }

    private void prepare() {
        dataPoster = getResources().obtainTypedArray(R.array.tvdata_photo);
        dataTittle = getResources().getStringArray(R.array.tvdata_name);
        dataDate = getResources().getStringArray(R.array.tvdata_date);
        dataDesc = getResources().getStringArray(R.array.tvdata_description);
        dataRate = getResources().obtainTypedArray(R.array.tvdata_rate);
        dataRate2 = getResources().getStringArray(R.array.tvdata_rate2);

    }
}
