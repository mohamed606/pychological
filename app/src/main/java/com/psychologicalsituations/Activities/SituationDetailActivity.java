package com.psychologicalsituations.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.psychologicalsituations.Adapters.SituationDetailAdapter;
import com.psychologicalsituations.Entities.PsychologicalSituation;
import com.psychologicalsituations.Listeners.SituationDetailClickListener;
import com.psychologicalsituations.Pickers.CustomNumberPicker;
import com.psychologicalsituations.R;
import com.psychologicalsituations.Utilits.ActionBarUtility;
import com.psychologicalsituations.Utilits.Constants;
import com.psychologicalsituations.Utilits.RecyclerUtilities;
import com.psychologicalsituations.ViewModels.SituationViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SituationDetailActivity extends AppCompatActivity implements SituationDetailClickListener {
    private RecyclerView recyclerView;
    private List<String> titles;
    private List<String> details;
    private SituationDetailAdapter adapter;
    private String userLevel;
    private static final int EDIT_RESULT_CODE = 2;
    private int situationId = -1;
    private long situationDate = -1;
    private SituationViewModel situationViewModel;
    private static final String TAG = "SituationDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation_detail);

        recyclerView = findViewById(R.id.details_recyclerView);

        ActionBarUtility.setUpButton(getSupportActionBar(), R.drawable.ic_baseline_close_24);

        situationViewModel = new ViewModelProvider(this).get(SituationViewModel.class);

        titles = new ArrayList<>();

        details = new ArrayList<>();

        setUpTitles();

        setUpDetails();

        adapter = new SituationDetailAdapter(titles, details, getAdapterSize(), this, this);

        RecyclerUtilities.setUpRecycler(recyclerView,
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL),
                adapter);
    }

    @Override
    public void goToEditActivity(int position) {
        if (position != 7 && position != 8) {
            Intent intent = new Intent(this, EditActivity.class);
            intent.putExtra("position", position);
            intent.putExtra("details", details.get(position));
            intent.putExtra("title", titles.get(position));
            startActivityForResult(intent, EDIT_RESULT_CODE);
        } else {
            showNumberPickerDialog(position);
        }
    }

    private void showNumberPickerDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.numbers_dialog, null);
        CustomNumberPicker numberPicker = view.findViewById(R.id.degree_numberPicker);
        builder.setView(view)
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        details.set(position, String.valueOf(numberPicker.getValue()));
                        adapter.notifyItemChanged(position);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        builder.setTitle(titles.get(position));
        builder.show();
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
                returnResultToMain(true);
                return true;
            case R.id.home:
                returnResultToMain(false);
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == EDIT_RESULT_CODE) {
            int position = data.getIntExtra("position", 0);
            details.set(position, data.getStringExtra("details"));
            adapter.notifyItemChanged(position);
        }
    }

    private void returnResultToMain(boolean isSaved) {
        if (isSaved) {
            if(details.get(0).trim().isEmpty()){
                Toast.makeText(this,"Situation can't be empty",Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
            PsychologicalSituation situation = getResultedSituation();
            if (situationId != -1) {
                situationViewModel.update(situation);
            } else {
                situationViewModel.insert(situation);
            }
        }
        finish();
    }

    private void populateDetails(Intent data) {
        PsychologicalSituation situation = data.getParcelableExtra(Constants.INTENT_SITUATION);
        details.add(situation.getSituation());
        details.add(situation.getIdea());
        details.add(situation.getEmotion());
        details.add(situation.getBehaviour());
        details.add(situation.getWrongThinking());
        details.add(situation.getCeont());
        details.add(situation.getCeontP());
        details.add(String.valueOf(situation.getDegreeOfBelief()));
        details.add(String.valueOf(situation.getPsychologicalDegree()));
        details.add(situation.getAlternativeThought());
        situationId = situation.getId();
        Date date = situation.getDate();
        if (date != null) {
            situationDate = date.getTime();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        returnResultToMain(true);
    }

    private void setUpTitles() {
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
    }

    private void setUpDetails() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(Constants.INTENT_SITUATION)) {
            setTitle(getString(R.string.edit_situation));
            populateDetails(intent);
        } else {
            setTitle(getString(R.string.add_situation));
            for (int i = 0; i < titles.size(); i++) {
                if (i == 7 || i == 8) {
                    details.add("0");
                } else {
                    details.add("");
                }
            }
        }
    }

    private int getAdapterSize() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        userLevel = sharedPreferences.getString(getString(R.string.levelKey), "1");
        int adapterSize;
        if (userLevel.equals("1")) {
            adapterSize = 4;
        } else {
            adapterSize = titles.size();
        }
        return adapterSize;
    }
    private PsychologicalSituation getResultedSituation(){
      PsychologicalSituation result =  new PsychologicalSituation(details.get(0),
                details.get(1),
                details.get(2),
                details.get(3),
                details.get(4),
                details.get(5),
                details.get(6),
                Double.parseDouble(details.get(7)),
                Double.parseDouble(details.get(8)),
                details.get(9),
                getDate());
      if(situationId != -1){
          result.setId(situationId);
      }
      return result;
    }
    private Date getDate(){
        Date date;
        if(situationDate == -1){
            date  = new Date();
        }else{
            date = new Date(situationDate);
        }
        return date;
    }
}