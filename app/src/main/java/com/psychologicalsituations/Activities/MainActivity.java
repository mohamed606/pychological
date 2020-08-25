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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.psychologicalsituations.Adapters.SituationAdapter;
import com.psychologicalsituations.Data.DummySituation;
import com.psychologicalsituations.Helpers.LocalHelper;
import com.psychologicalsituations.Listeners.SituationClickListener;
import com.psychologicalsituations.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements SituationClickListener {
    private FloatingActionButton addFab;
    private List<DummySituation> dummyDatas;
    private RecyclerView situationRecycler;
    private SituationAdapter adapter;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String currentLanguage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        String language = sharedPreferences.getString(getString(R.string.language_key), "en");
        Locale current  = getResources().getConfiguration().locale;
        currentLanguage = current.getLanguage();
        if(!currentLanguage.equals(language)){
            changeLanguage(language);
            currentLanguage = language;
        }
        boolean isFirstTime = sharedPreferences.getBoolean("isFirstTime",true);

        if(isFirstTime){
            editor.putBoolean("isFirstTime",false);
            firstTimeDialog();
            editor.apply();
        }
        addFab = findViewById(R.id.add_situation_fab);
        situationRecycler = findViewById(R.id.rc_situations);
        dummyDatas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            dummyDatas.add(new DummySituation("se", new Date()));
        }
        adapter = new SituationAdapter(dummyDatas, this, this);
        allocateSituationRecycler();
        addClickListenerForFab();

    }

    @Override
    protected void onResume() {
        super.onResume();
        String language = sharedPreferences.getString(getString(R.string.language_key), "en");
        if(!currentLanguage.equals(language)){
            recreate();
        }
    }


    private void addClickListenerForFab() {
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent situationIntent = new Intent(MainActivity.this, SituationDetailActivity.class);
                situationIntent.putExtra("level", getLevel());
                startActivity(situationIntent);
            }
        });
    }

    private void allocateSituationRecycler() {
        //DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        situationRecycler.setLayoutManager(new LinearLayoutManager(this));
        situationRecycler.setAdapter(adapter);
       // situationRecycler.addItemDecoration(decoration);
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                dummyDatas.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        helper.attachToRecyclerView(situationRecycler);
    }

    @Override
    public void goToDetailActivity(int position) {
        Intent situationIntent = new Intent(MainActivity.this, AlternativeSituationDetailActivity.class);
        //situationIntent.putExtra("level", getLevel());
        startActivity(situationIntent);
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
            startActivity(intent);
        }
        return false;
    }

    private int getLevel() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return Integer.parseInt(Objects.requireNonNull(sharedPreferences.getString(getString(R.string.levelKey), "1")));
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
    private void firstTimeDialog(){
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
                editor.putString(getString(R.string.levelKey),"2");
                editor.apply();
            }
        });
        builder.show();
    }
}