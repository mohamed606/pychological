package com.psychologicalsituations.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.psychologicalsituations.R;

public class EditActivity extends AppCompatActivity {
    private TextView title;
    private EditText details;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        title = findViewById(R.id.edit_title_tv);
        details = findViewById(R.id.edit_details_editTex);
        setUpButton(getSupportActionBar());
        Intent intent = getIntent();
        if (intent != null) {
            title.setText(intent.getStringExtra("title"));
            details.setText(intent.getStringExtra("details"));
            position = intent.getIntExtra("position", 0);
        }
    }

    @Override
    public void onBackPressed() {
        saveDetail();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        saveDetail();
        return true;
    }

    private void saveDetail() {
        Intent intent = new Intent(this, AlternativeSituationDetailActivity.class);
        intent.putExtra("details", details.getText().toString());
        intent.putExtra("position", position);
        startActivity(intent);
    }

    private void setUpButton(ActionBar actionBar) {
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}