package com.example.u170011.mycalorieapp;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class SharedPrefsActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences userData;
    String calories, protein, fat, carbs;
    TextView heading, calDisplay, proDisplay, carbDisplay, fatDisplay, dailyCalTV, proteinTV,carbTV, fatTV ;
    Button backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //set up full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_shared_prefs);
        //initialize custom font
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "Fonts/Calculator Bold.ttf");

        //initialize text views, edit texts, buttons and apply cutom font
        dailyCalTV=(TextView) findViewById(R.id.dailyCalTV);
        dailyCalTV.setTypeface(custom_font);
        proteinTV=(TextView) findViewById(R.id.proteinTV);
        proteinTV.setTypeface(custom_font);
        carbTV=(TextView) findViewById(R.id.carbTV);
        carbTV.setTypeface(custom_font);
        fatTV=(TextView) findViewById(R.id.fatTV);
        fatTV.setTypeface(custom_font);
        heading=(TextView)findViewById(R.id.macroReqTV);
        heading.setTypeface(custom_font);
        calDisplay=(TextView) findViewById(R.id.calDisplay);
        calDisplay.setTypeface(custom_font);
        proDisplay=(TextView) findViewById(R.id.proDisplay);
        proDisplay.setTypeface(custom_font);
        carbDisplay=(TextView) findViewById(R.id.carbDisplay);
        carbDisplay.setTypeface(custom_font);
        fatDisplay=(TextView) findViewById(R.id.fatDisplay);
        fatDisplay.setTypeface(custom_font);
        backBtn=(Button)findViewById(R.id.sharedPrefsBack);
        backBtn.setTypeface(custom_font);
        if(backBtn !=null){
            backBtn.setOnClickListener(this);
        }

        //get the user details from the shared preferences
        userData=getApplicationContext().getSharedPreferences("details",0);
        calories =userData.getString("userCalAlt", null);
        protein =userData.getString("userProAlt",null);
        carbs =userData.getString("userCarbAlt",null);
        fat =userData.getString("userFatAlt", null);

        //set the text views to these string values
        calDisplay.setText(calories);
        proDisplay.setText(protein);
        carbDisplay.setText(carbs);
        fatDisplay.setText(fat);
    }

    @Override
    public void onClick(View v) {

        finish();
    }
}
