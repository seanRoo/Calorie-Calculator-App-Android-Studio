package com.example.u170011.mycalorieapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {
    Intent intent;
    SharedPreferences user_pass;
    String userNameStr, passWordStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //set up full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);

        //initialize text views and buttons
        TextView tx = (TextView)findViewById(R.id.headingTV);

        //button to start a new calculation
        Button newCalcBtn=(Button)findViewById(R.id.newCalcBtn);

        //button to view previously calculated values
        Button oldCalcButton= (Button)findViewById(R.id.oldCalcBtn);

        //initialize custom font
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "Fonts/Calculator Bold.ttf");

        //get user name and password from shared preferences
        user_pass = getApplicationContext().getSharedPreferences("details",0);
        userNameStr = user_pass.getString("userName", null);
        passWordStr = user_pass.getString("password", null);

        //apply custom font to text view and buttons
        tx.setTypeface(custom_font);
        newCalcBtn.setTypeface(custom_font);
        oldCalcButton.setTypeface(custom_font);


    }
    //method for when new calcuation button is clicked
    public void newCalc(View view){
        //start the intent that takes the user to the 'create account' page
        intent = new Intent(StartActivity.this, create_account.class);
        startActivity(intent);
    }
    //method to view users personal previously calculated values when existing calculation button is clicked
    public void oldCalc(View view){
        //intent takes user to a pae where they enter their user name and password to view their values
            intent= new Intent(StartActivity.this,name_password.class);
            startActivity(intent);

    }
}
