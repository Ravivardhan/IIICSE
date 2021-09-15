package com.example.iiicse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class contribution extends AppCompatActivity {
  ListView ls;
  String subjects[]=new String[]{"Life Science","Principles of Programming Languages","Computer Networks","Unix and Shell Programming","Object Oriented Analysis and Design","Data Warehousing and Data Mining","Micro Processors and Micro Controllers"};
  String subshort[]=new String[]{"LS","PPL","CN","USP","OOAD","DWDM","MPMC"};


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contribution);
    ls=(ListView)findViewById(R.id.contrlistview);


    ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,subjects);
    ls.setAdapter(adapter);


    ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i=new Intent(contribution.this,contribution_files.class);
        i.putExtra("subject",subshort[position]);
        startActivity(i);

      }
    });

  }
}