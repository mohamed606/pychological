package com.psychologicalsituations.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.psychologicalsituations.R;
import com.psychologicalsituations.Utilits.ActionBarUtility;

public class EditActivity extends AppCompatActivity {
    private EditText details;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        TextView title = findViewById(R.id.edit_title_tv);
        details = findViewById(R.id.edit_details_editTex);
        ActionBarUtility.setUpButton(getSupportActionBar(), R.drawable.ic_baseline_arrow_back_24);
        Intent intent = getIntent();
        if (intent != null) {
            title.setText(intent.getStringExtra("title"));
            details.setText(intent.getStringExtra("details"));
            position = intent.getIntExtra("position", 0);
        }
        setTitle(getString(R.string.edit) + " " + title.getText());
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
        Intent intent = new Intent(this, SituationDetailActivity.class);
        intent.putExtra("details", details.getText().toString());
        intent.putExtra("position", position);
        setResult(RESULT_OK, intent);
        finish();
    }
}