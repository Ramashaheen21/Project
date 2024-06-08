package com.example.mobileproject;

import android.os.Bundle;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.ActivityPages.Login;
import com.example.mobileproject.ActivityPages.Splash;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent loginIntent = new Intent(MainActivity.this, Splash.class);
        startActivity(loginIntent);
        finish();
    }
}