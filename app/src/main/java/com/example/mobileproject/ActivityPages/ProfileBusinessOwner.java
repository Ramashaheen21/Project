package com.example.mobileproject.ActivityPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.R;

public class ProfileBusinessOwner extends AppCompatActivity {
    private Button btnmain;
    private Button btnLocation;
    private Button btnprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_bussiness_owner);
        setupViews();
    }

    private void setupViews() {
        // Initialize buttons

        btnmain = findViewById(R.id.button_main);
        btnprofile = findViewById(R.id.button_profile);
        btnLocation = findViewById(R.id.button_Location);



        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ProfileBusinessOwner.class);
                startActivity(i);
            }
        });
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainBusinessOwner.class);
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
