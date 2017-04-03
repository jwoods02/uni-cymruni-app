package com.example.c1629177.cymruniapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;

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

    NotificationCompat.Builder notification;
    private static final int uniqueID = 45612;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        DBNames = databaseAccess.getNames();
        DBBeacons = databaseAccess.getBeacons();
        DBNames = databaseAccess.getNames();
        DBBeacons = databaseAccess.getBeacons();
        databaseAccess.close();

        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);

//        String[] beaconsDetected = {"ABC-12D-123", "WSE-234-DBE"};

        printedBusinessList = new ArrayList<>();

//        for (int i=0; i <DBNames.size(); i++) {
//            if (Arrays.asList(beaconsDetected).contains(DBBeacons.get(i))) {
//                printedBusinessList.add(DBNames.get(i));
//            }
//        }

        welshSpeakingBusinessView = (ListView) findViewById(R.id.welshSpeakingBusinessList);
        welshSpeakingBusinessAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, printedBusinessList);
        welshSpeakingBusinessView.setAdapter(welshSpeakingBusinessAdapter);

        beaconsDetected = new ArrayList<>();

        scanMan = new GCellBeaconScanManager(this);
        scanMan.enableBlueToothAutoSwitchOn(true);

        scanMan.startScanningForBeacons();


        // Basic OnItemClickListener to show how you can interact with user based on item clicked.
        welshSpeakingBusinessView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                        // Shows basic toast to show that app knows which item user has pressed.
                        String businessPicked = getString(R.string.you_selected) +
                                String.valueOf(adapterView.getItemAtPosition(position));

                        Toast.makeText(MainActivity.this, businessPicked, Toast.LENGTH_SHORT).show();

                        String ShopSelected = String.valueOf(adapterView.getItemAtPosition(position));

                        Intent i = new Intent(getApplicationContext(), ShopActivity.class);
                        i.putExtra("SHOP_SELECTED",ShopSelected);
                        startActivity(i);


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

    public void notificationButtonClicked(View view){
        //build the notification here
        // first one is the pictue you want to pop up.
        notification.setSmallIcon(R.drawable.cymrunired);
        // second one is the text that pops up
        notification.setTicker("Cymru Ni - Local Welsh Business nearby");
        // third tells you when it happened in mili seconds
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle(getString(R.string.app_name));
        notification.setContentText(getString(R.string.business_found_nearby));


        //where do you want the notification to go to?
        Intent intent = new Intent(this,MainActivity.class);
        // gives phone access to the intent
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        // builds notification and issues it - issue means sending it to your device.
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID, notification.build());


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