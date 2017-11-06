package com.example.a_t.maps;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {
     Button map1,map2,map3,map4,map5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map1 = (Button) findViewById(R.id.map);
        map2=(Button)findViewById(R.id.mapsearch);
        map3=(Button)findViewById(R.id.mapgps);
        map4=(Button)findViewById(R.id.mapdirection);
        map5=(Button)findViewById(R.id.maproute);


        map1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, currentlocationinmap.class));
            }
        });
        map2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MapsActivitysearch.class));

            }
        });

        map3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, gps.class));

            }
        });
        map5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
            }
        });
        map4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MapsActivitytouch.class));
            }
        });

    }


}
