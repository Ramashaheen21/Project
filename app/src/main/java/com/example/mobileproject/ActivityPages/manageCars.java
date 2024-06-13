package com.example.mobileproject.ActivityPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.R;

public class manageCars extends AppCompatActivity {
    private Button btn_add;
    private Button btn_delete;
   // private Button btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_cars);
        setupViews();
    }

    private void setupViews() {
        btn_add = findViewById(R.id.btn_add);
        btn_delete = findViewById(R.id.btn_delete);
       // btn_update = findViewById(R.id.btn_update);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(manageCars.this, AddCar.class);
                startActivity(intent);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(manageCars.this, DeleteCar.class);
                startActivity(intent);
            }
        });

        /*btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(manageCars.this, UpdateCar.class);
                startActivity(intent);
            }
        });*/
    }
}
