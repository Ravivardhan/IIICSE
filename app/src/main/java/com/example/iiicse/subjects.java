package com.example.iiicse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class subjects extends AppCompatActivity {
    ListView sub;
    String subjects[]=new String[]{"Life Science","Principles of Programming Languages","Computer Networks","Unix and Shell Programming","Object Oriented Analysis and Design","Micro Processors and Micro Controllers"};
    String subshort[]=new String[]{"LS","PPL","CN","USP","OOAD","MPMC"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);
        sub=(ListView) findViewById(R.id.listviewsub);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,subjects);
        sub.setAdapter(adapter);

        sub.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(subjects.this,units.class);
                i.putExtra("subject",subshort[position]);
                startActivity(i);

            }
        });



    }
}