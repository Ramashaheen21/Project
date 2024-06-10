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

        mancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainBusinessOwner.this, manageCars.class);
                startActivity(intent);
            }
        });

    }
    }
