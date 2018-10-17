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

public class name_password extends AppCompatActivity implements View.OnClickListener {
    TextView usernameTV, passwordTV;
    EditText userET, passwordET;
    Button password_btn;
    String userNameStr, passWordStr;
    SharedPreferences userDetails;
    Boolean correct;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_password);
        //initialize custom font
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "Fonts/Calculator Bold.ttf");

        //initialize text views, edit texts, button and apply custom font
        usernameTV = (TextView) findViewById(R.id.userTV);
        usernameTV.setTypeface(custom_font);
        passwordTV = (TextView) findViewById(R.id.passwordTV);
        passwordTV.setTypeface(custom_font);
        userET = (EditText) findViewById(R.id.userET);
        passwordET = (EditText) findViewById(R.id.passwordET);
        password_btn = (Button) findViewById(R.id.user_password_btn);

        //avoids null pointer exception
        if (password_btn != null) {
            //set on click listener and custom font
            password_btn.setOnClickListener(this);
            password_btn.setTypeface(custom_font);
        }

    }

    @Override
    public void onClick(View v) {
        //get the username and password from shared preferences
        userDetails = getApplicationContext().getSharedPreferences("details", 0);
        userNameStr = userDetails.getString("userName", null);
        passWordStr = userDetails.getString("password", null);

        if (userET.getText().toString().equals(userNameStr) && passwordET.getText().toString().equals(passWordStr)){
            //if the input is equal to the user name and password from shared preferences
            correct=true;
            //credentials are correct

        }else{
            correct=false;
            //credentials not correct
        }

        if (correct) {
            //if credentials are correct
            Toast.makeText(this, "Your Personal Macronutrient Requirements", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(getApplicationContext(),SharedPrefsActivity.class);
            //start the intent taking the user to the shared prefs page
            startActivity(intent);
            finish();
            //finish the page

        }

        else {
            //else input does not match strings from shared prefs
            counter--;
            //decrement the counter

            if (counter == 0) {
                //user has entered incorrect details three times
                Toast.makeText(this, "You have entered the incorrect credentials too many times!", Toast.LENGTH_LONG).show();
                finish();
                //end the page
            }
            else {
                //counter has not reached 0
                Toast.makeText(this, "Wrong Credentials, Try Again", Toast.LENGTH_LONG).show();
                //prompt user to try again
            }
        }
    }

}

