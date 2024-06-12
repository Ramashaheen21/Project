package com.example.mobileproject.ActivityPages;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
public class UpdateCar extends AppCompatActivity {

    private EditText etId;
    private EditText etBrandModel;
    private EditText etSeatNumber;
    private EditText etColor;
    private EditText etGearType;
    private EditText etPricePerDay;
    private EditText etYear;
    private Button btnUpdateCar;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference colRef = db.collection("cars");

    private Coco existingCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_update_cars);

        // Retrieve the selected car object from intent
        existingCar = (Coco) getIntent().getSerializableExtra("selectedCar");

        // Initialize views
        etId = findViewById(R.id.et_id);
        etBrandModel = findViewById(R.id.et_brand_model);
        etSeatNumber = findViewById(R.id.et_seat_number);
        etColor = findViewById(R.id.et_color);
        etGearType = findViewById(R.id.et_gear_type);
        etPricePerDay = findViewById(R.id.et_price_per_day);
        etYear = findViewById(R.id.et_year);
        btnUpdateCar = findViewById(R.id.btn_update_car);

        // Populate EditText fields with existing car data
        if (existingCar != null) {
            etId.setText(existingCar.getId());
            etBrandModel.setText(existingCar.getBrandModel());
            etSeatNumber.setText(existingCar.getSeatNumber());
            etColor.setText(existingCar.getColor());
            etGearType.setText(existingCar.getGearType());
            etPricePerDay.setText(existingCar.getPricePerDay());
            etYear.setText(existingCar.getYear());
        }

        // Update button click listener
        btnUpdateCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get updated values from EditText fields
                String id = etId.getText().toString();
                String brandModel = etBrandModel.getText().toString();
                String seatNumber = etSeatNumber.getText().toString();
                String color = etColor.getText().toString();
                String gearType = etGearType.getText().toString();
                String pricePerDay = etPricePerDay.getText().toString();
                String year = etYear.getText().toString();

                // Update the existing car object with new values
                existingCar.setId(id);
                existingCar.setBrandModel(brandModel);
                existingCar.setSeatNumber(seatNumber);
                existingCar.setColor(color);
                existingCar.setGearType(gearType);
                existingCar.setPricePerDay(pricePerDay);
                existingCar.setYear(year);

                // Call a method to update the car with the provided information
                updateCar(existingCar);
            }
        });
    }

    private void updateCar(Coco car) {
        // Update Firestore document with the updated car data
        colRef.document(car.getId())
                .set(car)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(UpdateCar.this, "Car updated successfully", Toast.LENGTH_SHORT).show();
                        finish(); // Finish the activity after successful update
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("UpdateCar", "Error updating car", e);
                        Toast.makeText(UpdateCar.this, "Failed to update car", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
