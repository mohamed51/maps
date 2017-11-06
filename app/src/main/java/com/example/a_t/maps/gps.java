package com.example.a_t.maps;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class gps extends AppCompatActivity {
    Button ask_btn,locationbtn;
    TextView l1,l2;
    private LocationListener locationListener;
    private LocationManager locationManager;
    String post_latitude,post_longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        ask_btn=(Button)findViewById(R.id.ask);
        l1=(TextView)findViewById(R.id.location1);
        l2=(TextView)findViewById(R.id.location2);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationbtn=(Button)findViewById(R.id.btnlocation);

        //
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                l1.setText(location.getLatitude()+"");
                l2.setText(location.getLongitude()+"");
                ask_btn.setVisibility(View.VISIBLE);

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(gps.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(gps.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                }, 10);
            } else {
                configerbutton();
            }
        }
        ask_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                post_latitude=l1.getText().toString().trim();
                post_longitude=l2.getText().toString().trim();
                Intent i = new Intent(gps.this, MapsActivitygps.class);
                i.putExtra("latitude", post_latitude);
                i.putExtra("longitude", post_longitude);
                startActivity(i);

            }
        });


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    configerbutton();
                return;
        }
    }

    private void configerbutton() {
        locationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(gps.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(gps.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);

            }
        });
    }
}
