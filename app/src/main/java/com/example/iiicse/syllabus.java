package com.example.iiicse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class syllabus extends AppCompatActivity {

    String subjects[]=new String[]{"Life Science","Principles of Programming Languages","Computer Networks","Unix and Shell Programming","Object Oriented Analysis and Design","Micro Processors and Micro Controllers"};
    String subshort[]=new String[]{"LS","PPL","CN","USP","OOAD","MPMC"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);

        ListView syllabus_subjects=(ListView)findViewById(R.id.syllabus_subjects);


        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,subjects);
        syllabus_subjects.setAdapter(adapter);


        syllabus_subjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(syllabus.this,syllabuscopy.class);
                i.putExtra("subject",subshort[position]);
                startActivity(i);

            }
        });



    }
}