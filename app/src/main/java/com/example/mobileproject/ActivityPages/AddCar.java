package com.example.mobileproject.ActivityPages;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.R;

public class AddCar extends AppCompatActivity {
    private EditText etId, etBrandModel, etSeatNumber, etColor, etGearType, etPricePerDay, etYear;
    private Button btnAddCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_add_cars);

        setupViews();
    }

    private void setupViews() {
        etId = findViewById(R.id.et_id);
        etBrandModel = findViewById(R.id.et_brand_model);
        etSeatNumber = findViewById(R.id.et_seat_number);
        etColor = findViewById(R.id.et_color);
        etGearType = findViewById(R.id.et_gear_type);
        etPricePerDay = findViewById(R.id.et_price_per_day);
        etYear = findViewById(R.id.et_year);
        btnAddCar = findViewById(R.id.btn_add_car);

        btnAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCar();
            }
        });
    }

    private void addCar() {
        String id = etId.getText().toString().trim();
        String brandModel = etBrandModel.getText().toString().trim();
        String seatNumber = etSeatNumber.getText().toString().trim();
        String color = etColor.getText().toString().trim();
        String gearType = etGearType.getText().toString().trim();
        String pricePerDay = etPricePerDay.getText().toString().trim();
        String year = etYear.getText().toString().trim();

        // Validate input and add the car (You can add the logic for adding a car here)
        if (id.isEmpty() || brandModel.isEmpty() || seatNumber.isEmpty() || color.isEmpty() || gearType.isEmpty() || pricePerDay.isEmpty() || year.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else {
            // Example of adding car logic
            Toast.makeText(this, "Car added successfully", Toast.LENGTH_SHORT).show();
            // Reset fields or do further actions
        }
    }
}