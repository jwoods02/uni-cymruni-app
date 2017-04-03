package com.example.c1629177.cymruniapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Boolean mapReady = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MapsActivity)) return false;

        MapsActivity that = (MapsActivity) o;

        if (!mMap.equals(that.mMap)) return false;
        if (!mapReady.equals(that.mapReady)) return false;
        if (!RedMarker.equals(that.RedMarker)) return false;
        if (!OrangeMarker.equals(that.OrangeMarker)) return false;
        return YellowMarker.equals(that.YellowMarker);

    }

    @Override
    public int hashCode() {
        int result = 0;
        if (mMap != null) {
            result = mMap.hashCode();
            result = 31 * result + mapReady.hashCode();
            result = 31 * result + RedMarker.hashCode();
            result = 31 * result + OrangeMarker.hashCode();
            result = 31 * result + YellowMarker.hashCode();
        } else {
            result = 0;
        }
        return result;
    }

    private ArrayList<Marker> RedMarker = new ArrayList<>();
    private ArrayList<Marker> OrangeMarker = new ArrayList<>();
    private ArrayList<Marker> YellowMarker = new ArrayList<>();

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

        YellowMarker.add(mMap.addMarker(new MarkerOptions()
                .position(new LatLng(51.488396, -3.178465))
                .title("Costa Coffee")
                .snippet("Beginner level Welsh is spoken here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))));
        YellowMarker.add(mMap.addMarker(new MarkerOptions()
                .position(new LatLng(51.493076, -3.177566))
                .title("Zi's Cafe")
                .snippet("Beginner level Welsh is spoken here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))));
        YellowMarker.add(mMap.addMarker(new MarkerOptions()
                .position(new LatLng(51.485033, -3.175764))
                .title("Park Place Club")
                .snippet("Beginner level Welsh is spoken here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))));
        YellowMarker.add(mMap.addMarker(new MarkerOptions()
                .position(new LatLng(51.486180, -3.176547))
                .title("Mock1")
                .snippet("Beginner level Welsh is spoken here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))));
        YellowMarker.add(mMap.addMarker(new MarkerOptions()
                .position(new LatLng(51.45894, -3.342465))
                .title("Mock2")
                .snippet("Beginner level Welsh is spoken here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))));


        OrangeMarker.add(mMap.addMarker(new MarkerOptions()
                .position(new LatLng(51.488259, -3.173882))
                .title("Cafe 37")
                .snippet("Intermediate Welsh is spoken here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))));
        OrangeMarker.add(mMap.addMarker(new MarkerOptions()
                .position(new LatLng(53.744259, -3.123882))
                .title("Mock3")
                .snippet("Intermediate Welsh is spoken here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))));
        OrangeMarker.add(mMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.442989, -3.987882))
                .title("Mock4")
                .snippet("Intermediate Welsh is spoken here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))));
        OrangeMarker.add(mMap.addMarker(new MarkerOptions()
                .position(new LatLng(52.844259, -4.023882))
                .title("Mock5")
                .snippet("Intermediate Welsh is spoken here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))));


        /* Filling redmarker arraylist with markers */
        RedMarker.add(mMap.addMarker(new MarkerOptions()
                .position(new LatLng(51.489635, -3.182219))
                .title("Hoffi Coffee")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
/* this is a custom marker - the ic_audiotrack needs to be changed!!!!!!! */
                .snippet("Fluent Welsh is spoken here")));
        RedMarker.add(mMap.addMarker(new MarkerOptions()
                .position(new LatLng(51.488373, -3.177478))
                .title("Cardiff University Student Union")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                .snippet("Fluent Welsh is spoken here")));
        RedMarker.add(mMap.addMarker(new MarkerOptions()
                .position(new LatLng(51.488363, -3.182625))
                .title("Welsh Government")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                .snippet("Fluent Welsh is spoken here")));
        RedMarker.add(mMap.addMarker(new MarkerOptions()
                .position(new LatLng(51.485363, -3.178993))
                .title("City Hall")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                .snippet("Fluent Welsh is spoken here")));


    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

/* Checks which box is clicked and then changes marker visibilty */
        switch(view.getId()) {
            case R.id.Red:
                if (checked)
                    for(Marker m : RedMarker){
                        m.setVisible(false);
                    }
                else
                    for (Marker m: RedMarker){
                        m.setVisible(true);
                    }
                    break;
            case R.id.Orange:
                if (checked)
                    for(Marker ma : OrangeMarker){
                        ma.setVisible(false);
                    }
                else
                    for(Marker ma : OrangeMarker){
                       ma.setVisible(true);
                    }
                break;
            case R.id.Yellow:
                if (checked)
                    for(Marker mar : YellowMarker){
                        mar.setVisible(false);
                    }
                else
                    for(Marker mar : YellowMarker){
                        mar.setVisible(true);
                    }
                break;
        }
    }

    // THIS NEEDS TO BE IN EVERY ACTIVITY FOR LOCALIZATION
    // From http://stackoverflow.com/questions/40221711/android-context-getresources-updateconfiguration-deprecated/40704077#40704077
    // Also from http://stackoverflow.com/questions/43160062/cannot-get-shared-prefrences-inside-custom-context-wrapper-injection/43160497#43160497
    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences sharedPref = newBase.getSharedPreferences("userLang", Context.MODE_PRIVATE);
        String lang = sharedPref.getString("lang", "");
        super.attachBaseContext(MyContextWrapper.wrap(newBase, lang));
    }
}