package com.example.c1629177.cymruniapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.media.MediaRouteProvider;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity {

    Button submitBtn;
    EditText usernameText, passwordText;
    private TextView info;
    private LoginButton loginButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        FacebookSdk.setApplicationId(getString(R.string.facebook_app_id));
        setContentView(R.layout.activity_login);
        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);


        submitBtn = (Button) findViewById(R.id.submitBtn);
        usernameText = (EditText) findViewById(R.id.usernameText);
        passwordText = (EditText) findViewById(R.id.passwordText);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean loginValid;
                databaseAccess.open();
                loginValid = databaseAccess.loginTest(usernameText.getText().toString(), passwordText.getText().toString());
                databaseAccess.close();

                if ( loginValid ) {
                    Toast.makeText(getApplicationContext(),getString(R.string.redirecting),Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.incorrect_login), Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        callbackManager = CallbackManager.Factory.create();
        info = (TextView) findViewById(R.id.info);
        loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>(){
        @Override
        public void onSuccess(LoginResult loginResult){
            info.setText(
                    "User ID: " +loginResult.getAccessToken().getUserId() +"\n" +"Auth Token: " +loginResult.getAccessToken().getToken()
            );
        }
        @Override
        public void onCancel(){
            info.setText("Login attempt canceled.");
        }
        @Override
        public void onError(FacebookException e){
            info.setText("Login attempt failed.");
        }
    });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
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
