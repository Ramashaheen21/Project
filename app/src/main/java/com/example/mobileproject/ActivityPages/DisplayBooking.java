package com.example.mobileproject.ActivityPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobileproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DisplayBooking extends AppCompatActivity {
    private ArrayList<String> carList;
    private RequestQueue queuereq;
    private Button menu_Booking;
    private Button menu_liked;
    private Button menu_home;
    private Button menu_account;
    private ListView carListView;
    private String carmodel,startdate,enddate;
    private double pricetotal;
    private int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_display_booking);

        menu_Booking = findViewById(R.id.button_booking);
        menu_liked = findViewById(R.id.button_Location);
        menu_home = findViewById(R.id.button_main);
        menu_account = findViewById(R.id.button_profile);
        carListView = findViewById(R.id.carListView);

        carList = new ArrayList<>();
        queuereq = Volley.newRequestQueue(this);

        // Check if the activity is created for the first time
        if (savedInstanceState == null) {
            fetchDataFromServer();
        }


        menu_liked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayBooking.this, FindUs.class);
                startActivity(intent);
            }
        });

        menu_Booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DisplayBooking.this, DisplayBooking.class);
                startActivity(i);
            }
        });

        menu_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), HomePage.class);
                startActivity(i);
            }
        });

        menu_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DisplayBooking.this, profile.class);
                startActivity(i);
            }
        });

    }

    private void fetchDataFromServer() {
        String url = "http://10.0.0.17/carPHP/GetBookingPhp.php?";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                carmodel = jsonObject.getString("Car model");
                                year = jsonObject.getInt("Year");
                                startdate = jsonObject.getString("Start date");
                                enddate = jsonObject.getString("End date");
                                pricetotal = jsonObject.getDouble("Total price");

                                String bookingInfo =carmodel+" "+year + "\n" + startdate +" - "+ enddate + "\n" + pricetotal;
                                carList.add(bookingInfo);
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(DisplayBooking.this,
                                    android.R.layout.simple_list_item_1, carList);
                            carListView.setAdapter(adapter);
                            Toast.makeText(DisplayBooking.this, "get data successful!", Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplayBooking.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the RequestQueue
        queuereq.add(jsonArrayRequest);
    }

}
