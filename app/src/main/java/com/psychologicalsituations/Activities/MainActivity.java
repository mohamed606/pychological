package com.psychologicalsituations.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.psychologicalsituations.Adapters.SituationAdapter;
import com.psychologicalsituations.Data.DummySituation;
import com.psychologicalsituations.Listeners.SituationClickListener;
import com.psychologicalsituations.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SituationClickListener {
    private FloatingActionButton addFab;
    private List<DummySituation> dummyDatas;
    private RecyclerView situationRecycler;
    private SituationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    private void addClickListenerForFab() {
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent situationIntent = new Intent(MainActivity.this, SituationDetailActivity.class);
                startActivity(situationIntent);
            }
        });
    }

    private void allocateSituationRecycler() {
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        situationRecycler.setLayoutManager(new LinearLayoutManager(this));
        situationRecycler.setAdapter(adapter);
        situationRecycler.addItemDecoration(decoration);
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
        Intent situationIntent = new Intent(MainActivity.this, SituationDetailActivity.class);
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
        if (itemId == R.id.theme) {
            int isNightMode = AppCompatDelegate.getDefaultNightMode();
            if (isNightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            }
            return true;
        }
        return false;
    }
}