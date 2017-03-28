package com.example.c1629177.cymruniapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.gcell.ibeacon.gcellbeaconscanlibrary.GCellBeaconManagerScanEvents;
import com.gcell.ibeacon.gcellbeaconscanlibrary.GCellBeaconRegion;
import com.gcell.ibeacon.gcellbeaconscanlibrary.GCellBeaconScanManager;
import com.gcell.ibeacon.gcellbeaconscanlibrary.GCelliBeacon;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GCellBeaconManagerScanEvents {

    GCellBeaconScanManager scanMan;
    ArrayList<String> beaconsDetected;
    List<String> DBNames;
    List<String> DBBeacons;
    List<String> printedBusinessList;
    String currentBeacon;
    int DBIndex;
    ListView welshSpeakingBusinessView;
    ListAdapter welshSpeakingBusinessAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<String> DBNames = databaseAccess.getNames();
        List<String> DBBeacons = databaseAccess.getBeacons();
        DBNames = databaseAccess.getNames();
        DBBeacons = databaseAccess.getBeacons();
        databaseAccess.close();

//        String[] beaconsDetected = {"ABC-12D-123", "WSE-234-DBE"};

        printedBusinessList = new ArrayList<>();

        String[] beaconsDetected = {"ABC-12D-123", "WSE-234-DBE"};

        List<String> printedBusinessList = new ArrayList<>();

//        for (int i=0; i <DBNames.size(); i++) {
//            if (Arrays.asList(beaconsDetected).contains(DBBeacons.get(i))) {
//                printedBusinessList.add(DBNames.get(i));
//            }
//        }

        welshSpeakingBusinessView = (ListView) findViewById(R.id.welshSpeakingBusinessList);
        welshSpeakingBusinessAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, printedBusinessList);
        welshSpeakingBusinessView.setAdapter(welshSpeakingBusinessAdapter);

       /*TODO fix this  beaconsDetected = new ArrayList<>(); */

        scanMan = new GCellBeaconScanManager(this);
        scanMan.enableBlueToothAutoSwitchOn(true);

        scanMan.startScanningForBeacons();


        // Basic OnItemClickListener to show how you can interact with user based on item clicked.
        welshSpeakingBusinessView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                     @Override
                     public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                        // Shows basic toast to show that app knows which item user has pressed.
                         String businessPicked = "You selected " +
                                 String.valueOf(adapterView.getItemAtPosition(position));

                         Toast.makeText(MainActivity.this, businessPicked, Toast.LENGTH_SHORT).show();


                     }
                 });

        Button GoToLoginBtn = (Button) findViewById(R.id.GoToLoginBtn);
        GoToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });
        }

    @Override
    public void onGCellUpdateBeaconList(List<GCelliBeacon> list) {
        for (GCelliBeacon beacon : list) {
            currentBeacon = beacon.getProxUuid().getStringFormattedUuid();

            if(!beaconsDetected.contains(currentBeacon)) {
                beaconsDetected.add(currentBeacon);

                if (DBBeacons.contains(currentBeacon)) {

                    DBIndex = DBBeacons.indexOf(currentBeacon);

                    printedBusinessList.add(DBNames.get(DBIndex));
                    ((BaseAdapter)welshSpeakingBusinessAdapter).notifyDataSetChanged();

                    // ADD NOTIFACTION HERE


                }
            }
        }
    }

    @Override
    public void didEnterBeaconRegion(GCellBeaconRegion gCellBeaconRegion) {

    }

    @Override
    public void didExitBeaconRegion(GCellBeaconRegion gCellBeaconRegion) {

    }

    @Override
    public void didRangeBeaconsinRegion(GCellBeaconRegion gCellBeaconRegion, List<GCelliBeacon> list) {

    }

    @Override
    public void bleNotSupported() {

    }

    @Override
    public void bleNotEnabled() {

    }

    @Override
    public void locationPermissionsDenied() {

    }
}
