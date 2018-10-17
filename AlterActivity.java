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

public class AlterActivity extends AppCompatActivity implements View.OnClickListener {
    String calAlt, proAlt, carbAlt, fatAlt;
    TextView calDisplay, proDisplay, carbDisplay, fatDisplay, proteinTV, carbTV, fatTV, calTV, macReqTV;
    Button saveButton;
    SharedPreferences userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //set up full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_alter);

        //get user details from the bundle to alter their caloric values
        Bundle alterBundle = getIntent().getExtras();

        //initialize custom font
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "Fonts/Calculator Bold.ttf");

        //get strings
        //calAlt="Calorie Altered"
        calAlt=alterBundle.getString("calAlt");
        fatAlt=alterBundle.getString("fatAlt");
        proAlt=alterBundle.getString("proAlt");
        carbAlt=alterBundle.getString("carbAlt");

        //initialize and apply font
        calDisplay=(TextView)findViewById(R.id.calDisplay);
        calDisplay.setTypeface(custom_font);
        proDisplay=(TextView)findViewById(R.id.proDisplay);
        proDisplay.setTypeface(custom_font);
        carbDisplay=(TextView)findViewById(R.id.carbDisplay);
        carbDisplay.setTypeface(custom_font);
        fatDisplay=(TextView)findViewById(R.id.fatDisplay);
        fatDisplay.setTypeface(custom_font);
        proteinTV=(TextView) findViewById(R.id.proteinTV);
        proteinTV.setTypeface(custom_font);
        carbTV=(TextView) findViewById(R.id.carbTV);
        carbTV.setTypeface(custom_font);
        fatTV= (TextView) findViewById(R.id.fatTV);
        fatTV.setTypeface(custom_font);
        calTV=(TextView) findViewById(R.id.dailyCalTV);
        calTV.setTypeface(custom_font);
        macReqTV=(TextView) findViewById(R.id.macroReqTV);
        macReqTV.setTypeface(custom_font);

        saveButton=(Button)findViewById(R.id.saveButton);
        if (saveButton!=null){
            saveButton.setOnClickListener(this);
            saveButton.setTypeface(custom_font);
        }

        //set text views to new altered values
        calDisplay.setText(calAlt);
        proDisplay.setText(proAlt);
        carbDisplay.setText(carbAlt);
        fatDisplay.setText(fatAlt);

    }

    @Override
    public void onClick(View v) {

        //initialize shared preferences
        userData = getApplicationContext().getSharedPreferences("details",0);
        SharedPreferences.Editor editor = userData.edit();

        //put altered values
        editor.putString("userCalAlt",calAlt);
        editor.putString("userProAlt",proAlt);
        editor.putString("userCarbAlt",carbAlt);
        editor.putString("userFatAlt",fatAlt);
        editor.apply();

        //finish the page
        finish();

    }
}
