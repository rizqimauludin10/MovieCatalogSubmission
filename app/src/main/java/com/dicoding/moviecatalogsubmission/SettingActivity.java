package com.dicoding.moviecatalogsubmission;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dicoding.moviecatalogsubmission.utils.LocaleHelperUtils;
import com.dicoding.moviecatalogsubmission.utils.SharedPrefManager;

import java.util.Objects;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.view.View.OnClickListener;
import static android.widget.RadioGroup.OnCheckedChangeListener;

public class SettingActivity extends AppCompatActivity {
    private String mLanguageCode;
    SharedPrefManager sharedPrefManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        sharedPrefManager = new SharedPrefManager(SettingActivity.this.getApplicationContext());
        String saveLang = sharedPrefManager.getSP_Locale();

        LocaleHelperUtils.setAppLocale(this, mLanguageCode);

        Toolbar toolbar = findViewById(R.id.setting_toolbar);
        ImageView back = findViewById(R.id.back_setting);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");

        Log.d("Locale Test", "Shared Preference > " + saveLang);

        RadioGroup radioGroupLg = findViewById(R.id.rg_language);
        RadioButton rb_Eg = findViewById(R.id.lg_eg);
        RadioButton rb_Es = findViewById(R.id.lg_es);
        RadioButton rb_In = findViewById(R.id.lg_in);

        switch (saveLang) {
            case "es":
                rb_Es.setChecked(true);
                break;
            case "en":
                rb_Eg.setChecked(true);
                break;
            case "in":
                rb_In.setChecked(true);
                break;
        }

        back.setOnClickListener(v -> {
            /*Intent i = new Intent(SettingActivity.this, MainActivity.class);
            startActivity(i);*/
            onBackPressed();
        });

        radioGroupLg.setOnCheckedChangeListener((group, checkedId) -> {
            int id = group.getCheckedRadioButtonId();

            switch (id) {
                case R.id.lg_eg :
                    mLanguageCode = "en";
                    sharedPrefManager.saveSPString(SharedPrefManager.SP_Locale, mLanguageCode);
                    intentRefresh();
                    break;
                case R.id.lg_es :
                    mLanguageCode = "es";
                    sharedPrefManager.saveSPString(SharedPrefManager.SP_Locale, mLanguageCode);
                    intentRefresh();
                    break;
                case R.id.lg_in :
                    mLanguageCode = "in";
                    sharedPrefManager.saveSPString(SharedPrefManager.SP_Locale,mLanguageCode);
                    intentRefresh();
                default:

            }
        });


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.backanimin,
                R.anim.backanim);
        //super.onBackPressed();
    }

    public void intentRefresh() {
        Intent i = new Intent(SettingActivity.this, SettingActivity.class);
        overridePendingTransition(0, 0);
        startActivity(i);
        overridePendingTransition(0, 0);

        LocaleHelperUtils.setAppLocale(this, mLanguageCode);


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        //super.attachBaseContext(LocaleHelperUtils.setAppLocale(newBase, mLanguageCode));
    }

}
