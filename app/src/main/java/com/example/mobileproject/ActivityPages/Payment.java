package com.example.mobileproject.ActivityPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Payment extends AppCompatActivity {

    private TextView datetxt;
    private TextView pricePerDay,priceThrough,totalprice;

    private Button continuebtn;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_complete);

        pricePerDay = findViewById(R.id.PriceDayText);
        priceThrough = findViewById(R.id.PriceAllDaysText);
        totalprice = findViewById(R.id.totalPrice);
        datetxt = findViewById(R.id.datesText);
        // In Payment activity
        String startDate = getIntent().getStringExtra("startDate");
        String endDate = getIntent().getStringExtra("endDate");
        double price = getIntent().getDoubleExtra("price", 0.0);

        datetxt.setText(startDate+" - "+endDate);
        pricePerDay.setText(String.valueOf(price));
        // Parse start date and end date using SimpleDateFormat
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date s, e;
        try {
            s = dateFormat.parse(startDate);
            e = dateFormat.parse(endDate);
        } catch (ParseException exception) {
            exception.printStackTrace();
            return;
        }

        // Calculate the number of days between start date and end date
        long daysCount = (e.getTime() - s.getTime()) / (1000 * 60 * 60 * 24);


        // Calculate total price
         totalprice.setText(String.valueOf(price * daysCount));


        continuebtn = findViewById(R.id.submit);
        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Payment.this, HomePage.class);
                startActivity(i);
            }
        });
    }


}
