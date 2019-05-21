package com.pouya.digim;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;


public class confirmActivity  extends AppCompatActivity  implements OnMapReadyCallback {
    GoogleMap googleMap;

    TextView username_txt;
    TextView total_txt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_layout);
        Intent intent = getIntent();

        User user = (User) intent.getSerializableExtra("user");

        int total = intent.getIntExtra("total", 0);


        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.actionBar));
        setSupportActionBar(toolbar);

        setTitle("تایید نهایی");

        username_txt = findViewById(R.id.username);
        total_txt = findViewById(R.id.total);

        username_txt.setText(" " + user.name + " عزیز ");
        total_txt.setText(String.valueOf(total));



    }

    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(getApplicationContext(), "request permissions ...", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);


        } else {
//            this.setMapListener();
        }

        googleMap.setMyLocationEnabled(true);

        googleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                goToLocation(location.getLatitude(), location.getLongitude(), 14);
            }
        });


//        onMapClick = new onMapClick(googleMap, this);
//        googleMap.setOnMapClickListener(onMapClick);
    }


    private void goToLocation(double latitude, double longitude, int zoom) {
        CameraUpdate update =
                CameraUpdateFactory.newLatLngZoom(
                        new LatLng(latitude, longitude),
                        zoom);
        googleMap.animateCamera(update);
    }

    public void confirm(View v) {
        Intent intent = new Intent(confirmActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void cancel(View v) {
        Intent intent = new Intent(confirmActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
