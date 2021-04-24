package com.example.roomapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {
    private EditText titlename;
    private EditText descname;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        titlename = findViewById(R.id.title);
        descname = findViewById(R.id.desc);
        numberPicker = findViewById(R.id.numberpicker);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();
        if (intent.hasExtra("id")) {
            setTitle("Edit Note");
            titlename.setText(intent.getStringExtra("title"));
            descname.setText(intent.getStringExtra("desc"));
            numberPicker.setValue(intent.getIntExtra("prority",1));

        } else {
            setTitle("Add Note");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveNote() {
        String title = titlename.getText().toString();
        String desc = descname.getText().toString();
        int prority = numberPicker.getValue();

        if (title.trim().isEmpty() || desc.trim().isEmpty()) {
            Toast.makeText(this, "Please enter title and desc", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent();
        intent.putExtra("title", title);
        intent.putExtra("desc", desc);
        intent.putExtra("prority", prority);

        int id=getIntent().getIntExtra("id",-1);
        if (id!=-1){
            intent.putExtra("id",id);
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
