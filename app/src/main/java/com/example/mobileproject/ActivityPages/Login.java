
package com.example.mobileproject.ActivityPages;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.R;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;

import com.google.firebase.firestore.FirebaseFirestore;



public class Login extends AppCompatActivity {

    private static final String CORRECT_USERNAME_CUSTOMER = "test@customer.com";
    private static final String CORRECT_PASSWORD_CUSTOMER = "12341234";
    private static final String CORRECT_USERNAME_ADMIN = "test@admin.com";
    private static final String CORRECT_PASSWORD_ADMIN = "56785678";

    private EditText username_txt;
    private EditText pw_txt;
    private Button login_btn;
    private Button register_btn;
    private boolean isAllChecked = false;

    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private CollectionReference colRef = database.collection("LogIn");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseApp.initializeApp(this);

        setupViews();
    }

    private void setupViews() {
        username_txt = findViewById(R.id.username_txt);
        pw_txt = findViewById(R.id.pw_txt);
        register_btn = findViewById(R.id.register_btn);
        login_btn = findViewById(R.id.login_btn);


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = username_txt.getText().toString().trim();
                String password = pw_txt.getText().toString().trim();

                isAllChecked = checkUsername();
                if (isAllChecked) {
                    SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.apply();
                    if (userName.equals(CORRECT_USERNAME_CUSTOMER) && password.equals(CORRECT_PASSWORD_CUSTOMER)) {
                        Intent intent = new Intent(Login.this, HomePage.class);
                        startActivity(intent);
                        showToast("Login succeeded!");
                    }
                    else if(userName.equals(CORRECT_USERNAME_ADMIN) && password.equals(CORRECT_PASSWORD_ADMIN)){
                        Intent intent = new Intent(Login.this, MainBusinessOwner.class);
                        startActivity(intent);
                        showToast("Login succeeded!");
                    }
                    else {
                        showToast("Login failed!");
                    }
                }
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        });

    }

    boolean isEmail(EditText text) {
        CharSequence username_txt = text.getText().toString();
        return (!TextUtils.isEmpty(username_txt) && Patterns.EMAIL_ADDRESS.matcher(username_txt).matches());
    }


    private boolean checkUsername() {
        if (username_txt.getText().toString().isEmpty()) {
            username_txt.setError("Username is Required");
            return false;
        } else if (!isEmail(username_txt)) {
            username_txt.setError("Please Enter a Valid Username!");
            return false;
        }

        if (pw_txt.getText().toString().isEmpty() || pw_txt.length() < 6) {
            pw_txt.setError("Password is Required");
            return false;
        }

        return true;
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }



}