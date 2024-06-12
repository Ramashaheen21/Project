package com.example.mobileproject.ActivityPages;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
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
    private RequestQueue queue;

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

    ConstraintLayout card1;
    ConstraintLayout card2;
    ConstraintLayout card3;

    private String color,carModel,gear,carb;
    private int  seat;
    private double price;

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

        queue = Volley.newRequestQueue(this);
        Log.d(TAG, "HomePage onCreate");
        menu_liked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, FindUs.class);
                startActivity(intent);
            }
        });

        menu_Booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this, DisplayBooking.class);
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
                i.putExtra("carBrand", "Mercedes-Benz");
                startActivity(i);
            }
        });

        bmwbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, CarListView.class);
                i.putExtra("carBrand", "BMW");
                startActivity(i);
            }
        });

        porschebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, CarListView.class);
                i.putExtra("carBrand", "Porsche");
                startActivity(i);
            }
        });
        bentleybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, CarListView.class);
                i.putExtra("carBrand", "Bentley");
                startActivity(i);
            }
        });
        feraribtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, CarListView.class);
                i.putExtra("carBrand", "Ferrari");
                startActivity(i);
            }
        });
        astonmartinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, CarListView.class);
                i.putExtra("carBrand", "Aston Martin");
                startActivity(i);
            }
        });


        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);

        // For card1
        TextView vehicle1 = card1.findViewById(R.id.vehicle);
        TextView price1 = card1.findViewById(R.id.price);
        ImageView vehicleImage1 = card1.findViewById(R.id.vehicleImage);
        Button vehicke1btn = card1.findViewById(R.id.book);

        vehicle1.setText("Ferrari 458 Italia");
        price1.setText("$250/day");
        loadImageFromAssets("carPhotos/fr004.png", vehicleImage1);
        vehicke1btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, carInfo.class);
                startActivity(i);
            }
        });

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String carid = "FR004";
                fetchCarDetailsAndStartActivity(carid);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String carid="PO002";
                fetchCarDetailsAndStartActivity(carid);
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String carid = "M008";
                fetchCarDetailsAndStartActivity(carid);
            }
        });


        // For card2
        TextView vehicle2 = card2.findViewById(R.id.vehicle);
        TextView price2 = card2.findViewById(R.id.price);
        ImageView vehicleImage2 = card2.findViewById(R.id.vehicleImage);

        vehicle2.setText("Porche 944");
        price2.setText("$300/day");
        loadImageFromAssets("carPhotos/po002.jpg", vehicleImage2);

// For card3
        TextView vehicle3 = card3.findViewById(R.id.vehicle);
        TextView price3 = card3.findViewById(R.id.price);
        ImageView vehicleImage3 = card3.findViewById(R.id.vehicleImage);

        vehicle3.setText("Mercedes-Benz SLK-Class (R171)");
        price3.setText("$290/day");
        loadImageFromAssets("carPhotos/m008.png", vehicleImage3);
    }

    private void loadImageFromAssets(String filePath, ImageView imageView) {
        try {
            InputStream inputStream = getAssets().open(filePath);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            imageView.setImageBitmap(bitmap);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void fetchCarDetailsAndStartActivity(String CarId) {
        // Perform Volley request to fetch car details
        String url = "http://10.0.0.17/carPHP/GetID.php?cat=" + CarId;


        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            if (response.length() > 0) {
                                JSONObject carDetails = response.getJSONObject(0);

                                String color = carDetails.getString("Color");
                                String carModel = carDetails.getString("Car model");
                                String gear = carDetails.getString("Gear type");
                                int seat = carDetails.getInt("Car seats number");
                                double price = carDetails.getDouble("Price per day");
                                String carb = carDetails.getString("Car brand");
                                String ID = carDetails.getString("Car ID");

                                Intent intent = new Intent(HomePage.this, carInfo.class);
                                intent.putExtra("color", color);
                                intent.putExtra("carM", carModel);
                                intent.putExtra("g", gear);
                                intent.putExtra("s", seat);
                                intent.putExtra("PPD", price);
                                intent.putExtra("carB", carb);
                                intent.putExtra("carid", ID);
                                startActivity(intent);
                            } else {
                                showToast("No car details found.");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            showToast("Error parsing response");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showToast("Error fetching data: " + error.getMessage());
                    }
                });

        queue.add(request);
    }

}
