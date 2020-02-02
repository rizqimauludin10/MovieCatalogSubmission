package com.dicoding.moviecatalogsubmission.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.dicoding.moviecatalogsubmission.R;
import com.dicoding.moviecatalogsubmission.SettingActivity;
import com.dicoding.moviecatalogsubmission.adapter.TabPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class FavoriteFragment extends Fragment {
    private TabLayout tabLayout;
    private Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favorite_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar = view.findViewById(R.id.favToolbar);

        tabLayout = view.findViewById(R.id.tablayout_id);
        ViewPager viewPager = view.findViewById(R.id.viewpager_id);
        TabPagerAdapter adapter = new TabPagerAdapter(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), 0);


        setToolbarTitle(getResources().getString(R.string.tab_favorite));


        adapter.AddFragment(new FavMovieFragment(), getResources().getString(R.string.tab_movie));
        adapter.AddFragment(new FavTvFragment(), getResources().getString(R.string.tab_tvshows));


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        setCustomFont();
    }

    private void setToolbarTitle(String title) {
        toolbar.setTitle(title);
        toolbar.setTitleTextColor((ContextCompat.getColor(Objects.requireNonNull(getActivity()), R.color.black2)));
        customMenu();
    }

    private void customMenu() {
        toolbar.inflateMenu(R.menu.main);

        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.setting) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                Objects.requireNonNull(getActivity()).finish();
            }
            return false;
        });
    }


    private void setCustomFont() {
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();

        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);

            int tabChildsCount = vgTab.getChildCount();

            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(Typeface.createFromAsset(Objects.requireNonNull(getActivity()).getAssets(), "font/latoblack.ttf"));
                }
            }
        }
    }
}
