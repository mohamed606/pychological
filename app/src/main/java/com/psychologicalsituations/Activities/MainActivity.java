package com.psychologicalsituations.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.psychologicalsituations.Adapters.SituationAdapter;
import com.psychologicalsituations.Entities.PsychologicalSituation;
import com.psychologicalsituations.Helpers.LocalHelper;
import com.psychologicalsituations.Listeners.SituationClickListener;
import com.psychologicalsituations.R;
import com.psychologicalsituations.ViewModels.SituationViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SituationClickListener {
    private FloatingActionButton addFab;
    private RecyclerView situationRecycler;
    private SituationAdapter adapter;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private SituationViewModel situationViewModel;
    private static final int ADD_SITUATION_REQUEST_CODE = 1;
    private static final int UPDATE_SITUATION_REQUEST_CODE = 2;
    private static final int CHANGE_LANGUAGE_REQUEST_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        String language = sharedPreferences.getString(getString(R.string.language_key), "en");
        if (!isLanguageCorrect(language)) {
            changeLanguage(language);
        }
        boolean isFirstTime = sharedPreferences.getBoolean("isFirstTime", true);
        if (isFirstTime) {
            editor.putBoolean("isFirstTime", false);
            firstTimeDialog();
            editor.apply();
        }
        addFab = findViewById(R.id.add_situation_fab);
        situationRecycler = findViewById(R.id.rc_situations);
        adapter = new SituationAdapter(this, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
        situationViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(SituationViewModel.class);
        situationViewModel.getAllSituations().observe(this,
                psychologicalSituations -> adapter.setSituations(psychologicalSituations)
        );

        allocateSituationRecycler();
        addClickListenerForFab();
    }


    private void addClickListenerForFab() {
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent situationIntent = new Intent(MainActivity.this, AlternativeSituationDetailActivity.class);
                startActivityForResult(situationIntent, ADD_SITUATION_REQUEST_CODE);
            }
        });
    }

    private void allocateSituationRecycler() {
        situationRecycler.setLayoutManager(new LinearLayoutManager(this));
        situationRecycler.setAdapter(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                situationViewModel.delete(adapter.getSituation(viewHolder.getAdapterPosition()));
            }
        });
        helper.attachToRecyclerView(situationRecycler);
    }

    @Override
    public void goToDetailActivity(int position) {
        Intent intent = new Intent(MainActivity.this, AlternativeSituationDetailActivity.class);
        PsychologicalSituation situation = adapter.getSituation(position);
        intent.putExtra("situationId", situation.getId());
        intent.putExtra(getString(R.string.situation), situation.getSituation());
        intent.putExtra(getString(R.string.idea), situation.getIdea());
        intent.putExtra(getString(R.string.emotion), situation.getEmotion());
        intent.putExtra(getString(R.string.behaviour), situation.getBehaviour());
        intent.putExtra(getString(R.string.wrong_thinking), situation.getWrongThinking());
        intent.putExtra(getString(R.string.coent), situation.getCeont());
        intent.putExtra(getString(R.string.ceontP), situation.getCeontP());
        intent.putExtra(getString(R.string.tdob), String.valueOf(situation.getDegreeOfBelief()));
        intent.putExtra(getString(R.string.pd), String.valueOf(situation.getPsychologicalDegree()));
        intent.putExtra(getString(R.string.at), situation.getAlternativeThought());
        intent.putExtra("situationDate", situation.getDate().getTime());

        startActivityForResult(intent, UPDATE_SITUATION_REQUEST_CODE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivityForResult(intent, CHANGE_LANGUAGE_REQUEST_CODE);
            return true;
        } else if (itemId == R.id.delete_all) {
            situationViewModel.deleteAll();
            return true;
        }
        return false;
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

    private void firstTimeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.level));
        builder.setMessage(R.string.choose_level);
        builder.setNegativeButton(R.string.first_level, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setPositiveButton(R.string.second_level, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                editor.putString(getString(R.string.levelKey), "2");
                editor.apply();
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_SITUATION_REQUEST_CODE && resultCode == RESULT_OK) {
            String tdobScale = data.getStringExtra(getString(R.string.tdob));
            String pdScale = data.getStringExtra(getString(R.string.pd));
            double tdob = 0;
            double pd = 0;
            if(tdobScale != null && !tdobScale.trim().isEmpty()){
                tdob = Double.parseDouble(tdobScale);
            }
            if(pdScale != null && !pdScale.trim().isEmpty()){
                pd = Double.parseDouble(pdScale);
            }
            PsychologicalSituation situation = new PsychologicalSituation(data.getStringExtra(getString(R.string.situation))
                    , data.getStringExtra(getString(R.string.idea))
                    , data.getStringExtra(getString(R.string.emotion))
                    , data.getStringExtra(getString(R.string.behaviour))
                    , data.getStringExtra(getString(R.string.wrong_thinking))
                    , data.getStringExtra(getString(R.string.coent))
                    , data.getStringExtra(getString(R.string.ceontP))
                    , tdob
                    , pd
                    , data.getStringExtra(getString(R.string.at))
                    , new Date());
            situationViewModel.insert(situation);
        } else if (requestCode == CHANGE_LANGUAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            String language = sharedPreferences.getString(getString(R.string.language_key), "en");
            changeLanguage(language);
        } else if (requestCode == UPDATE_SITUATION_REQUEST_CODE && resultCode == RESULT_OK) {
            String tdobScale = data.getStringExtra(getString(R.string.tdob));
            String pdScale = data.getStringExtra(getString(R.string.pd));
            double tdob = 0;
            double pd = 0;
            if(tdobScale != null && !tdobScale.trim().isEmpty()){
                tdob = Double.parseDouble(tdobScale);
            }
            if(pdScale != null && !pdScale.trim().isEmpty()){
                pd = Double.parseDouble(pdScale);
            }
            Log.v("main",String.valueOf(tdob));
            PsychologicalSituation situation = new PsychologicalSituation(data.getStringExtra(getString(R.string.situation))
                    , data.getStringExtra(getString(R.string.idea))
                    , data.getStringExtra(getString(R.string.emotion))
                    , data.getStringExtra(getString(R.string.behaviour))
                    , data.getStringExtra(getString(R.string.wrong_thinking))
                    , data.getStringExtra(getString(R.string.coent))
                    , data.getStringExtra(getString(R.string.ceontP))
                    , tdob
                    , pd
                    , data.getStringExtra(getString(R.string.at))
                    , new Date(data.getLongExtra("situationDate", 0)));
            situation.setId(data.getIntExtra("situationId", 0));
            situationViewModel.update(situation);
        }
    }

    private boolean isLanguageCorrect(String language) {
        Locale current = getResources().getConfiguration().locale;
        String currentLanguage = current.getLanguage();
        return currentLanguage.equals(language);
    }
}