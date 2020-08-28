package com.psychologicalsituations.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.psychologicalsituations.R;
import com.psychologicalsituations.Utilits.ActionBarUtility;
import com.psychologicalsituations.Utilits.LanguageUtilities;

public class SettingsActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private boolean hasLanguageChanged;
    private View.OnClickListener levelClickListener;
    private View.OnClickListener languageClickListener;
    private SharedPreferences sharedPreferences;
    private TextView levelSummaryTextView;
    private TextView languageSummaryTextView;
    private AlertDialog levelDialog;
    private AlertDialog languageDialog;
    private View levelRadioGroup;
    private View languageRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        levelRadioGroup = getLayoutInflater().inflate(R.layout.level_dialog, null);

        languageRadioGroup = getLayoutInflater().inflate(R.layout.language_dialog, null);

        levelSummaryTextView = findViewById(R.id.level_summary_tv);

        languageSummaryTextView = findViewById(R.id.language_summary_tv);


        ActionBarUtility.setUpButton(getSupportActionBar());

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String level = sharedPreferences.getString(getString(R.string.levelKey), "1");

        String languageAcronym = sharedPreferences.getString(getString(R.string.language_key), "en");

        String language = convertAcronymToFull(languageAcronym);

        languageSummaryTextView.setText(language);

        levelSummaryTextView.setText(level);

        levelDialog = getDialog(levelRadioGroup, getString(R.string.level));

        languageDialog = getDialog(languageRadioGroup, getString(R.string.language));

        setLevelClickListener(levelDialog);

        setLanguageClickListener(languageDialog);

        setUpTheLanguageRadioButtons(languageAcronym);

        setUpTheLevelRadioButtons(level);

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        LanguageUtilities.setUpActivityLanguage(this, sharedPreferences.getString(getString(R.string.language_key), "en"));

        if (savedInstanceState != null) {
            hasLanguageChanged = savedInstanceState.getBoolean("hasLanguageChanged");
        } else {
            hasLanguageChanged = false;
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equals(getString(R.string.language_key))) {
            String language = sharedPreferences.getString(s, "en");
            hasLanguageChanged = !hasLanguageChanged;
            LanguageUtilities.changeLanguage(this, language);
        }
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

    public void showChooseLanguageDialog(View view) {
        languageDialog.show();
    }
    private AlertDialog getDialog(View view, String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.setTitle(title);
        return builder.create();
    }
    public void setLevelClickListener(AlertDialog dialog) {
        levelClickListener = view -> {
            int id = view.getId();
            if (id == R.id.first_Level_radioButton) {
                sharedPreferences.edit().putString(getString(R.string.levelKey), "1").apply();
                levelSummaryTextView.setText("1");
            } else if (id == R.id.second_level_radioButton) {
                sharedPreferences.edit().putString(getString(R.string.levelKey), "2").apply();
                levelSummaryTextView.setText("2");
            }
            dialog.dismiss();
        };
    }

    public void setLanguageClickListener(AlertDialog dialog) {
        languageClickListener = view -> {
            int id = view.getId();
            if (id == R.id.en_radioButton) {
                sharedPreferences.edit().putString(getString(R.string.language_key), "en").apply();
                languageSummaryTextView.setText(getString(R.string.language_english));
            } else if (id == R.id.ar_radioButton) {
                sharedPreferences.edit().putString(getString(R.string.language_key), "ar").apply();
                languageSummaryTextView.setText(getString(R.string.language_arabic));
            }
            dialog.dismiss();
        };
    }

    public void getChooseLevelDialog(View view) {
        levelDialog.show();
    }

    private String convertAcronymToFull(String acronym) {
        if (acronym.equals("en")) {
            return getString(R.string.language_english);
        } else if (acronym.equals("ar")) {
            return getString(R.string.language_arabic);
        }
        return null;
    }

    private void setUpTheLevelRadioButtons(String level) {
        RadioButton firstLevelRadioButton = levelRadioGroup.findViewById(R.id.first_Level_radioButton);
        RadioButton secondLevelRadioButton = levelRadioGroup.findViewById(R.id.second_level_radioButton);
        if (level.equals("1")) {
            firstLevelRadioButton.setChecked(true);
        } else if (level.equals("2")) {
            secondLevelRadioButton.setChecked(true);
        }
        firstLevelRadioButton.setOnClickListener(levelClickListener);
        secondLevelRadioButton.setOnClickListener(levelClickListener);
    }

    private void setUpTheLanguageRadioButtons(String languageAcronym) {
        RadioButton enRadioButton = languageRadioGroup.findViewById(R.id.en_radioButton);
        RadioButton arRadioButton = languageRadioGroup.findViewById(R.id.ar_radioButton);
        if (languageAcronym.equals("en")) {
            enRadioButton.setChecked(true);
        } else if (languageAcronym.equals("ar")) {
            arRadioButton.setChecked(true);
        }
        enRadioButton.setOnClickListener(languageClickListener);
        arRadioButton.setOnClickListener(languageClickListener);
    }
}