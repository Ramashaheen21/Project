package com.example.mobileproject.ActivityPages;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.R;
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
        menu_liked = findViewById(R.id.button_Location);
        menu_home = findViewById(R.id.button_main);
        menu_account = findViewById(R.id.button_profile);

        top_list = findViewById(R.id.topList);

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

            }
        });


        menu_liked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FindUs.class);
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
                Intent i = new Intent(getApplicationContext(), profile.class);
                startActivity(i);
            }
        });
    }

}