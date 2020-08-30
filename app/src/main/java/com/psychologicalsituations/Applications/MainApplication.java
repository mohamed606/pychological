package com.psychologicalsituations.Applications;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

import com.psychologicalsituations.Helpers.LocalHelper;
import com.psychologicalsituations.R;

public class MainApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocalHelper.onAttach(base, "en"));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (!sharedPreferences.contains(getString(R.string.language_key))) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(getString(R.string.language_key), "en");
            editor.apply();
        }
        if (!sharedPreferences.contains(getString(R.string.levelKey))) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(getString(R.string.levelKey), "1");
            editor.apply();
        }

    }
}
