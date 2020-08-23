package com.psychologicalsituations.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation_detail);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
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
}