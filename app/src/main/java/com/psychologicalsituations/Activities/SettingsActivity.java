package com.psychologicalsituations.Activities;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.psychologicalsituations.Helpers.LocalHelper;
import com.psychologicalsituations.R;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setUpButton(getSupportActionBar());
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        String language = sharedPreferences.getString(getString(R.string.language_key), "en");
        Locale current  =getResources().getConfiguration().locale;
        if(!current.getLanguage().equals(language)){
            changeLanguage(language);
        }

    }
    private void setUpButton(ActionBar actionBar){
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        //Log.v("sharedL",s );
        if (s.equals(getString(R.string.language_key))) {
            String language = sharedPreferences.getString(s, "en");
           changeLanguage(language);
        }
    }
    private void changeLanguage(String language){
        LocalHelper.setLocale(this, language);
        Locale myLocale = new Locale(language);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        recreate();
    }
}