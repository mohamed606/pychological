package com.psychologicalsituations.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telecom.Call;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.psychologicalsituations.Adapters.SituationDetailAdapter;
import com.psychologicalsituations.Listeners.SituationDetailClickListener;
import com.psychologicalsituations.R;

import java.util.ArrayList;
import java.util.List;

public class AlternativeSituationDetailActivity extends AppCompatActivity implements SituationDetailClickListener {
    private RecyclerView recyclerView;
    private List<String> titles;
    private List<String> details;
    private String userLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternative_situation_detail);
        titles = new ArrayList<>();
        details = new ArrayList<>();
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
        recyclerView = findViewById(R.id.details_recyclerView);
        int adapterSize;
        if (userLevel.equals("1")) {
            adapterSize = 4;
        } else {
            adapterSize = titles.size();
        }
        Intent intent = getIntent();
        if (intent != null) {
            details.set(intent.getIntExtra("position", 0), intent.getStringExtra("details"));
        }
        SituationDetailAdapter adapter = new SituationDetailAdapter(titles, details, adapterSize, this, this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void goToEditActivity(int position) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("details", details.get(position));
        intent.putExtra("title", titles.get(position));
        startActivity(intent);
    }
}