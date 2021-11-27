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

public class papers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papers);

        Intent i=getIntent();

        Bundle b=i.getExtras();

        String subject=String.valueOf(b.get("sub"));
        String paper=String.valueOf(b.get("paper"));



        DatabaseReference dref;
        ListView listview;
        ArrayList<String> list=new ArrayList<>();
        ArrayList<String> links=new ArrayList<>();


        listview = (ListView) findViewById(R.id.papers);


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        dref = FirebaseDatabase.getInstance().getReference("QP").child(paper);


        dref.child(subject).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                //Toast.makeText(contribution_files.this, String.valueOf(map), Toast.LENGTH_SHORT).show();
                //String value = dataSnapshot.getValue(String.class);
                //list.add(String.valueOf(map));

                System.out.println(map.toString());
                System.out.println(map.get("filename").toString());
                System.out.println(map.get("link").toString());
                String ss=map.get("subject").toString();
                if(subject.equals(ss)) {


                    //System.out.println(map.get("unit").toString());
                    list.add(String.valueOf(map.get("filename")));

                    System.out.println(list);
                    links.add(String.valueOf(map.get("link")));
                }
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

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent i=new Intent(syllabi.this,mat.class);
                //i.putExtra("link", links.get(position));
                //startActivity(i);
                CharSequence options[] = new CharSequence[]{
                        "Download",
                        "View",
                        "Cancel"
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Choose One");

                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // we will be downloading the pdf
                        if (which == 0) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(links.get(position)));
                            startActivity(intent);
                            Toast.makeText(papers.this, "download", Toast.LENGTH_SHORT).show();
                        }
                        // We will view the pdf
                        if (which == 1) {
                            Intent intent = new Intent(view.getContext(), ViewPdfActivity.class);
                            intent.putExtra("url", links.get(position));
                            startActivity(intent);                        }
                    }
                });
                builder.show();
            }
        });








    }
}