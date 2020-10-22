package com.psychologicalsituations.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.psychologicalsituations.Adapters.SituationAdapter;
import com.psychologicalsituations.Entities.PsychologicalSituation;
import com.psychologicalsituations.Listeners.SituationClickListener;
import com.psychologicalsituations.R;
import com.psychologicalsituations.Utilits.Constants;
import com.psychologicalsituations.Utilits.LanguageUtilities;
import com.psychologicalsituations.Utilits.OrientationUtils;
import com.psychologicalsituations.Utilits.RecyclerUtilities;
import com.psychologicalsituations.ViewModels.SituationViewModel;

public class MainActivity extends AppCompatActivity implements SituationClickListener {
    private FloatingActionButton addFab;
    private RecyclerView situationRecycler;
    private SituationAdapter adapter;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private SituationViewModel situationViewModel;
    private static final int CHANGE_LANGUAGE_REQUEST_CODE = 3;
    private CardView cardView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFab = findViewById(R.id.add_situation_fab);

        situationRecycler = findViewById(R.id.rc_situations);

        cardView = findViewById(R.id.card);

        adapter = new SituationAdapter(this);

        situationViewModel = new ViewModelProvider(this).get(SituationViewModel.class);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        editor = sharedPreferences.edit();

        LanguageUtilities.setUpActivityLanguage(this,sharedPreferences.getString(getString(R.string.language_key), "en"));
        boolean isFirstTime = sharedPreferences.getBoolean("isFirstTime", true);
        showFirstTimeDialog(isFirstTime);
        showFirstTimeHint(isFirstTime);
        if(isFirstTime){
            editor.putBoolean("isFirstTime", false);
            editor.apply();
        }else {
            cardView.setVisibility(View.GONE);
        }
        addClickListenerForFab();

        RecyclerUtilities.setUpRecycler(situationRecycler,
                new StaggeredGridLayoutManager(OrientationUtils.getNumberOfColumns(this), StaggeredGridLayoutManager.VERTICAL),
                adapter);

        addItemTouchHelper();

        situationViewModel.getAllSituations().observe(this,
                psychologicalSituations -> {
                    adapter.submitList(psychologicalSituations);
                }
        );
        setTitle(R.string.app_name);
    }

    private void showFirstTimeHint(boolean isFirstTime) {
        if(isFirstTime){
            startApplicationHint();
        }
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

    private void addItemTouchHelper() {
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
    public void goToDetailActivity(PsychologicalSituation situation) {
        Intent intent = new Intent(MainActivity.this, SituationDetailActivity.class);
        intent.putExtra(Constants.INTENT_SITUATION, situation);
        startActivity(intent);
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
        if (requestCode == CHANGE_LANGUAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            String language = sharedPreferences.getString(getString(R.string.language_key), "en");
            LanguageUtilities.changeLanguage(this, language);
        }
    }

    private void showFirstTimeDialog(boolean isFirstTime) {
        if (isFirstTime) {
            firstTimeDialog();
        }
    }
    private void startApplicationHint(){
        TapTarget forCard = createTapTargetForCard();
        new TapTargetSequence(this).targets(createTapTargetForFab(),forCard).listener(new TapTargetSequence.Listener() {
            @Override
            public void onSequenceFinish() {

            }
            @Override
            public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {
                if(lastTarget == forCard && targetClicked){
                    cardView.setVisibility(View.GONE);
                }
            }
            @Override
            public void onSequenceCanceled(TapTarget lastTarget) {

            }
        }).start();
    }
    private TapTarget createTapTargetForFab(){
       return TapTarget.forView(addFab, getString(R.string.add_button), getString(R.string.add_button_hint))
                // All options below are optional
                .outerCircleColor(R.color.blue)
                .outerCircleAlpha(0.96f)
                .targetCircleColor(R.color.white)
                .titleTextSize(20)
                .descriptionTextSize(10)
                .textColor(R.color.black)
                .textTypeface(Typeface.SANS_SERIF)
                .drawShadow(true)
                .cancelable(false)
                .transparentTarget(true)
                .targetRadius(60)  ;
    }
    private TapTarget createTapTargetForCard(){
        return  TapTarget.forView(cardView, getString(R.string.this_is_situation_card), getString(R.string.situation_card_hint))
                // All options below are optional
                .outerCircleColor(R.color.blue)
                .outerCircleAlpha(0.96f)
                .targetCircleColor(R.color.white)
                .titleTextSize(20)
                .descriptionTextSize(10)
                .textColor(R.color.black)
                .textTypeface(Typeface.SANS_SERIF)
                .drawShadow(true)
                .cancelable(false)
                .transparentTarget(true)
                .targetRadius(80) ;
    }
}