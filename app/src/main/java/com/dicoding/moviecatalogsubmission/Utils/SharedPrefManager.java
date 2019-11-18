package com.dicoding.moviecatalogsubmission.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    public static final String SP_Catalog = "spCatalog";
    public static final String SP_Locale = "spLocale";
    public static final String SP_RadioLang = "spRadioLang";

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

    public String getSP_Locale() {
        return sharedPreferences.getString(SP_Locale, "");
    }

    public Boolean getSPRadioLang() {
        return sharedPreferences.getBoolean(SP_RadioLang, false);
    }
}
