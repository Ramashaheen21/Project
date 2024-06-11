package com.example.mobileproject.ActivityPages;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

public class CarListView extends AppCompatActivity {
    private RequestQueue queue;
    private ListView listView;
    private ArrayList<String> lstcars;

    private String model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list_view);
        listView = findViewById(R.id.carListView);

        // Initialize the RequestQueue
        queue = Volley.newRequestQueue(this);

        // Initialize the car list
        lstcars = new ArrayList<>();

        String carBrand = getIntent().getStringExtra("carBrand");

        // Fetch data when the activity is created
        fetchDataFromServer(carBrand);

        // Set an item click listener for the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCar = lstcars.get(position);
                String[] carInfoParts = selectedCar.split(" - ");
                String selectedCarModel = carInfoParts[0]; // Extract the selected car model
                Intent carinfointent = new Intent(CarListView.this, carInfo.class);
                carinfointent.putExtra("carBrand", carBrand);
                carinfointent.putExtra("carModel", selectedCarModel); // Pass the selected car model
                startActivity(carinfointent);
            }
        });
    }

    private void fetchDataFromServer(String carBrand) {
        // Use your machine's IP address or a proper URL
        String url = "http://10.0.2.2/carPHP/PHPcar.php?cat=" + carBrand;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("CarListView", "Response received: " + response.toString());
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                model = jsonObject.getString("Car model");
                                int year = jsonObject.getInt("Year");

                                String carInfo = model + " - " + year;
                                lstcars.add(carInfo);
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(CarListView.this,
                                    android.R.layout.simple_list_item_1, lstcars);
                            listView.setAdapter(adapter);
                        } catch (JSONException e) {
                            Log.e("CarListView", "JSON parsing error: " + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CarListView", "Error fetching data: " + error.getMessage());
                        Toast.makeText(CarListView.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the RequestQueue
        queue.add(jsonArrayRequest);
    }
}
