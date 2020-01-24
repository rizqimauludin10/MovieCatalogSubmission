package com.dicoding.moviecatalogsubmission;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.dicoding.moviecatalogsubmission.fragment.FavoriteFragment;

public class SearchActivity extends AppCompatActivity {

    Fragment fragment = new FavoriteFragment();
    final FragmentManager fm = this.getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        fm.beginTransaction().add(R.id.aa, fragment, "1").commit();
        Log.d("Fragment Test", "Fragment Test > ");

    }
}
