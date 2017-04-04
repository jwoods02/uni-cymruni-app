package com.example.c1629177.cymruniapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by c1629177 on 03/04/2017.
 */
public class Pop extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popupwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.2));
    }

    public void yesyes(View view){
        Intent fave = new Intent(Pop.this, FaveActivity.class);
        startActivity(fave);
    }

    public void nono(View view){
    }
}
