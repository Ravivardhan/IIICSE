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
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Button reset=findViewById(R.id.reset);
        EditText email=findViewById(R.id.forgotemail);



        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog dialog=new ProgressDialog(ForgotPassword.this);
                dialog.show();
                String emailID=email.getText().toString();

                FirebaseAuth.getInstance().sendPasswordResetEmail(emailID)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    dialog.dismiss();
                                    Toast.makeText(ForgotPassword.this, "check your email to reset your password", Toast.LENGTH_SHORT).show();

                                    Intent i=new Intent(ForgotPassword.this,Login.class);
                                    startActivity(i);
                                }
                                else{
                                    dialog.dismiss();
                                    Toast.makeText(ForgotPassword.this, "something went wrong.. check your email iD", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });




    }
}