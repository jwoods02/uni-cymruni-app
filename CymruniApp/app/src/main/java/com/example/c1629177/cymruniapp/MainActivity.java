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

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i1 = new Intent(MainActivity.this, MapsActivity.class);;
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<String> CymruNi = databaseAccess.getQuotes();
        databaseAccess.close();

        ListView welshSpeakingBusinessView = (ListView) findViewById(R.id.welshSpeakingBusinessList);
        ListAdapter welshSpeakingBusinessAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, CymruNi);
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
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        }
    }
