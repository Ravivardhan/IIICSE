package com.example.iiicse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class contribution_files extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribution_files);


        Intent i=getIntent();
        Bundle b=i.getExtras();

        Toast.makeText(this, String.valueOf(b.get("subject")), Toast.LENGTH_SHORT).show();
    }
}