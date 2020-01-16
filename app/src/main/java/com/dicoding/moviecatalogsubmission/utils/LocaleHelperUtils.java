package com.dicoding.moviecatalogsubmission.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.dicoding.moviecatalogsubmission.SettingActivity;

import java.util.Locale;

public class LocaleHelperUtils extends ContextWrapper{

    public LocaleHelperUtils(Context base) {
        super(base);
    }

    public static LocaleHelperUtils setAppLocale(Context context, String localeCode) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        SharedPrefManager sharedPrefManager = new SharedPrefManager(context);
        localeCode = sharedPrefManager.getSP_Locale();

        config.setLocale(new Locale(localeCode.toLowerCase()));
        config.locale = new Locale(localeCode.toLowerCase());
        resources.updateConfiguration(config, dm);
        resources.updateConfiguration(config, dm);

        return new LocaleHelperUtils(context);

    }



 /*   public LocaleHelperUtils(Context context) {
        this.context = context;
    }

    public static setAppLocale(Context context, String localeCode) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        config.setLocale(new Locale(localeCode.toLowerCase()));
        config.locale = new Locale(localeCode.toLowerCase());
        resources.updateConfiguration(config, dm);

        return new ContextWrapper(context);

    }*/
}
