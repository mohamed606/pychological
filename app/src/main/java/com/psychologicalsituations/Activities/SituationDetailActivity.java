package com.psychologicalsituations.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.psychologicalsituations.R;

public class SituationDetailActivity extends AppCompatActivity {
    private EditText situationEditText;
    private EditText ideaEditText;
    private EditText emotionEditText;
    private EditText behaviourEditText;
    private EditText wrongThinkingEditText;
    private EditText ceontEditText; //Confirming evidence of negative thought
    private EditText ceontPositiveEditText; //Contradicting evidence of negative thought
    private EditText tdobEditText; //The degree of belief
    private EditText pdEditText; //Psychological degree
    private EditText atEditText; //Alternative thought
    private TextView situationTextView;
    private TextView ideaTextView;
    private TextView emotionTextView;
    private TextView behaviourTextView;
    private TextView wrongThinkingTextView;
    private TextView ceontTextView;
    private TextView ceontPositiveTextView;
    private TextView tdobTextView;
    private TextView pdTextView;
    private TextView atTextView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation_detail);
        setUpButton(getSupportActionBar());
        situationEditText = findViewById(R.id.s_detail_editText);
        ideaEditText = findViewById(R.id.i_detail_editText);
        emotionEditText = findViewById(R.id.e_detail_editText);
        behaviourEditText = findViewById(R.id.b_detail_editText);
        wrongThinkingEditText = findViewById(R.id.wt_detail_editText);
        ceontEditText = findViewById(R.id.ceont_detail_editText);
        ceontPositiveEditText = findViewById(R.id.ceontP_detail_editText);
        tdobEditText = findViewById(R.id.tdob_detail_editText);
        pdEditText = findViewById(R.id.pd_detail_editText);
        atEditText = findViewById(R.id.at_detail_editText);
        situationTextView = findViewById(R.id.s_detail_textView);
        ideaTextView = findViewById(R.id.i_detail_textView);
        emotionTextView = findViewById(R.id.e_detail_textView);
        behaviourTextView = findViewById(R.id.b_detail_textView);
        wrongThinkingTextView = findViewById(R.id.wt_detail_textView);
        ceontTextView = findViewById(R.id.ceont_detail_textView);
        ceontPositiveTextView = findViewById(R.id.ceontP_detail_textView);
        tdobTextView = findViewById(R.id.tdob_detail_textView);
        pdTextView = findViewById(R.id.pd_detail_textView);
        atTextView = findViewById(R.id.at_detail_textView);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        showViewAccordingToLevel(Integer.parseInt(sharedPreferences.getString(getString(R.string.levelKey), "1")));
    }

    private void setUpButton(ActionBar actionBar) {
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.save:
                Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            case R.id.delete:
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            default:
                return false;
        }
    }

    private void showViewAccordingToLevel(int level) {
        if (level == 1) {
            wrongThinkingEditText.setVisibility(View.GONE);
            ceontEditText.setVisibility(View.GONE);
            ceontPositiveEditText.setVisibility(View.GONE);
            tdobEditText.setVisibility(View.GONE);
            pdEditText.setVisibility(View.GONE);
            atEditText.setVisibility(View.GONE);
            wrongThinkingTextView.setVisibility(View.GONE);
            ceontTextView.setVisibility(View.GONE);
            ceontPositiveTextView.setVisibility(View.GONE);
            tdobTextView.setVisibility(View.GONE);
            pdTextView.setVisibility(View.GONE);
            atTextView.setVisibility(View.GONE);
        } else if (level == 2) {
            wrongThinkingEditText.setVisibility(View.VISIBLE);
            ceontEditText.setVisibility(View.VISIBLE);
            ceontPositiveEditText.setVisibility(View.VISIBLE);
            tdobEditText.setVisibility(View.VISIBLE);
            pdEditText.setVisibility(View.VISIBLE);
            atEditText.setVisibility(View.VISIBLE);
            wrongThinkingTextView.setVisibility(View.VISIBLE);
            ceontTextView.setVisibility(View.VISIBLE);
            ceontPositiveTextView.setVisibility(View.VISIBLE);
            tdobTextView.setVisibility(View.VISIBLE);
            pdTextView.setVisibility(View.VISIBLE);
            atTextView.setVisibility(View.VISIBLE);
        }
    }
}