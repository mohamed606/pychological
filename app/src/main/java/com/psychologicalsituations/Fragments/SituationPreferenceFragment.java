package com.psychologicalsituations.Fragments;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.psychologicalsituations.R;

public class SituationPreferenceFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings);

    }


}
