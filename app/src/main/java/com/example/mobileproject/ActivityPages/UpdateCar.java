package com.example.mobileproject.ActivityPages;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.R;

public class UpdateCar extends AppCompatActivity {

    private EditText etId;
    private EditText etBrandModel;
    private EditText etSeatNumber;
    private EditText etColor;
    private EditText etGearType;
    private EditText etPricePerDay;
    private EditText etYear;
    private Button btnUpdateCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_update_cars); // Ensure this matches your actual XML filename
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
        btnUpdateCar = findViewById(R.id.btn_update_car);

        btnUpdateCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString();
                String brandModel = etBrandModel.getText().toString();
                String seatNumber = etSeatNumber.getText().toString();
                String color = etColor.getText().toString();
                String gearType = etGearType.getText().toString();
                String pricePerDay = etPricePerDay.getText().toString();
                String year = etYear.getText().toString();

                // Call a method to update the car with the provided information
                updateCar(id, brandModel, seatNumber, color, gearType, pricePerDay, year);
            }
        });
    }

    private void updateCar(String id, String brandModel, String seatNumber, String color, String gearType, String pricePerDay, String year) {
        // Add your logic here to update the car with the provided information
    }
}
