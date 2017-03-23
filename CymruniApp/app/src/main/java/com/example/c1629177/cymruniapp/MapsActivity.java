package com.example.c1629177.cymruniapp;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Boolean mapReady = false;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_maps2);
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);

        Intent i = new Intent(MapsActivity.this, LoginActivity.class);
        startActivity(i);

        Button btnMap = (Button) findViewById(R.id.btnMap);
        Button btnHybrid = (Button) findViewById(R.id.btnHybrid);
        Button btnSatellite = (Button) findViewById(R.id.btnSatellite);
       /* btnMap.setOnClickListener((v) -> {
            if(mapReady)
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        });
*/
        // anonymous class syntax(same as lambda)
        btnMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mapReady)
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });

        btnSatellite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mapReady)
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });

        btnHybrid.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mapReady)
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });
//
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        mapReady = true;

        LatLng studentUnionLatLng = new LatLng(51.488373, -3.177478);
        mMap.addMarker(new MarkerOptions().position(studentUnionLatLng).title("Cardiff University Student Union"));
        CameraPosition target = CameraPosition.builder().target(studentUnionLatLng).zoom(14).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(target));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(51.488396, -3.178465))
                .title("Costa Coffee")
                .snippet("Beginner level Welsh is spoken here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));


        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(51.488259, -3.173882))
                .title("Cafe 37")
                .snippet("Intermediate Welsh is spoken here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));


        ArrayList RedMarker = new ArrayList();
        RedMarker.add(mMap.addMarker(new MarkerOptions()
                .position(new LatLng(51.489635, -3.182219))
                .title("Hoffi Coffee")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
/* this is a custom marker - the ic_audiotrack needs to be changed!!!!!!! */
                .snippet("Fluent Welsh is spoken here"))
                .isVisible());
        RedMarker.add(mMap.addMarker(new MarkerOptions()
                .position(new LatLng(51.488373, -3.177478))
                .title("Cardiff University Student Union")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                .snippet("Fluent Welsh is spoken here"))
                .isVisible());
        RedMarker.add(mMap.addMarker(new MarkerOptions()
                .position(new LatLng(51.488363, -3.182625))
                .title("Welsh Government")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                .snippet("Fluent Welsh is spoken here"))
                .isVisible());
        RedMarker.add(mMap.addMarker(new MarkerOptions()
                .position(new LatLng(51.485363, -3.178993))
                .title("City Hall")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                .snippet("Fluent Welsh is spoken here"))
                .isVisible());
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

//        // Check which checkbox was clicked
//        switch(view.getId()) {
//            case R.id.Red:
//                if (checked)
//                RedMarker.get[i] //TODO copy all this into a text editor then git checkout then paste it but on a new branch and figure out how I can continue the checkobox activity
//                else
//                // Remove the meat
//                break;
//            case R.id.Orange:
//                if (checked)
//                // Cheese me
//                else
//                // I'm lactose intolerant
//                break;
//            case R.id.Yellow:
//                if (checked)
//                // Cheese me
//                else
//                // I'm lactose intolerant
//                break;
//            // TODO: Veggie sandwich
    }
}