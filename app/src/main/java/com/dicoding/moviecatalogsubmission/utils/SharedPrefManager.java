package com.dicoding.moviecatalogsubmission.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    public static final String SP_Catalog = "spCatalog";
    public static final String SP_Locale = "spLocale";
    public static final String SP_RadioLang = "spRadioLang";
    public static final String SP_MovieIdDetail = "spIdMovie";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SP_Catalog, Context.MODE_PRIVATE);
        spEditor = sharedPreferences.edit();
    }

    public void saveSPString(String keySp, String value) {
        spEditor.putString(keySp, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySp, boolean value){
        spEditor.putBoolean(keySp, value);
        spEditor.commit();
    }

    public void saveSPInteger(String keySp, Integer value) {
        spEditor.putInt(keySp, value);
        spEditor.commit();
    }

    public String getSP_Locale() {
        return sharedPreferences.getString(SP_Locale, "");
    }

    public Boolean getSPRadioLang() {
        return sharedPreferences.getBoolean(SP_RadioLang, false);
    }

    public Integer getSP_MovieIdDetail() {
        return (Integer) sharedPreferences.getInt(SP_MovieIdDetail, 0);
    }
}
