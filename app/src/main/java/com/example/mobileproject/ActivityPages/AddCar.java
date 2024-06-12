package com.example.mobileproject.ActivityPages;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AddCar extends AppCompatActivity {
    private EditText etId, etBrandModel, etSeatNumber, etColor, etGearType, etPricePerDay, etYear;
    private RecyclerView carListRecyclerView;
    private Button btnAddCar;
    private ArrayList<Coco> carList;
    private CarAdapter carAdapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference colRef = db.collection("fire");
    private boolean addingData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_add_cars);

        carListRecyclerView = findViewById(R.id.car_list);
        etId = findViewById(R.id.et_id);
        etBrandModel = findViewById(R.id.et_brand_model);
        etSeatNumber = findViewById(R.id.et_seat_number);
        etColor = findViewById(R.id.et_color);
        etGearType = findViewById(R.id.et_gear_type);
        etPricePerDay = findViewById(R.id.et_price_per_day);
        etYear = findViewById(R.id.et_year);
        btnAddCar = findViewById(R.id.btn_add_car);

        carList = new ArrayList<>();
        carAdapter = new CarAdapter(this, carList);
        carListRecyclerView.setAdapter(carAdapter);
        carListRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        btnAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                synchronized (AddCar.this) {
                    if (addingData) {
                        // If Firestore operation is in progress, ignore the click
                        return;
                    }

                    addingData = true; // Set flag to indicate Firestore operation is in progress
                }

                String id = etId.getText().toString().trim();
                String brandModel = etBrandModel.getText().toString().trim();
                String seatNumber = etSeatNumber.getText().toString().trim();
                String color = etColor.getText().toString().trim();
                String gearType = etGearType.getText().toString().trim();
                String pricePerDay = etPricePerDay.getText().toString().trim();
                String year = etYear.getText().toString().trim();

                // Validate input and add the car
                if (id.isEmpty() || brandModel.isEmpty() || seatNumber.isEmpty() || color.isEmpty() || gearType.isEmpty() || pricePerDay.isEmpty() || year.isEmpty()) {
                    Toast.makeText(AddCar.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    synchronized (AddCar.this) {
                        addingData = false; // Reset flag
                    }
                    return;
                }

                Coco car = new Coco(id, brandModel, seatNumber, color, gearType, pricePerDay, year);

                // Add the car to Firestore
                colRef.add(car)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                // Data added successfully
                                Toast.makeText(AddCar.this, "Data added to Firestore", Toast.LENGTH_SHORT).show();
                                carList.add(car); // Add car to local list
                                carAdapter.notifyDataSetChanged(); // Notify adapter
                                synchronized (AddCar.this) {
                                    addingData = false; // Reset flag
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Failed to add data
                                Log.d("Error333", e.toString());
                                Toast.makeText(AddCar.this, "Failed to add data to Firestore", Toast.LENGTH_SHORT).show();
                                synchronized (AddCar.this) {
                                    addingData = false; // Reset flag
                                }
                            }
                        });
            }
        });
    }
}
