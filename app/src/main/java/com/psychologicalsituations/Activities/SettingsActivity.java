package com.psychologicalsituations.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.psychologicalsituations.Helpers.LocalHelper;
import com.psychologicalsituations.R;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    boolean hasLanguageChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setUpButton(getSupportActionBar());
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        String language = sharedPreferences.getString(getString(R.string.language_key), "en");
        Locale current = getResources().getConfiguration().locale;
        if (!current.getLanguage().equals(language)) {
            changeLanguage(language);
        }
        if (savedInstanceState != null) {
            hasLanguageChanged = savedInstanceState.getBoolean("hasLanguageChanged");
        } else {
            hasLanguageChanged = false;
        }
    }

    private void setUpButton(ActionBar actionBar) {
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        //Log.v("sharedL",s );
        if (s.equals(getString(R.string.language_key))) {
            String language = sharedPreferences.getString(s, "en");
            hasLanguageChanged = !hasLanguageChanged;
            changeLanguage(language);
        }
    }

    private void changeLanguage(String language) {
        LocalHelper.setLocale(this, language);
        Locale myLocale = new Locale(language);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        recreate();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("hasLanguageChanged", hasLanguageChanged);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                sendResultBackToMain();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        sendResultBackToMain();
    }

    private void sendResultBackToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        if (hasLanguageChanged) {
            startActivityForResult(intent, RESULT_OK);
        } else {
            startActivityForResult(intent, RESULT_CANCELED);
        }
        finish();
    }
}