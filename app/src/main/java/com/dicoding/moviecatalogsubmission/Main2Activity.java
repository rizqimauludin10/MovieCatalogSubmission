package com.dicoding.moviecatalogsubmission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.dicoding.moviecatalogsubmission.adapter.TabPagerAdapter;
import com.dicoding.moviecatalogsubmission.fragment.MovieList_Fragment;
import com.dicoding.moviecatalogsubmission.fragment.TvList_Fragment;
import com.google.android.material.tabs.TabLayout;

public class Main2Activity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        adapter = new TabPagerAdapter(getSupportFragmentManager(), 0);

        //addFragmentHere
        adapter.AddFragment(new MovieList_Fragment(), "Movie List");
        adapter.AddFragment(new TvList_Fragment(), "TV Show");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
