package com.example.iiicse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    Button login;
    FirebaseAuth mauth;
    EditText uname;
    EditText pwd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login=findViewById(R.id.login);
        uname=findViewById(R.id.loginusername);
        pwd=findViewById(R.id.loginpassword);
        mauth=FirebaseAuth.getInstance();
        TextView signuph=findViewById(R.id.signuph);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=uname.getText().toString();
                String pass=pwd.getText().toString();
                mauth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull  Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(Login.this,MainActivity2.class);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(Login.this, "Invalid Login credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        signuph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this,register.class);
                startActivity(i);
            }
        });

    }
}