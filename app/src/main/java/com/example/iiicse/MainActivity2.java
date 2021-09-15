package com.example.iiicse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity2 extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);





            // drawer layout instance to toggle the menu icon to open
            // drawer and back button to close drawer
            drawerLayout = findViewById(R.id.my_drawer_layout);
            actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

            // pass the Open and Close toggle for the drawer layout listener
            // to toggle the button
            drawerLayout.addDrawerListener(actionBarDrawerToggle);
            actionBarDrawerToggle.syncState();

            // to make the Navigation drawer icon always appear on the action bar
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView=(NavigationView)findViewById(R.id.nav_drawer);
        navigationView.setNavigationItemSelectedListener((menuItem) -> {
            switch (menuItem.getItemId())
            {
                case R.id.nav_account:
                    Intent i=new Intent(MainActivity2.this,subjects.class);
                    startActivity(i);

                    return true;

                case R.id.nav_settings:
                    Intent j=new Intent(MainActivity2.this,previous.class);
                    startActivity(j);
                    break;
                case R.id.nav_logout:
                    Intent k=new Intent(MainActivity2.this,imp.class);
                    startActivity(k);
                    break;
                case R.id.developer:
                    Intent l=new Intent(MainActivity2.this,developer.class);
                    startActivity(l);
                    break;
                case R.id.contribute:
                    Intent n=new Intent(MainActivity2.this,contribution.class);
                    startActivity(n);
                    break;
            }
            return true;
        });
        }

        // override the onOptionsItemSelected()
        // function to implement
        // the item click listener callback
        // to open and close the navigation
        // drawer when the icon is clicked
        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {


            if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
                return true;
            }


            return super.onOptionsItemSelected(item);

            }




        }

