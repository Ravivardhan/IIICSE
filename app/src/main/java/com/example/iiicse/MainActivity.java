package com.example.iiicse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIME_OUT=4000;
    FirebaseAuth mauth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user=FirebaseAuth.getInstance().getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(user!=null) {
                    Intent i = new Intent(MainActivity.this,
                            MainActivity2.class);

                    startActivity(i);
                }
                else{
                    Intent j=new Intent(MainActivity.this,Login.class);
                    startActivity(j);
                }
                finish();
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}