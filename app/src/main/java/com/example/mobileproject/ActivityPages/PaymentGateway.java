package com.example.mobileproject.ActivityPages;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobileproject.R;

import com.stripe.android.PaymentConfiguration;
import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.paymentsheet.PaymentSheetResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PaymentGateway extends AppCompatActivity {

    Button payment;
    String PublishableKey = "pk_test_51NamUAHYesUS9MnL3WQiqpBFwxF5kuIwaxPvS8pKDOCM5OZOZGp369zHVyCJYfQQvhTRNNDcrwjVXD1ibczc27UK00NXzky3M5";
    String SecretKey = "sk_test_51NamUAHYesUS9MnLlGPy5mlO8BjxXmrvh3OKVPTwyltEGw0J3Xz8pcg3ry3Hjc54krGO7tOjN3ElGDoSsEJpKpm300Ln3wr9uy";
    String CustomerId;
    String EphericalKey;
    String ClientSecret;
    PaymentSheet paymentSheet;
    Integer Value;
    private static final String CHANNEL_ID = "MyChannel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_info);

        // Check and request notification permission if not granted
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }

        Value = getIntent().getIntExtra("TotalPrice", 350);

        payment = findViewById(R.id.btn_add_payment);
        PaymentConfiguration.init(this, PublishableKey);

        paymentSheet = new PaymentSheet(this, this::onPaymentResult);

        payment.setOnClickListener(view -> createCustomerAndStartPaymentFlow());
    }

    private void createCustomerAndStartPaymentFlow() {
        StringRequest request = new StringRequest(Request.Method.POST, "https://api.stripe.com/v1/customers",
                response -> {
                    try {
                        JSONObject object = new JSONObject(response);
                        CustomerId = object.getString("id");
                        createEphemeralKey(CustomerId);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> Toast.makeText(PaymentGateway.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show()) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + SecretKey);
                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void createEphemeralKey(String customerId) {
        StringRequest request = new StringRequest(Request.Method.POST, "https://api.stripe.com/v1/ephemeral_keys",
                response -> {
                    try {
                        JSONObject object = new JSONObject(response);
                        EphericalKey = object.getString("id");
                        createPaymentIntent(CustomerId, EphericalKey);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> Toast.makeText(PaymentGateway.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show()) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + SecretKey);
                headers.put("Stripe-Version", "2020-08-27");
                return headers;
            }

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("customer", customerId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void createPaymentIntent(String customerId, String ephemeralKey) {
        StringRequest request = new StringRequest(Request.Method.POST, "https://api.stripe.com/v1/payment_intents",
                response -> {
                    try {
                        JSONObject object = new JSONObject(response);
                        ClientSecret = object.getString("client_secret");
                        presentPaymentSheet(customerId, ephemeralKey, ClientSecret);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> Toast.makeText(PaymentGateway.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show()) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + SecretKey);
                return headers;
            }

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("customer", customerId);
                params.put("amount", String.valueOf(Value * 100)); // Amount in cents
                params.put("currency", "usd"); // currency in USD
                params.put("automatic_payment_methods[enabled]", "true");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void presentPaymentSheet(String customerId, String ephemeralKey, String clientSecret) {
        PaymentSheet.Configuration configuration = new PaymentSheet.Configuration(
                "Ronit Sanghvi",
                new PaymentSheet.CustomerConfiguration(
                        customerId,
                        ephemeralKey
                )
        );

        paymentSheet.presentWithPaymentIntent(clientSecret, configuration);
    }

    private void onPaymentResult(PaymentSheetResult paymentSheetResult) {
        if (paymentSheetResult instanceof PaymentSheetResult.Completed) {
            Toast.makeText(this, "Payment Success", Toast.LENGTH_SHORT).show();
            buildNotification();
            finish();
        } else {
            Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void buildNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
                return;
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My Channel";
            String description = "Channel for payment notifications";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle("Payment Received");
        bigTextStyle.bigText("Thank you for your Order!");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Payment Received")
                .setContentText("Thank you for your Order!")
                .setStyle(bigTextStyle)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setLights(Color.RED, 3000, 3000);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1, builder.build());
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with the notification
                buildNotification();
            } else {
                // Permission denied, show a message to the user
                Toast.makeText(this, "Notification permission is required to show notifications", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
