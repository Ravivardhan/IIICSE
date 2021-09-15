package com.example.iiicse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class units extends AppCompatActivity {
    ListView units;
    String u[]=new String[]{"Unit-I","Unit-II","Unit-III","Unit-IV","Unit-V"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_units);

        units=(ListView) findViewById(R.id.unit);

        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,u);
        units.setAdapter(adapter2);

        Intent i=getIntent();
        Bundle b=i.getExtras();

        Toast.makeText(units.this,String.valueOf( b.get("subject")), Toast.LENGTH_SHORT).show();

        units.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(units.this,syllabi.class);
                i.putExtra("subject",String.valueOf(b.get("subject")));
                i.putExtra("unit",u[position]);
                startActivity(i);
            }
        });
    }
}