package com.dicoding.moviecatalogsubmission;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dicoding.moviecatalogsubmission.Utils.LocaleHelperUtils;
import com.dicoding.moviecatalogsubmission.Utils.SharedPrefManager;

import static android.view.View.*;
import static android.widget.RadioGroup.OnCheckedChangeListener;

public class SettingActivity extends AppCompatActivity {
    private RadioGroup radioGroupLg;
    private RadioButton rb_Eg, rb_Es, rb_In;
    private Button bt;
    private ImageView back;
    private String mLanguageCode;
    private String saveLang;
    private LocaleHelperUtils localeHelperUtils;
    Context context;
    SharedPrefManager sharedPrefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        sharedPrefManager = new SharedPrefManager(SettingActivity.this.getApplicationContext());
        saveLang = sharedPrefManager.getSP_Locale();

        Toolbar toolbar = findViewById(R.id.setting_toolbar);
        back = findViewById(R.id.back_setting);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        Log.d("Locale Test", "Shared Preference > " + saveLang);

        radioGroupLg = findViewById(R.id.rg_language);
        rb_Eg = findViewById(R.id.lg_eg);
        rb_Es = findViewById(R.id.lg_es);
        rb_In = findViewById(R.id.lg_in);

        if (saveLang.equals("es")){
            rb_Es.setChecked(true);
        } else if (saveLang.equals("en")) {
            rb_Eg.setChecked(true);
        } else if (saveLang.equals("in")) {
            rb_In.setChecked(true);
        }

        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingActivity.this, Main2Activity.class);
                startActivity(i);
            }
        });

        radioGroupLg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = group.getCheckedRadioButtonId();

                switch (id) {
                    case R.id.lg_eg :
                        mLanguageCode = "en";
                        //Toast.makeText(getApplicationContext(), "English", Toast.LENGTH_SHORT).show();
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
            }
        });


    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        startActivity(new Intent(this, Main2Activity.class));
        overridePendingTransition(R.anim.backanimin,
                R.anim.backanim);
    }

    public void intentRefresh() {
        Intent i = new Intent(SettingActivity.this, SettingActivity.class);
        finish();
        overridePendingTransition(0, 0);
        startActivity(i);
        overridePendingTransition(0, 0);

        localeHelperUtils = new LocaleHelperUtils(this);
        localeHelperUtils.setAppLocale(mLanguageCode);

    }

}
