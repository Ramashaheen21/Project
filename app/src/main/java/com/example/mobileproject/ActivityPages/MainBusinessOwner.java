package com.example.mobileproject.ActivityPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.R;

public class MainBusinessOwner extends AppCompatActivity {

    private Button mancar;
    private Button manbook;
    private Button btnmain;
    private Button btnLocation;
    private Button btnprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_business_owner);
        setupViews();
    }

    private void setupViews() {
        // Initialize buttons
        mancar = findViewById(R.id.mancar);
        manbook = findViewById(R.id.manbook);
        btnmain = findViewById(R.id.button_main);
        btnprofile = findViewById(R.id.button_profile);
        btnLocation = findViewById(R.id.button_Location);

        mancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainBusinessOwner.this, manageCars.class);
                startActivity(intent);
            }
        });

        manbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainBusinessOwner.this , manageBooking.class);
                startActivity(intent);
            }
        });

        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent i = new Intent(MainBusinessOwner.this, ProfileBusinessOwner.class);
                    startActivity(i);
            }
        });
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainBusinessOwner.this, Report.class);
                startActivity(i);
            }
        });
        btnmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainBusinessOwner.class);
                startActivity(i);
            }
        });
    }
}
