package com.example.mobileproject.ActivityPages;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class booking extends AppCompatActivity {

    private CustomerBookingInfo booking;

    private EditText FirstName, LastName, ID, etBirthday, etStartDate, etEndDate, etCardID, etCardExpiration, etCardCVV;
    private Switch switchTerms;
    private Button btnPurchase;

    private FirebaseFirestore db;
    private String carprice, carmod;
    private int year;

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

        etCardID = findViewById(R.id.card_number_edit_text);
        etCardExpiration = findViewById(R.id.card_date);
        etCardCVV = findViewById(R.id.card_cvc);

        carprice = getIntent().getStringExtra("price");
        carmod = getIntent().getStringExtra("carModel");
        year = getIntent().getIntExtra("year", 0);

        db = FirebaseFirestore.getInstance();

        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    insertFirebasedata();
                } else {
                    // Display a message indicating that all fields must be filled
                    Toast.makeText(booking.this, "Please fill in all fields correctly", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Date picker for birthday
        etBirthday.setOnClickListener(view -> showDatePickerDialog(etBirthday));

        // Date picker for start date
        etStartDate.setOnClickListener(view -> showDatePickerDialog(etStartDate));

        // Date picker for end date
        etEndDate.setOnClickListener(view -> showDatePickerDialog(etEndDate));
    }

    private void insertFirebasedata() {
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
                        Toast.makeText(booking.this, "Booking added with ID: " + documentReference.getId(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(booking.this, Payment.class);
                        i.putExtra("startDate", startDate);
                        i.putExtra("endDate", endDate);
                        i.putExtra("price", carprice);
                        i.putExtra("carModel", carmod);
                        i.putExtra("year", year);
                        startActivity(i);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(booking.this, "Error adding booking: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        LinearLayout paymentInfo = findViewById(R.id.paymentInfo);

        // Set the paymentInfo visibility based on the switch state
        switchTerms.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                paymentInfo.setVisibility(View.VISIBLE);
            } else {
                paymentInfo.setVisibility(View.GONE);
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
        String cardID = etCardID.getText().toString().trim();
        String cardExpiration = etCardExpiration.getText().toString().trim();
        String cardCVV = etCardCVV.getText().toString().trim();

        // Check if any field is empty
        if (firstName.isEmpty() || lastName.isEmpty() || id.isEmpty() || birthday.isEmpty() ||
                startDate.isEmpty() || endDate.isEmpty() || cardID.isEmpty() || cardExpiration.isEmpty() || cardCVV.isEmpty()) {
            return false;
        }

        // Validate birthday (customer should be over 18 years old)
        if (!validateBirthday(birthday)) {
            Toast.makeText(this, "Customer must be over 18 years old", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validate card ID
        if (!validateCardID(cardID)) {
            Toast.makeText(this, "Card ID must be 16 digits", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validate card expiration
        if (!validateCardExpiration(cardExpiration)) {
            Toast.makeText(this, "Card expiration must be in the format mm/yy", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validate card CVV
        if (!validateCardCVV(cardCVV)) {
            Toast.makeText(this, "Card CVV must be 3 digits", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean validateBirthday(String birthday) {
        try {
            String[] parts = birthday.split("/");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month - 1, day);

            Calendar currentDate = Calendar.getInstance();
            int age = currentDate.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
            if (currentDate.get(Calendar.DAY_OF_YEAR) < calendar.get(Calendar.DAY_OF_YEAR)) {
                age--;
            }

            return age >= 18;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean validateCardID(String cardID) {
        return cardID.length() == 16 && cardID.matches("\\d{16}");
    }

    private boolean validateCardExpiration(String cardExpiration) {
        Pattern pattern = Pattern.compile("(0[1-9]|1[0-2])/\\d{2}");
        Matcher matcher = pattern.matcher(cardExpiration);
        return matcher.matches();
    }

    private boolean validateCardCVV(String cardCVV) {
        return cardCVV.length() == 3 && cardCVV.matches("\\d{3}");
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
