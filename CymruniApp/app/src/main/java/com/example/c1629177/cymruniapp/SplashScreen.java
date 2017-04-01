package com.example.c1629177.cymruniapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    public void enLangSelected(View view){
        Toast.makeText(SplashScreen.this, "EN selected", Toast.LENGTH_SHORT).show();
        saveLanguage("en");
        Intent x = new Intent(
                SplashScreen.this, MainActivity.class);
        startActivity(x);
    }

    public void cyLangSelected(View view){
        Toast.makeText(SplashScreen.this, "CY selected", Toast.LENGTH_SHORT).show();
        saveLanguage("cy");
        Intent x = new Intent(
                SplashScreen.this, MainActivity.class);
        startActivity(x);
    }

    public void saveLanguage(String lang){
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("lang", lang);
        editor.apply();
    }


    // THIS NEEDS TO BE IN EVERY ACTIVITY FOR LOCALIZATION
    // From http://stackoverflow.com/questions/40221711/android-context-getresources-updateconfiguration-deprecated/40704077#40704077
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(MyContextWrapper.wrap(newBase,"en"));
    }



}
