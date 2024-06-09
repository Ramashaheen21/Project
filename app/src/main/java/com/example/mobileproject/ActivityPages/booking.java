package com.example.mobileproject.ActivityPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.R;

public class booking extends AppCompatActivity {

    EditText firstname;
    EditText lastname;
    EditText ID;
    EditText birthday;

    EditText startDate;

    EditText endDate;

    Switch switchTerm;

    Button addPayment;

    ListView cardInfo;

    Button btnPurchase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bookinginfo);
        btnPurchase = findViewById(R.id.btn_purchase);


        setupViews();
    }

    private void setupViews() {
        firstname = findViewById(R.id.first_name);
        lastname = findViewById(R.id.last_name);
        ID = findViewById(R.id.id_number);
        birthday = findViewById(R.id.birthday);
        startDate = findViewById(R.id.start_date);
        endDate = findViewById(R.id.end_date);
        switchTerm = findViewById(R.id.switch_terms);
        addPayment = findViewById(R.id.btn_add_payment);
        cardInfo = findViewById(R.id.card_info);
        btnPurchase = findViewById(R.id.btn_purchase);

        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(booking.this, Payment.class);
                startActivity(i);
            }
        });

    }
}
