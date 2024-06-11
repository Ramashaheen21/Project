package com.example.mobileproject.ActivityPages;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class booking extends AppCompatActivity {

    private CustomerBookingInfo booking;

    private EditText FirstName, LastName, ID, etBirthday, etStartDate, etEndDate;
    private Switch switchTerms;
    private Button  btnPurchase;

    private FirebaseFirestore db;
    private  String carprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bookinginfo);
        btnPurchase = findViewById(R.id.btn_purchase);

        FirstName = findViewById(R.id.first_name);
        LastName = findViewById(R.id.last_name);
        ID = findViewById(R.id.id_number);
        etBirthday = findViewById(R.id.birthday);
        etStartDate = findViewById(R.id.start_date);
        etEndDate = findViewById(R.id.end_date);
        switchTerms = findViewById(R.id.switch_terms);
        btnPurchase = findViewById(R.id.btn_purchase);

        carprice = getIntent().getStringExtra("price");

        db = FirebaseFirestore.getInstance();

        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertFirebasedata();
            }
        });

        // Date picker for birthday
        etBirthday.setOnClickListener(view -> showDatePickerDialog(etBirthday));

        // Date picker for start date
        etStartDate.setOnClickListener(view -> showDatePickerDialog(etStartDate));

        // Date picker for end date
        etEndDate.setOnClickListener(view -> showDatePickerDialog(etEndDate));

    }

    private void insertFirebasedata(){
        String firstName = FirstName.getText().toString().trim();
        String lastName = LastName.getText().toString().trim();
        String id = ID.getText().toString().trim();
        String birthday = etBirthday.getText().toString().trim();
        String startDate = etStartDate.getText().toString().trim();
        String endDate = etEndDate.getText().toString().trim();

        booking = new CustomerBookingInfo(firstName, lastName, id, birthday, startDate, endDate);

        db.collection("CustomerInfo").add(booking)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        if (validateFields()) {

                            Toast.makeText(booking.this, "Booking added with ID: " + documentReference.getId(), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(booking.this, Payment.class);
                            i.putExtra("startDate", startDate);
                            i.putExtra("endDate", endDate);
                            i.putExtra("price", carprice);
                            startActivity(i);
                        }
                        else {
                            // Display a message indicating that all fields must be filled
                            Toast.makeText(booking.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(booking.this, "Error adding booking: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private boolean validateFields() {
        String firstName = FirstName.getText().toString().trim();
        String lastName = LastName.getText().toString().trim();
        String id = ID.getText().toString().trim();
        String birthday = etBirthday.getText().toString().trim();
        String startDate = etStartDate.getText().toString().trim();
        String endDate = etEndDate.getText().toString().trim();

        // Check if any field is empty
        if (firstName.isEmpty() || lastName.isEmpty() || id.isEmpty() || birthday.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
            return false;
        }
        return true;
    }
    private void showDatePickerDialog(final EditText editText) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
            String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
            editText.setText(date);
        }, year, month, day);
        datePickerDialog.show();
    }
}
