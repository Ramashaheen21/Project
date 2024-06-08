package com.example.mobileproject.ActivityPages;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobileproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    private static final String TAG = "HomePage";

    private RecyclerView recyclerView;
    private CarAdapter carAdapter;
    private List<Car> carList;
    private Button btn_search;
    private Button mercedesbtn;
    private Button bmwbtn;
    private Button porschebtn;
    private Button feraribtn;
    private Button bentleybtn;
    private Button astonmartinbtn;

    private Button menu_Booking;
    private Button menu_liked;
    private Button menu_home;
    private Button menu_account;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        mercedesbtn = findViewById(R.id.mercedesbtn);
        bmwbtn = findViewById(R.id.bmwbtn);
        porschebtn = findViewById(R.id.porschebtn);
        feraribtn = findViewById(R.id.ferraribtn);
        bentleybtn = findViewById(R.id.bentleybtn);
        astonmartinbtn = findViewById(R.id.astonMartinbtn);
        menu_Booking = findViewById(R.id.button_booking);
        menu_liked = findViewById(R.id.button_Location);
        menu_home = findViewById(R.id.button_main);
        menu_account = findViewById(R.id.button_profile);

         imageView = findViewById(R.id.card_image);
        try {
            InputStream inputStream = getAssets().open("drawable/carPhotos/Aston Martin DB5.png");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "HomePage onCreate");
        menu_liked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, FindUs.class);
                startActivity(intent);
                showToast("Liked clicked!");
            }
        });

        menu_Booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this, booking.class);
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
                Intent i = new Intent(HomePage.this, profile.class);
                startActivity(i);
            }
        });

        mercedesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, CarListView.class);
                startActivity(i);
            }
        });

        bmwbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, CarListView.class);
                startActivity(i);
            }
        });

        porschebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, CarListView.class);
                startActivity(i);
            }
        });
        bentleybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, CarListView.class);
                startActivity(i);
            }
        });
        feraribtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, CarListView.class);
                startActivity(i);
            }
        });
        astonmartinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, CarListView.class);
                startActivity(i);
            }
        });

    }

    private void fetchCars(String carBrand) {
        String url = "http://localhost/carPHP/PHPcar.php?cat=" + carBrand;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        carList.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject car = jsonArray.getJSONObject(i);
                            String carId = car.getString("Car ID");
                            String car_brand = car.getString("Car brand");
                            String carModel = car.getString("Car model");
                            int year = car.getInt("Year");
                            int seats = car.getInt("Car seats number");
                            String gearType = car.getString("Gear type");
                            String color = car.getString("Color");
                            double pricePerDay = car.getDouble("Price per day");

                            carList.add(new Car(carId, car_brand, carModel, year, seats, gearType, color, pricePerDay));
                        }
                        carAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(HomePage.this, "Failed to parse JSON", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(HomePage.this, "Failed to fetch data", Toast.LENGTH_SHORT).show());

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }


    private List<Car> parseJsonResponse(JSONArray response) {
        List<Car> mercedesCars = new ArrayList<>();
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject carJson = response.getJSONObject(i);
                String make = carJson.getString("make");
                if (make.equalsIgnoreCase("Mercedes")) {
                    String model = carJson.getString("model");
                    int year = carJson.getInt("year");
                    mercedesCars.add(new Car(model, year));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mercedesCars;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
