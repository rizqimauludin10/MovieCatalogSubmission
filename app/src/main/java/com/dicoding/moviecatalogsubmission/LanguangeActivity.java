package com.dicoding.moviecatalogsubmission;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dicoding.moviecatalogsubmission.Utils.LocaleHelper;
import com.dicoding.moviecatalogsubmission.Utils.SharedPrefManager;

import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.widget.RadioGroup.*;

public class LanguangeActivity extends AppCompatActivity {
    private RadioGroup radioGroupLg;
    private RadioButton rb_Eg, rb_Es;
    private Button bt;
    private String mLanguageCode;
    private String saveLang;
    Context context;
    SharedPrefManager sharedPrefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languange);

        sharedPrefManager = new SharedPrefManager(LanguangeActivity.this.getApplicationContext());
        saveLang = sharedPrefManager.getSP_Locale();

        Log.d("Locale Test", "Shared Preference > " + saveLang);


        radioGroupLg = findViewById(R.id.rg_language);
        rb_Eg = findViewById(R.id.lg_eg);
        rb_Es = findViewById(R.id.lg_es);
        bt = findViewById(R.id.btn);

        if (saveLang.equals("es")){
            rb_Es.setChecked(true);
        } else if (saveLang.equals("en")) {
            rb_Eg.setChecked(true);
        }



        bt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //LocaleHelper.setLocale(LanguangeActivity.this, mLanguageCode);
                //recreate();
            }
        });

        radioGroupLg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = group.getCheckedRadioButtonId();

                switch (id) {
                    case R.id.lg_eg :
                        mLanguageCode = "en";
                        Toast.makeText(getApplicationContext(), "English", Toast.LENGTH_SHORT).show();
                        sharedPrefManager.saveSPString(SharedPrefManager.SP_Locale, mLanguageCode);
                        //sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_RadioLang, true);
                        startActivity(new Intent(LanguangeActivity.this, Main2Activity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();
                        break;
                    case R.id.lg_es :
                        mLanguageCode = "es";
                        Toast.makeText(getApplicationContext(), "Spain", Toast.LENGTH_SHORT).show();
                        sharedPrefManager.saveSPString(SharedPrefManager.SP_Locale, mLanguageCode);
                        //sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_RadioLang, true);
                        startActivity(new Intent(LanguangeActivity.this, Main2Activity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();
                        break;
                        default:

                }
            }
        });
    }

}
