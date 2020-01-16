package com.dicoding.moviecatalogsubmission;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.dicoding.moviecatalogsubmission.fragment.FavoriteFragment;
import com.dicoding.moviecatalogsubmission.fragment.MovieListFragment;
import com.dicoding.moviecatalogsubmission.fragment.TvListFragment;
import com.dicoding.moviecatalogsubmission.utils.LocaleHelperUtils;
import com.dicoding.moviecatalogsubmission.utils.SharedPrefManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Fragment fragment1 = new MovieListFragment();
    final Fragment fragment2 = new TvListFragment();
    final Fragment fragment3 = new FavoriteFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;
    private BottomNavigationView bottomNavigationView;
    private String mLanguageCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPrefManager sharedPrefManager = new SharedPrefManager(MainActivity.this.getApplicationContext());

        mLanguageCode = sharedPrefManager.getSP_Locale();
        LocaleHelperUtils.setAppLocale(this, mLanguageCode);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getResources().getString(R.string.toolbar_tittle));
        toolbar.setTitleTextColor((ContextCompat.getColor(this, R.color.black2)));

        bottomNavigationView = findViewById(R.id.btmNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.getMenu().clear();


        if (savedInstanceState == null) {
            fm.beginTransaction().add(R.id.mainacv, fragment3, "3").hide(fragment3).commit();
            fm.beginTransaction().add(R.id.mainacv, fragment2, "2").hide(fragment2).commit();
            fm.beginTransaction().add(R.id.mainacv, fragment1, "1").commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        bottomNavigationView.inflateMenu(R.menu.bottomnavigation_menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.setting) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.home_menu:
                fm.beginTransaction().hide(active).show(fragment1).commit();
                active = fragment1;
                return true;
            case R.id.tv_menu:
                fm.beginTransaction().hide(active).show(fragment2).commit();
                active = fragment2;
                return true;
            case R.id.favorite_menu:
                fm.beginTransaction().hide(active).show(fragment3).commit();
                active = fragment3;
                return true;
        }
        return false;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelperUtils.setAppLocale(newBase, mLanguageCode));
    }


}
