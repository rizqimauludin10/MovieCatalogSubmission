package com.dicoding.moviecatalogsubmission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dicoding.moviecatalogsubmission.utils.LocaleHelperUtils;
import com.dicoding.moviecatalogsubmission.utils.SharedPrefManager;
import com.dicoding.moviecatalogsubmission.adapter.TabPagerAdapter;
import com.dicoding.moviecatalogsubmission.fragment.MovieListFragment;
import com.dicoding.moviecatalogsubmission.fragment.TvListFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPrefManager sharedPrefManager = new SharedPrefManager(MainActivity.this.getApplicationContext());

        String mLanguageCode = sharedPrefManager.getSP_Locale();
        LocaleHelperUtils localeHelperUtils = new LocaleHelperUtils(this);
        localeHelperUtils.setAppLocale(mLanguageCode);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getResources().getString(R.string.toolbar_tittle));
        toolbar.setTitleTextColor((ContextCompat.getColor(this, R.color.black2)));

        tabLayout = findViewById(R.id.tablayout_id);
        ViewPager viewPager = findViewById(R.id.viewpager_id);
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), 0);

        adapter.AddFragment(new MovieListFragment(), getResources().getString(R.string.tab_movie));
        adapter.AddFragment(new TvListFragment(), getResources().getString(R.string.tab_tvshows));

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        setCustomFont();
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
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void setCustomFont() {
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();

        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);

            int tabChildsCount = vgTab.getChildCount();

            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(Typeface.createFromAsset(getAssets(), "font/latoblack.ttf"));
                }
            }
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}
