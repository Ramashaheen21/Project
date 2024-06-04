package com.example.mobileproject.ActivityPages;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import com.example.mobileproject.R;

import org.osmdroid.config.Configuration;
import org.osmdroid.mapsforge.BuildConfig;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.api.IMapController;

public class FindUs extends AppCompatActivity {

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_us);

        // Initialize the MapView
        mapView = findViewById(R.id.mapView);

        // Set up the MapView
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);
        mapView.setMultiTouchControls(true);

        // Set up the map controller
        IMapController mapController = mapView.getController();
        mapController.setZoom(15.0);

        // Set the location coordinates
        GeoPoint startPoint = new GeoPoint(31.955211, 35.175089); // Use your desired location
        mapController.setCenter(startPoint);

        // Add a marker to the location
        Marker startMarker = new Marker(mapView);
        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setTitle("Find Us Here");
        mapView.getOverlays().add(startMarker);

        // Add a click listener to the marker
        startMarker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                // Show a toast message when the marker is clicked
                Toast.makeText(FindUs.this, "Marker clicked!", Toast.LENGTH_SHORT).show();
                // Return true to consume the click event
                return true;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume(); // Needed for compass, my location overlays, v6.0.0 and up
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();  // Needed for compass, my location overlays, v6.0.0 and up
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mapView.onDestroy();
    }
}
