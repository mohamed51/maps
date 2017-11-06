package com.example.a_t.maps;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivitygps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double latitudevalue;
    double longitudevalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_activitygps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        latitudevalue= Double.parseDouble(getIntent().getExtras().getString("latitude"));
        longitudevalue= Double.parseDouble(getIntent().getExtras().getString("longitude"));
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng=new LatLng(latitudevalue,longitudevalue);

        // Add a marker in Sydney and move the camera
        Geocoder g=new Geocoder(getApplicationContext());
        try {
            List<Address> listname=g.getFromLocation(latitudevalue,longitudevalue,1);
            String name=listname.get(0).getLocality()+",";
            name+=listname.get(0).getCountryName();
            mMap.addMarker(new MarkerOptions().position(latLng).title(name));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.getUiSettings().setZoomControlsEnabled(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
