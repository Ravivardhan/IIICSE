package com.example.iiicse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.stream.Stream;

public class imp extends AppCompatActivity {
    ArrayList<String> list=new ArrayList<>();
    ArrayList<String> user=new ArrayList<>();
    ArrayList<String> msguser=new ArrayList<>();

    ArrayList<String> message=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imp);

        EditText msg1 = findViewById(R.id.message);

        ImageButton send = (ImageButton) findViewById(R.id.send);


        ListView listview = (ListView) findViewById(R.id.liveview);


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        DatabaseReference dref = FirebaseDatabase.getInstance().getReference("chatroom");


        dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                //Toast.makeText(contribution_files.this, String.valueOf(map), Toast.LENGTH_SHORT).show();
                //String value = dataSnapshot.getValue(String.class);
                //list.add(String.valueOf(map));
                System.out.println(map.toString());
                System.out.println(map.get("user").toString());
                System.out.println(map.get("message").toString());


                list.add(String.valueOf(map.get("user")) + " :  " + String.valueOf(map.get("message")));

                System.out.println(list);
                user.add(String.valueOf(map.get("user")));
                message.add(String.valueOf(map.get("message")));
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


        //message sending code here.....


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(msg1.getText().toString().equals("")){
                    Toast.makeText(imp.this, "message couldn't be blank", Toast.LENGTH_SHORT).show();
                }
                else{

                    String msg = msg1.getText().toString();

                    String username;


                    String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();


                    DatabaseReference dref = FirebaseDatabase.getInstance().getReference("users");


                    dref.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                            //Toast.makeText(contribution_files.this, String.valueOf(map), Toast.LENGTH_SHORT).show();
                            //String value = dataSnapshot.getValue(String.class);

                            if (String.valueOf(map.get("email")).equals(String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getEmail()))) {
                                String usernamemsg=String.valueOf(map.get("username"));


                                usermessage usermessage = new usermessage(usernamemsg, msg);


                                DatabaseReference ref=FirebaseDatabase.getInstance().getReference("chatroom");


                                ref
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                // get total available quest
                                                int size = (int) dataSnapshot.getChildrenCount();
                                                //Toast.makeText(imp.this, String.valueOf(size), Toast.LENGTH_SHORT).show();
                                                if(size<10){
                                                    String s="0"+String.valueOf(size);
                                                    ref.child("message"+"0"+String.valueOf(size)).setValue(usermessage);
                                                }
                                                else {
                                                    ref.child("message" + String.valueOf(size)).setValue(usermessage);
                                                }
                                                msg1.setText("");
                                            }
                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });


                            }

                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



                }




            }
        });
    }}