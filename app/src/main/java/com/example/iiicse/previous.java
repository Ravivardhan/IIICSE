package com.example.iiicse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class previous extends AppCompatActivity {
    String papers[]=new String[]{"MID Papers","Previous Papers"};
    String pps[]=new String[]{"MID","PP"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous);



        ListView pplist=findViewById(R.id.pplist);


        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,papers);
        pplist.setAdapter(adapter);

        pplist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(previous.this,ppapers.class);
                i.putExtra("paper",pps[position]);
                startActivity(i);

            }
        });




    }
}