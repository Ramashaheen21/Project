package com.example.mobileproject.ActivityPages;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;

import com.example.mobileproject.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar ;

public class HomePage extends AppCompatActivity {
    private static final String TAG = "HomePage";

    private EditText edt_pickupLocation;
    private TextView tf_pickupDate, tf_dropoffDate, tf_warning;
    private Button btn_search;

    private Button menu_Booking;
    private Button menu_liked;
    private Button menu_home;
    private Button menu_account;
    private HorizontalScrollView top_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupView();
    }


    private void setupView() {

        menu_Booking = findViewById(R.id.button_booking);
        menu_liked = findViewById(R.id.button_liked);
        menu_home = findViewById(R.id.button_main);
        menu_account = findViewById(R.id.button_profile);

        top_list = findViewById(R.id.topList);


        getNearsCars();

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tf_pickupDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(HomePage.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        tf_pickupDate.setText(date);
                    }
                }, year, month, day);
                dialog.show();
            }
        });

        tf_dropoffDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(HomePage.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        tf_dropoffDate.setText(date);
                    }
                }, year, month, day);
                dialog.show();
            }
        });


        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_pickupLocation.getText().toString().isEmpty()) {
                    tf_warning.setText(R.string.pickupLocationError);
                } else if (tf_pickupDate.getText().toString().isEmpty()) {
                    tf_warning.setText(R.string.pickupDateError);
                } else if (tf_dropoffDate.getText().toString().isEmpty()) {
                    tf_warning.setText(R.string.dropoffDateError);
                } else {
                    String pickupLocation = edt_pickupLocation.getText().toString();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

                    LocalDate pickupDate = LocalDate.parse(tf_pickupDate.getText().toString(), formatter);
                    LocalDate dropoffDate = LocalDate.parse(tf_dropoffDate.getText().toString(), formatter);

                    if (pickupDate.isBefore(LocalDate.now())) {
                        tf_warning.setText(R.string.futureDatesError);
                    } else if (pickupDate.isAfter(dropoffDate)) {
                        tf_warning.setText(R.string.dropDateAfterPickupError);
                    } else {
                        Intent i = new Intent(getApplicationContext(), CarsAvailable.class);
                        i.putExtra("pickupLocation", pickupLocation);
                        i.putExtra("fromDate", pickupDate.toString());
                        i.putExtra("toDate", dropoffDate.toString());

                        SharedPreferences sharedPreferences = getSharedPreferences("dates", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("fromDate", pickupDate.toString());
                        editor.putString("toDate", dropoffDate.toString());
                        editor.apply();

                        startActivity(i);
                    }
                }
            }
        });


        menu_liked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), likedActivity.class);
                startActivity(i);
            }
        });

        menu_Booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), booking.class);
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
                Intent i = new Intent(getApplicationContext(), Profile.class);
                startActivity(i);
            }
        });
    }


    private void getNearsCars() {
        recycler_layout.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recycler_layout.setLayoutManager(layoutManager);

        ArrayList<HomeCarCard> listOfCars = new ArrayList<>();
        HomeCarCardAdaptor carsAdapter = new HomeCarCardAdaptor(this, listOfCars);
        recycler_layout.setAdapter(carsAdapter);


        // Get a reference to the cars node in the Firebase Realtime Database
        DatabaseReference carsReference = FirebaseDatabase.getInstance().getReference().child("cars");

        // Set the myLocation variable
        LocationHelper locationHelper = new LocationHelper(this);
        String myLocation = locationHelper.getLocation();

        // Read data from the Firebase Realtime Database
        carsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count = 0;
                for (DataSnapshot carSnapshot : dataSnapshot.getChildren()) {
                    if (count >= 6) {
                        break;
                    }
                    String carLocation = carSnapshot.child("lat_long").getValue(String.class);
                    double distance = DistanceCalculator.distance(myLocation, carLocation);
                    if (distance <= 10000000) {
                        count++;

                        // The car is within 10 km of the user's location
                        final HomeCarCard carCard = new HomeCarCard();
                        carCard.id = carSnapshot.getKey();
                        carCard.brand = carSnapshot.child("brand").getValue(String.class);
                        carCard.price = carSnapshot.child("price").getValue(Integer.class) + "$/Day";

                        getCarImage(carCard.id, carCard);
                    }
                }
            }

            private void getCarImage(String carID, HomeCarCard carCard) {
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();

                StorageReference imageRef = storageRef.child("images/cars/" + carID + ".jpg");

                imageRef.getBytes(5 * 1024 * 1024).addOnSuccessListener(bytes -> {
                    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    carCard.image = bmp;
                    listOfCars.add(carCard);
                    carsAdapter.notifyDataSetChanged();
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle the error
            }
        });
    }
}