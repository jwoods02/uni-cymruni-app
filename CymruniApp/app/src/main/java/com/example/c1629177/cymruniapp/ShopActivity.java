package com.example.c1629177.cymruniapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShopActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        Bundle extras = getIntent().getExtras();

        if (extras == null) {
            return;
        }
        //-------------------- Extra --------------------------------------------------------------

        String title = getIntent().getStringExtra("SHOP_SELECTED");
        // title.getClass().getName();

        //-------------------- Database --------------------------------------------------------------git

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        //Getting description

        String DESC = databaseAccess.getDescription(title);

        databaseAccess.close();

        //-------------------- Title --------------------------------------------------------------


        TextView titlelayout = (TextView) findViewById(R.id.Title);
        titlelayout.setText(title);

        //-------------------- Content --------------------------------------------------------------


        TextView desc = (TextView) findViewById(R.id.Desc);
        desc.setText(DESC);
    }
}
