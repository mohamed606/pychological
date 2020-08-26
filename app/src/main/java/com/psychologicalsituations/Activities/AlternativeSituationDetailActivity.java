package com.psychologicalsituations.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.psychologicalsituations.Adapters.SituationDetailAdapter;
import com.psychologicalsituations.Listeners.SituationDetailClickListener;
import com.psychologicalsituations.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AlternativeSituationDetailActivity extends AppCompatActivity implements SituationDetailClickListener {
    private RecyclerView recyclerView;
    private List<String> titles;
    private List<String> details;
    private SituationDetailAdapter adapter;
    private String userLevel;
    private static final int EDIT_RESULT_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternative_situation_detail);
        recyclerView = findViewById(R.id.details_recyclerView);
        setUpButton(getSupportActionBar());
        titles = new ArrayList<>();
        details = new ArrayList<>();
        titles.add(getString(R.string.situation));
        titles.add(getString(R.string.idea));
        titles.add(getString(R.string.emotion));
        titles.add(getString(R.string.behaviour));
        titles.add(getString(R.string.wrong_thinking));
        titles.add(getString(R.string.coent));
        titles.add(getString(R.string.ceontP));
        titles.add(getString(R.string.tdob));
        titles.add(getString(R.string.pd));
        titles.add(getString(R.string.at));
        for (int i = 0; i < titles.size(); i++) {
            details.add("");
        }
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        userLevel = sharedPreferences.getString(getString(R.string.levelKey), "1");
        int adapterSize;
        if (userLevel.equals("1")) {
            adapterSize = 4;
        } else {
            adapterSize = titles.size();
        }
        adapter = new SituationDetailAdapter(titles, details, adapterSize, this, this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setUpButton(ActionBar actionBar) {
        if (actionBar == null) {
            return;
        }
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);
    }

    @Override
    public void goToEditActivity(int position) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("details", details.get(position));
        intent.putExtra("title", titles.get(position));
        startActivityForResult(intent, EDIT_RESULT_CODE);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                returnResultToMain(RESULT_OK);
                return true;
            case R.id.home:
                returnResultToMain(RESULT_CANCELED);
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == EDIT_RESULT_CODE) {
            details.set(data.getIntExtra("position", 0), data.getStringExtra("details"));
            adapter.notifyDataSetChanged();
        }
    }

    private void returnResultToMain(int resultCode) {
        Intent intent = new Intent(this, MainActivity.class);
        if (resultCode == RESULT_OK) {
            intent.putExtra(getString(R.string.situation), details.get(0));
            intent.putExtra(getString(R.string.idea), details.get(1));
            intent.putExtra(getString(R.string.emotion), details.get(2));
            intent.putExtra(getString(R.string.behaviour), details.get(3));
            intent.putExtra(getString(R.string.wrong_thinking), details.get(4));
            intent.putExtra(getString(R.string.coent), details.get(5));
            intent.putExtra(getString(R.string.ceontP), details.get(6));
            intent.putExtra(getString(R.string.tdob), details.get(7));
            intent.putExtra(getString(R.string.pd), details.get(8));
            intent.putExtra(getString(R.string.at), details.get(9));
        }
        setResult(resultCode, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        returnResultToMain(RESULT_OK);
    }
}