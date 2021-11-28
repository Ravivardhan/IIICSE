package com.example.iiicse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProgressDialog dialog;
        dialog= new ProgressDialog(register.this);
        dialog.setMessage("Registering");




        FirebaseAuth mauth;
        DatabaseReference ref;
        setContentView(R.layout.activity_register);

        Button register=findViewById(R.id.register);

        EditText username=findViewById(R.id.regusername);
        EditText email=findViewById(R.id.regemail);
        EditText pwd=findViewById(R.id.regpassword);
       // EditText confirmpwd=findViewById(R.id.regconfirmpwd);
        mauth= FirebaseAuth.getInstance();

        ref= FirebaseDatabase.getInstance().getReference("users");



       // String regconfirmpwd=confirmpwd.getText().toString();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                String reguser=username.getText().toString();
                String regemail=email.getText().toString();
                String regpwd=pwd.getText().toString();

                userdetails user=new userdetails(reguser,regemail,regpwd);
                if(regpwd.length()>6)
                {
                    if(!reguser.isEmpty()) {
                        mauth.createUserWithEmailAndPassword(regemail, regpwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    ref.child(String.valueOf(mauth.getCurrentUser().getUid())).setValue(user);
                                    dialog.dismiss();

                                    Toast.makeText(register.this, "Signup successful", Toast.LENGTH_SHORT).show();

                                    Intent i = new Intent(register.this, MainActivity2.class);
                                    startActivity(i);
                                } else {
                                    dialog.dismiss();
                                    Toast.makeText(register.this, "Signup failed...", Toast.LENGTH_SHORT).show();
                                }
                            }

                        });
                    }
                    else {
                        dialog.dismiss();
                        Toast.makeText(register.this, "username can't be null", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    dialog.dismiss();
                    Toast.makeText(register.this, "password must contain atleast 6 characters", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}