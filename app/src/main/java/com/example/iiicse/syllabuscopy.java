package com.example.iiicse;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class syllabuscopy extends AppCompatActivity {

    DatabaseReference dref;
    ListView listview;
    ArrayList<String> list=new ArrayList<>();
    ArrayList<String> links=new ArrayList<>();
    ArrayList<String> by=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabuscopy);

        ListView sc=(ListView)findViewById(R.id.syllabuscopy);
        Intent i=getIntent();
        Bundle b=i.getExtras();

        String subject=String.valueOf(b.get("subject"));




        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        dref = FirebaseDatabase.getInstance().getReference("syllabus");


        dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                //Toast.makeText(contribution_files.this, String.valueOf(map), Toast.LENGTH_SHORT).show();
                //String value = dataSnapshot.getValue(String.class);
                //list.add(String.valueOf(map));
                System.out.println(map.toString());
                System.out.println(map.get("filename").toString());
                //System.out.println(map.get("by").toString());
               // by.add(String.valueOf(map.get("by")));
                System.out.println(map.get("link").toString());
                if(map.get("subject").toString().equals(subject)) {
                    //System.out.println(map.get("unit").toString());
                    list.add(String.valueOf(map.get("filename")));

                    //System.out.println(list);
                    links.add(String.valueOf(map.get("link")));

                    Intent intent = new Intent(syllabuscopy.this, ViewPdfActivity.class);
                    intent.putExtra("url",map.get("link").toString());
                    startActivity(intent);

                }
                sc.setAdapter(adapter);


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



















    }
}