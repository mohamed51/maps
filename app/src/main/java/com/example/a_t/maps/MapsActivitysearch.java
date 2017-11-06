package com.example.a_t.maps;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivitysearch extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_activitysearch);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


   public void onsearch(View view){
       EditText location_st=(EditText)findViewById(R.id.input_search);
       String location=location_st.getText().toString().trim();
       List<Address>addressList = null;
       if (location.equals("")) {
           Toast.makeText(this, "please insert address", Toast.LENGTH_SHORT).show();
       }
       else {
           Geocoder geocoder=new Geocoder(this);
           try {
               addressList=geocoder.getFromLocationName(location,1);

           } catch (IOException e) {
               e.printStackTrace();
           }
           Address address=addressList.get(0);
           LatLng latLng=new LatLng(address.getLatitude(),address.getLongitude());
           String str=addressList.get(0).getLocality()+",";
           str+=addressList.get(0).getCountryName();
           mMap.addMarker(new MarkerOptions().position(latLng).title(str));
           mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
           mMap.getUiSettings().setZoomControlsEnabled(true);
           mMap.getUiSettings().isMyLocationButtonEnabled();
           

       }
   }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
