package com.example.mobileproject.ActivityPages;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.R;

public class profile extends AppCompatActivity {
    private Button menu_Booking;
    private Button menu_liked;
    private Button menu_home;
    private Button menu_account;
    private Button logout;
    private TextView infoAboutUs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        menu_Booking = findViewById(R.id.button_booking);
        menu_liked = findViewById(R.id.button_Location);
        menu_home = findViewById(R.id.button_main);
        menu_account = findViewById(R.id.button_profile);
        infoAboutUs = findViewById(R.id.infoAboutUs);


        logout = findViewById(R.id.logout_btn);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logoutintent = new Intent(profile.this,Login.class);
                startActivity(logoutintent);
            }
        });

        menu_liked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, HomePage.class);
                startActivity(intent);
            }
        });

        menu_Booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(profile.this, booking.class);
                startActivity(i);
            }
        });

        menu_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), HomePage.class);
                startActivity(i);
            }
        });

        menu_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(profile.this, HomePage.class);
                startActivity(i);
            }
        });

        infoAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, aboutus.class);
                startActivity(intent);
            }
        });
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}