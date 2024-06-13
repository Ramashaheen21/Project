package com.example.mobileproject.ActivityPages;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobileproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class carInfo extends AppCompatActivity {
    private RequestQueue queue;
    private TextView carModelTextView,motor,seat,brand,price,color;
    private String brands,carModel,gearType,colors,carid ;
   private  int seats,year ;
    private double pricePerDay ;
    private Button rentbtn;

    private String carb ,carm ,g,c;
    private  double pricePD;
    private int s;

    ImageView carimgs;

    private List<images> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);

        carimgs = findViewById(R.id.car_img);
        rentbtn = findViewById(R.id.btn_rent);
        queue = Volley.newRequestQueue(this);

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

        carb = getIntent().getStringExtra("carB");
        carm = getIntent().getStringExtra("carM");
        g = getIntent().getStringExtra("g");
        s = getIntent().getIntExtra("s",0);
        pricePD = getIntent().getDoubleExtra("PPD" , 0);
        c = getIntent().getStringExtra("color");
        carid = getIntent().getStringExtra("carid");
        rentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(carInfo.this, booking.class);
                i.putExtra("price", pricePerDay);
                i.putExtra("carModel",carModel);
                i.putExtra("year",year);
                startActivity(i);
            }
        });
        fetchDataFromServer(carBrand,carMod);
        fetchCarID(carid);
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
                                 year = jsonObject.getInt("Year");

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
    private void fetchCarID(String carID) {
        String url = "http://10.0.0.17/carPHP/GetID.php?cat=" + carID;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("CarListView", "Response received: " + response.toString());
                        boolean found = false; // Flag to track if the car is found

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String retrievedCarID = jsonObject.getString("Car ID");

                                if (retrievedCarID.equals(carID)) {
                                    // Update UI with the found car's details
                                    brands = jsonObject.getString("Car brand");
                                    carModel = jsonObject.getString("Car model");
                                    seats = jsonObject.getInt("Car seats number");
                                    gearType = jsonObject.getString("Gear type");
                                    colors = jsonObject.getString("Color");
                                    pricePerDay = jsonObject.getDouble("Price per day");

                                    brand.setText(brands);
                                    carModelTextView.setText(carModel);
                                    motor.setText(gearType);
                                    seat.setText(String.valueOf(seats));
                                    color.setText(colors);
                                    price.setText(String.valueOf(pricePerDay));

                                    found = true; // Set flag to true since car is found
                                    break; // Exit the loop since we found the car
                                }
                            }

                            if (!found) {
                                // If the desired car ID is not found, display a message
                                Toast.makeText(carInfo.this, "Car not found", Toast.LENGTH_SHORT).show();
                            }
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
    private void loadItems() {

        String BASE_URL = "http://10.0.0.17/carPhotos/carPhotos/carsPics.php?";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        try {

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i<array.length(); i++){

                                JSONObject object = array.getJSONObject(i);

                                String brand = object.getString("Car brand");
                                String image = object.getString("image");
                                String model = object.getString("Car model");
                                String id = object.getString("CarID");

                                images pizza = new images(brand,model,id ,image);
                                items.add(pizza);
                            }

                        }catch (Exception e){

                        }

                        /*CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(carInfo.this,
                                items);
                        carimgs.setAdapter(adapter);*/

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(carInfo.this, error.toString(),Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(carInfo.this).add(stringRequest);

    }

}