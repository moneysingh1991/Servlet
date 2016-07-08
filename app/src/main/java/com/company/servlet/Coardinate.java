package com.company.servlet;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import static android.content.Context.LOCATION_SERVICE;

public class Coardinate extends AppCompatActivity {


    TextView textView_coardinate = null;
    Button coardinate_button = null;
    String location_data = null;

    String longitude, latitude;

    private LocationManager locationManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_coardinate);

        textView_coardinate = (TextView) findViewById(R.id.textView_coardinate);



    }

    public void show_coardinate(View view) {



        location_data  = "unknown";

        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

// Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
               // makeUseOfNewLocation(location);

               // location_data = Double.toString(location.getLatitude());
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            String locationProvider = LocationManager.NETWORK_PROVIDER;
           // locationProvider = LocationManager.GPS_PROVIDER;

            Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);

            locationProvider = LocationManager.NETWORK_PROVIDER;;

            longitude = Double.toString(lastKnownLocation.getLongitude());
            latitude = Double.toString(lastKnownLocation.getLatitude());


            location_data = longitude + ", " + latitude;

            //location_data  = "known";
            textView_coardinate.setText(location_data);
        }


    }

    public void show_google_map(View view) {

        Intent intent = new Intent(this, MapsActivity.class);

        startActivity(intent);

       /* MapsActivity m = null;

        LatLng l = new LatLng(Double.parseDouble(longitude), Double.parseDouble(latitude));

        m.set_cordinates(l);*/


    }

}
