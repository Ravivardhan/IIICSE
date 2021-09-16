package com.example.iiicse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class contribution_files extends AppCompatActivity {
    DatabaseReference dref;
    ListView listview;
    ArrayList<String> list=new ArrayList<>();
    ArrayList<String> links=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribution_files);
        Intent i=getIntent();
        Bundle b=i.getExtras();
        String sub=String.valueOf(b.get("subject"));

        listview = (ListView) findViewById(R.id.filelistview);


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        dref = FirebaseDatabase.getInstance().getReference("contribution");


        dref.child(sub).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                Toast.makeText(contribution_files.this, String.valueOf(map), Toast.LENGTH_SHORT).show();
                //String value = dataSnapshot.getValue(String.class);
                //list.add(String.valueOf(map));
                System.out.println(map.toString());
                System.out.println(map.get("filename").toString());
                System.out.println(map.get("by").toString());
                System.out.println(map.get("link").toString());
                System.out.println(map.get("unit").toString());
                list.add(String.valueOf(map.get("filename")));

                System.out.println(list);
                links.add(String.valueOf(map.get("link")));
                listview.setAdapter(adapter);


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

        //listview on click listener....


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(contribution_files.this,pdf.class);
                i.putExtra("link", links.get(position));
                startActivity(i);
            }
        });



    }}