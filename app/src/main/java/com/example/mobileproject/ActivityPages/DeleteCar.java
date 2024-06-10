package com.example.mobileproject.ActivityPages;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;

public class DeleteCar extends AppCompatActivity {

    private EditText etId;
    private Button btnDeleteCar;
    private RecyclerView carList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_delete_cars); // Ensure this matches your actual XML filename
        setupViews();
    }

    private void setupViews() {
        etId = findViewById(R.id.et_id);
        btnDeleteCar = findViewById(R.id.btn_delete_car);
        carList = findViewById(R.id.car_list);

        btnDeleteCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString();
                // Add code to delete car by id
                deleteCarById(id);
            }
        });

        // Initialize RecyclerView here if needed
    }

    private void deleteCarById(String id) {
        // Add logic to delete car by id
        // For example, remove item from the list and update the RecyclerView
    }
}

