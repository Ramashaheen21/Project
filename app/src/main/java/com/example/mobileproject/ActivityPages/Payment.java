package com.example.mobileproject.ActivityPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobileproject.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Payment extends AppCompatActivity {

    private String POST_URL = "http://10.0.0.17/carPHP/Postphp.php?";

    private TextView datetxt;
    private TextView pricePerDay,priceThrough,totalprice;

    private Button continuebtn;

    private String carmod,startDate,endDate;
    private double price;
    private int year;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_complete);

        pricePerDay = findViewById(R.id.PriceDayText);
        priceThrough = findViewById(R.id.PriceAllDaysText);
        totalprice = findViewById(R.id.totalPrice);
        datetxt = findViewById(R.id.datesText);

        // from Payment activity
         startDate = getIntent().getStringExtra("startDate");
         endDate = getIntent().getStringExtra("endDate");
         price = getIntent().getDoubleExtra("price", 0.0);
         carmod = getIntent().getStringExtra("carModel");
         year = getIntent().getIntExtra("year",0);

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
                postDataToDatabase();
                Intent i = new Intent(Payment.this, HomePage.class);
                startActivity(i);
            }
        });
    }

    private void postDataToDatabase() {

        // Instantiate the RequestQueue
        RequestQueue queue = Volley.newRequestQueue(this);

        // Create the StringRequest
        StringRequest stringRequest = new StringRequest(Request.Method.POST, POST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle response from server
                        Toast.makeText(Payment.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle errors
                Toast.makeText(Payment.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Post parameters to your PHP script
                Map<String, String> params = new HashMap<>();
                params.put("car_model", carmod);
                params.put("year", String.valueOf(year));
                params.put("start_date", startDate);
                params.put("end_date", endDate);
                params.put("total_price", String.valueOf(price));
                return params;
            }
        };

        // Add the request to the RequestQueue
        queue.add(stringRequest);
    }

}
