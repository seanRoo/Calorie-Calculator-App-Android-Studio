package com.example.u170011.mycalorieapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class create_account extends AppCompatActivity implements View.OnClickListener {

    TextView createTV, userTV, passwordTV;
    EditText userET, passwordET;
    Button createBtn;
    String userName, password;
    SharedPreferences user_pass;
    Boolean acceptable=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        //initialize custom font
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "Fonts/Calculator Bold.ttf");

        //initialize text views, edit texts and button
        //apply custom font to all
        createTV = (TextView) findViewById(R.id.createTV);
        createTV.setTypeface(custom_font);
        userTV = (TextView) findViewById(R.id.userTV);
        userTV.setTypeface(custom_font);
        passwordTV = (TextView) findViewById(R.id.passwordTV);
        passwordTV.setTypeface(custom_font);
        userET = (EditText) findViewById(R.id.userET);
        userET.setTypeface(custom_font);
        passwordET = (EditText) findViewById(R.id.passwordET);
        passwordET.setTypeface(custom_font);
        createBtn = (Button) findViewById(R.id.create_btn);
        createBtn.setTypeface(custom_font);

        //avoiding null pointer exception
        if (createBtn != null) {
            createBtn.setOnClickListener(this);
        }

    }



    @Override
    public void onClick(View v) {
        //get users user name and password from the edit texts, convert to string
        userName=userET.getText().toString();
        password = passwordET.getText().toString();

        //ensure user name and password are at least of length 4
        if(userName.length()>=4 && password.length()>=4) {

            //boolean to ensure the details are of correct length
            acceptable=true;
        }
        else{
            acceptable=false;

        }
        //initialize shared preferences to put user details
        user_pass = getApplicationContext().getSharedPreferences("details", 0);
        SharedPreferences.Editor details_editor = user_pass.edit();

        //if user details are acceptable
        if (acceptable) {

            //put user details in shares preferences
            details_editor.putString("userName", userName);
            details_editor.putString("password", password);

            //apply
            details_editor.apply();

            //start intent to go to main activity
            Intent intent = new Intent(create_account.this, MainActivity.class);
            startActivity(intent);

            //close the page
            finish();
        }
        else {
            //details are not acceptable
            //use a toast to tell the user
            Toast.makeText(this, "Username and Password must be at least four symbols.", Toast.LENGTH_LONG).show();
        }


    }
}
