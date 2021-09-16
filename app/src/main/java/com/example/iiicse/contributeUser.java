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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class contributeUser extends AppCompatActivity {

    Uri imageuri = null;
    ArrayList<String> list=new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribute_user);



        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"LS", "PPL", "USP","CN","OOAD","MPMC"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        String unit=dropdown.getSelectedItem().toString();




        ImageButton upload=(ImageButton) findViewById(R.id.upload);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

                // We will be redirected to choose pdf
                galleryIntent.setType("application/pdf");
                startActivityForResult(galleryIntent, 1);




                FirebaseAuth mauth;
                mauth=FirebaseAuth.getInstance();

                DatabaseReference mref= FirebaseDatabase.getInstance().getReference("contribution").child(unit);

                DatabaseReference mref2=FirebaseDatabase.getInstance().getReference("users");

                mref2.child(mauth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot snapshot) {
                        String username=snapshot.child("username").getValue().toString();
                        list.add(0,username);

                    }

                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {

                    }
                });


                Pdfinfo info=new Pdfinfo(list.get(0),list.get(1),unit,list.get(2));

                mref2.child(list.get(2)).setValue(info);

            }
        });


    }
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
            // Here we are uploading the pdf in firebase storage with the name of current time
            final StorageReference filepath = storageReference1.child(file + "." + "pdf");
            list.add(2,file);
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
                    if (task.isSuccessful()) {

                        // After uploading is done it progress
                        // dialog box will be dismissed
                        dialog.dismiss();
                        Uri uri = task.getResult();
                        String myurl;
                        myurl = uri.toString();
                        list.add(1,myurl);


                        Toast.makeText(contributeUser.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        dialog.dismiss();
                        Toast.makeText(contributeUser.this, "UploadedFailed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }}