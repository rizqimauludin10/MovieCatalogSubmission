package com.dicoding.moviecatalogsubmission;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dicoding.moviecatalogsubmission.fragment.FavoriteFragment;
import com.dicoding.moviecatalogsubmission.fragment.MovieListFragment;
import com.dicoding.moviecatalogsubmission.fragment.TvListFragment;
import com.dicoding.moviecatalogsubmission.utils.LocaleHelperUtils;
import com.dicoding.moviecatalogsubmission.utils.SharedPrefManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private TabLayout tabLayout;
    private Fragment fragment1;
    final Fragment fragment2 = new TvListFragment();
    final Fragment fragment3 = new FavoriteFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    //private final String SIMPLE_FRAGMENT_TAG = "myfragmenttag";

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

        //tabLayout = findViewById(R.id.tablayout_id);
        BottomNavigationView bottomNavigationView = findViewById(R.id.btmNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        //ViewPager viewPager = findViewById(R.id.viewpager_id);
        //TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), 0);

    if (savedInstanceState == null) {
        bottomNavigationView.setSelectedItemId(R.id.home_menu);
        //loadFragment(new MovieListFragment());
    }

       /*fm.beginTransaction().add(R.id.mainacv,fragment1, "1").commit();
        fm.beginTransaction().add(R.id.mainacv, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.mainacv, fragment2, "2").hide(fragment2).commit();*/

        //adapter.AddFragment(new MovieListFragment(), getResources().getString(R.string.tab_movie));
        //adapter.AddFragment(new TvListFragment(), getResources().getString(R.string.tab_tvshows));

        //viewPager.setAdapter(adapter);
        //tabLayout.setupWithViewPager(viewPager);



        //setCustomFont();
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainacv, fragment);
        transaction.commit();
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        //Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.mainacv);
        //FragmentManager mFragmentManager = getFragmentManager();
        int id = item.getItemId();
        switch (id) {
            case R.id.home_menu:
                //loadFragment(new MovieListFragment());
                fragment = new MovieListFragment();
                //loadFragment(fragment);


                if (fm != null) {
                    fm
                            .beginTransaction()
                            .replace(R.id.mainacv, fragment, MovieListFragment.class.getSimpleName())
                            .addToBackStack(null)
                            .commit();
                }
                /*fm.beginTransaction().hide(active).show(fragment1).commit();
                active = fragment1;*/
                return true;
               /* fragment = new MovieListFragment();
                break;*/
            case R.id.tv_menu:
                //loadFragment(new TvListFragment());
                fragment = new TvListFragment();

                if (fm != null) {
                    fm
                            .beginTransaction()
                            .replace(R.id.mainacv, fragment, TvListFragment.class.getSimpleName())
                            .addToBackStack(null)
                            .commit();
                }
                //loadFragment(fragment);
                /*fm.beginTransaction().hide(active).show(fragment2).commit();
                active = fragment2;*/
                return true;
                /*fragment = new TvListFragment();
                break;*/
            case R.id.favorite_menu:
                //loadFragment(new FavoriteFragment());
                fragment = new FavoriteFragment();
                if (fm != null) {
                    fm
                            .beginTransaction()
                            .replace(R.id.mainacv, fragment, FavoriteFragment.class.getSimpleName())
                            .addToBackStack(null)
                            .commit();
                }
                //loadFragment(fragment);
                /*getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainacv, fragment, fragment.getClass().getSimpleName())
                        .commit();*/
               /* fm.beginTransaction().hide(active).show(fragment3).commit();
                active = fragment3;*/
                return true;
                /*fragment= new FavoriteFragment();
                break;*/
        }
        return false;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
