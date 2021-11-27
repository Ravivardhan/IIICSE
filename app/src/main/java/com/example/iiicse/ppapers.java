package com.example.iiicse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class ppapers extends AppCompatActivity {
    DatabaseReference dref;
    FirebaseDatabase ref;
    String subjects[]=new String[]{"Life Science","Principles of Programming Languages","Computer Networks","Unix and Shell Programming","Object Oriented Analysis and Design","Data Warehousing and Data Mining","Micro Processors and Micro Controllers"};
    String subshort[]=new String[]{"LS","PPL","CN","USP","OOAD","DWDM","MPMC"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ppapers);

        ListView subs = findViewById(R.id.subs);

        Intent i=getIntent();
        Bundle b=i.getExtras();
        String paper=String.valueOf(b.get("paper"));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, subjects);
        subs.setAdapter(adapter);

        subs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ppapers.this, papers.class);
                i.putExtra("sub", subshort[position]);
                i.putExtra("paper",paper);
                startActivity(i);

            }


        });
    }}
