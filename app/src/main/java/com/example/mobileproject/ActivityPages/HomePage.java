package com.example.mobileproject.ActivityPages;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class HomePage extends AppCompatActivity {
    private static final String TAG = "HomePage";

    private RecyclerView recyclerView;
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

    ConstraintLayout card1;
    ConstraintLayout card2;
    ConstraintLayout card3;

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

//         imageView = findViewById(R.id.card_image);
//        try {
//            InputStream inputStream = getAssets().open("carPhotos/Aston Martin DB5.png");
//            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//            imageView.setImageBitmap(bitmap);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

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
}
