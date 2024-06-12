package com.example.mobileproject.ActivityPages;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

public class carInfo extends AppCompatActivity {
    private RequestQueue queue;
    private ArrayList<String> lstcars;
    private TextView carModelTextView,motor,seat,brand,price,color;
    private String brands,carModel,gearType,colors ;
   private  int seats ;
    private double pricePerDay ;
    Car car ;
    private Button rentbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);

        rentbtn = findViewById(R.id.btn_rent);
        queue = Volley.newRequestQueue(this);

        lstcars = new ArrayList<>();
        String carBrand = getIntent().getStringExtra("carBrand");
        String carMod = getIntent().getStringExtra("carModel");
        // Display the car information as desired
        // For example:
         carModelTextView = findViewById(R.id.model_txt);
         motor = findViewById(R.id.motorType_txt);
         seat = findViewById(R.id.seatNumber_txt);
         brand = findViewById(R.id.brand_txt);
         price = findViewById(R.id.price_txt);
         color = findViewById(R.id.et_color);



        rentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(carInfo.this, booking.class);
                i.putExtra("price", pricePerDay);
                startActivity(i);
            }
        });
        fetchDataFromServer(carBrand,carMod);
    }

    private void fetchDataFromServer(String carBrand, String carModelfromlist) {
        String url = "http://10.0.0.17/carPHP/PHPcar.php?cat=" + carBrand;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("CarListView", "Response received: " + response.toString());
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                 brands = jsonObject.getString("Car brand");
                                 carModel = jsonObject.getString("Car model");
                                 seats = jsonObject.getInt("Car seats number");
                                 gearType = jsonObject.getString("Gear type");
                                 colors = jsonObject.getString("Color");
                                 pricePerDay = jsonObject.getDouble("Price per day");

                                // Check if the current car model matches the desired car model
                                if (carModel.equals(carModelfromlist)) {
                                    // Display car information
                                    brand.setText(brands);
                                    carModelTextView.setText(carModel);
                                    motor.setText(gearType);
                                    seat.setText(String.valueOf(seats));
                                    color.setText(colors);
                                    price.setText(String.valueOf(pricePerDay));
                                    return; // Stop iterating through the response once the desired car is found
                                }
                            }
                            // If the desired car model is not found, display a message or handle it accordingly
                            Toast.makeText(carInfo.this, "Car not found", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            Log.e("CarListView", "JSON parsing error: " + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CarListView", "Error fetching data: " + error.getMessage());
                        Toast.makeText(carInfo.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the RequestQueue
        queue.add(jsonArrayRequest);
    }

}