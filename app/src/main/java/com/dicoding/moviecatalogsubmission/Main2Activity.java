package com.dicoding.moviecatalogsubmission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.dicoding.moviecatalogsubmission.Utils.LocaleHelperUtils;
import com.dicoding.moviecatalogsubmission.Utils.SharedPrefManager;
import com.dicoding.moviecatalogsubmission.adapter.TabPagerAdapter;
import com.dicoding.moviecatalogsubmission.fragment.MovieList_Fragment;
import com.dicoding.moviecatalogsubmission.fragment.TvList_Fragment;
import com.google.android.material.tabs.TabLayout;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Main2Activity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabPagerAdapter adapter;
    private String mLanguageCode;
    private LocaleHelperUtils localeHelperUtils;
    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        sharedPrefManager = new SharedPrefManager(Main2Activity.this.getApplicationContext());

        mLanguageCode = sharedPrefManager.getSP_Locale();
        localeHelperUtils = new LocaleHelperUtils(this);
        localeHelperUtils.setAppLocale(mLanguageCode);

        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        adapter = new TabPagerAdapter(getSupportFragmentManager(), 0);

        adapter.AddFragment(new MovieList_Fragment(), getResources().getString(R.string.tab_movie));
        adapter.AddFragment(new TvList_Fragment(), getResources().getString(R.string.tab_tvshows));

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.ToolbarTittle));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.setting) {
            Intent intent = new Intent(Main2Activity.this, SettingActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}
