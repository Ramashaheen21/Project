package com.example.mobileproject.ActivityPages;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.R;

import java.util.Calendar;

public class checkoutinfo extends AppCompatActivity {

    private EditText FirstName, LastName, ID, etBirthday, etStartDate, etEndDate;
    private Switch switchTerms;
    private Button btnAddPayment, btnPurchase;
    private ListView lvCardInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bookinginfo);

        FirstName = findViewById(R.id.first_name);
        LastName = findViewById(R.id.last_name);
        ID = findViewById(R.id.id_number);
        etBirthday = findViewById(R.id.birthday);
        etStartDate = findViewById(R.id.start_date);
        etEndDate = findViewById(R.id.end_date);
        switchTerms = findViewById(R.id.switch_terms);
      // btnAddPayment = findViewById(R.id.btn_add_payment);
        btnPurchase = findViewById(R.id.btn_purchase);
     //   lvCardInfo = findViewById(R.id.card_info);

        // Date picker for birthday
        etBirthday.setOnClickListener(view -> showDatePickerDialog(etBirthday));

        // Date picker for start date
        etStartDate.setOnClickListener(view -> showDatePickerDialog(etStartDate));

        // Date picker for end date
        etEndDate.setOnClickListener(view -> showDatePickerDialog(etEndDate));

        // Show/hide add payment button and list view based on switch
        switchTerms.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                btnAddPayment.setVisibility(View.VISIBLE);
                lvCardInfo.setVisibility(View.VISIBLE);
                btnPurchase.setVisibility(View.VISIBLE);
            } else {
                btnAddPayment.setVisibility(View.GONE);
                lvCardInfo.setVisibility(View.GONE);
                btnPurchase.setVisibility(View.GONE);
            }
        });

        // Add payment method button logic
        btnAddPayment.setOnClickListener(view -> {
            // Logic to add payment method and display in ListView
        });

        // Purchase button logic
        btnPurchase.setOnClickListener(view -> {
            // Logic to handle purchase
        });
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
