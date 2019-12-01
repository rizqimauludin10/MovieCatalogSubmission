package com.dicoding.moviecatalogsubmission.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

public class LocaleHelperUtils {

    Context context;

    public LocaleHelperUtils(Context context) {
        this.context = context;
    }

    public void setAppLocale(String localeCode) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
            config.setLocale(new Locale(localeCode.toLowerCase()));
            config.locale = new Locale(localeCode.toLowerCase());
        resources.updateConfiguration(config, dm);
    }
}
