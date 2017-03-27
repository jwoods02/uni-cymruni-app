package com.example.c1629177.cymruniapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<String> DBNames = databaseAccess.getNames();
        List<String> DBBeacons = databaseAccess.getBeacons();
        databaseAccess.close();



        String[] beaconsDetected = {"ABC-12D-123", "WSE-234-DBE"};

        List<String> printedBusinessList = new ArrayList<>();

        for (int i=0; i <DBNames.size(); i++) {
            if (Arrays.asList(beaconsDetected).contains(DBBeacons.get(i))) {
                printedBusinessList.add(DBNames.get(i));
            }
        }



        ListView welshSpeakingBusinessView = (ListView) findViewById(R.id.welshSpeakingBusinessList);
        ListAdapter welshSpeakingBusinessAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, printedBusinessList);
        welshSpeakingBusinessView.setAdapter(welshSpeakingBusinessAdapter);


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
    }
