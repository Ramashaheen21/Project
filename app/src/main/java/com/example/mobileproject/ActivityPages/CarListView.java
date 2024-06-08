package com.example.mobileproject.ActivityPages;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list_view);
        queue = Volley.newRequestQueue(this);

        listView = findViewById(R.id.carListView);
    }

    private void fetchDataFromServer() {

        String url = "http://10.0.0.2/carPHP/PHPcar.php?cat= Mercedes-Benz";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<String> lstcars = new ArrayList<>();

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String model = jsonObject.getString("Car model");
                                int year = jsonObject.getInt(" Year");
                                String carInfo = model + " - " + year;
                                lstcars.add(carInfo);
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(CarListView.this,
                                    android.R.layout.simple_list_item_1, lstcars);
                            listView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle Volley error
                    }
                });

        // Add the request to the RequestQueue
        queue.add(jsonArrayRequest);

    }


}
