package com.example.mobileproject.ActivityPages;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DeleteCar extends AppCompatActivity {

    private EditText etId;
    private Button btnDeleteCar;
    private RecyclerView carList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference colRef = db.collection("fire");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_delete_cars);
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
                // Delete car by ID
                deleteCarById(id);
            }
        });
    }

    private void deleteCarById(final String id) {
        // Locate the car document in Firestore using its ID
        colRef.document(id)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Car deleted successfully
                        Toast.makeText(DeleteCar.this, "Car with ID " + id + " deleted", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to delete car
                        Toast.makeText(DeleteCar.this, "Failed to delete car with ID " + id, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
