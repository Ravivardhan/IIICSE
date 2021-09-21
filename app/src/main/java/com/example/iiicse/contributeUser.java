package com.example.iiicse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Map;

public class contributeUser extends AppCompatActivity {
    ArrayList<String>list=new ArrayList<>();

    Uri imageuri = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribute_user);


        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"LS", "PPL", "USP","CN","OOAD","MPMC"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

       // Toast.makeText(this, String.valueOf(list), Toast.LENGTH_SHORT).show();




        ImageButton upload=(ImageButton) findViewById(R.id.upload);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText file= (EditText) findViewById(R.id.filename);
                String filename=file.getText().toString();
                if (filename.isEmpty()){

                    Toast.makeText(contributeUser.this, "enter filename", Toast.LENGTH_SHORT).show();
                }
                else {
                    String unit = dropdown.getSelectedItem().toString();
                    list.add(unit);
                    Intent galleryIntent = new Intent();
                    galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

                    // We will be redirected to choose pdf
                    galleryIntent.setType("application/pdf");
                    startActivityForResult(galleryIntent, 1);
                }
            }
        });

        Button sm=(Button)findViewById(R.id.button2);
        sm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference dref = FirebaseDatabase.getInstance().getReference("users");
                String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                String currentemail=FirebaseAuth.getInstance().getCurrentUser().getEmail();

                dref.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                        //String value = dataSnapshot.getValue(String.class);
                        //list.add(String.valueOf(map));

                        //System.out.println(map.get("username").toString());
                        String user= String.valueOf(map.get("username"));
                        String email= String.valueOf(map.get("email"));
                        if(email.equals(currentemail)){
                            list.add(user);
                        }
                        //Toast.makeText(contributeUser.this, String.valueOf(list), Toast.LENGTH_SHORT).show();
                        StorageReference storageReference = FirebaseStorage.getInstance().getReference("IIICSE");
                        StorageReference st=storageReference.child(list.get(0)).child(list.get(1)+"."+"pdf");
                        String link=String.valueOf(st.getDownloadUrl());

                        st.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Log.e("Tuts+", "uri: " + uri.toString());
                                list.add(uri.toString());
                                //Toast.makeText(contributeUser.this, String.valueOf(list), Toast.LENGTH_SHORT).show();
                                //Handle whatever you're going to do with the URL here
                                Pdfinfo info=new Pdfinfo(list.get(2),list.get(3),list.get(0),list.get(1));
                                DatabaseReference dre = FirebaseDatabase.getInstance().getReference("contribution");
                                //Toast.makeText(contributeUser.this, list.get(0), Toast.LENGTH_SHORT).show();
                                dre.child(String.valueOf(list.get(0))).child(String.valueOf(list.get(1))).setValue(info);


                                Intent i=new Intent(contributeUser.this,contribution.class);
                                Toast.makeText(contributeUser.this, "thanks for the contribution", Toast.LENGTH_SHORT).show();
                                startActivity(i);



                            }
                        });


                        //Toast.makeText(contributeUser.this, link, Toast.LENGTH_SHORT).show();
                        //System.out.println(link);



                    }

                    @Override
                    public void onChildChanged(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull  DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {

                    }
                });


    }});}
    ProgressDialog dialog;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            // Here we are initialising the progress dialog box
            dialog = new ProgressDialog(this);
            dialog.setMessage("Uploading");

            // this will show message uploading
            // while pdf is uploading
            dialog.show();
            imageuri = data.getData();
            final String timestamp = "" + System.currentTimeMillis();
            StorageReference storageReference = FirebaseStorage.getInstance().getReference("IIICSE");
            final String messagePushID = timestamp;

            Toast.makeText(contributeUser.this, imageuri.toString(), Toast.LENGTH_SHORT).show();
            Spinner tt=(Spinner)findViewById(R.id.spinner1);
            String unit=tt.getSelectedItem().toString();
            StorageReference storageReference1=storageReference.child(unit);

            EditText filename=findViewById(R.id.filename);
            String file=filename.getText().toString();
            list.add(file);
            Toast.makeText(this, String.valueOf(list), Toast.LENGTH_SHORT).show();
            // Here we are uploading the pdf in firebase storage with the name of current time
            final StorageReference filepath = storageReference1.child(file + "." + "pdf");
           Toast.makeText(contributeUser.this, filepath.getName(), Toast.LENGTH_SHORT).show();
            filepath.putFile(imageuri).continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return filepath.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    FirebaseAuth mauth;
                    mauth=FirebaseAuth.getInstance();

                    if (task.isSuccessful()) {
                        ArrayList<String> list=new ArrayList<>();

                        // After uploading is done it progress
                        // dialog box will be dismissed
                        dialog.dismiss();
                        Uri uri = task.getResult();
                        String myurl;
                        myurl = uri.toString();
                        list.add(myurl);
                        System.out.println(myurl);
                        Toast.makeText(contributeUser.this, String.valueOf(list), Toast.LENGTH_SHORT).show();

                        Toast.makeText(contributeUser.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();




                    } else {
                        dialog.dismiss();
                        Toast.makeText(contributeUser.this, "UploadedFailed", Toast.LENGTH_SHORT).show();
                    }


                }
            });



        }






    }}