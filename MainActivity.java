package com.example.u170011.mycalorieapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner mySpinner;
    TextView activityTv, genderTV, ageTV, heightTV, weightTV, detailsTV;
    EditText ageET, heightET, weightET;
    double dubAge, dubWeight, dubHeight, reeMultiplier, calories, kgToLbs, weightInLbs, protein, fat, carbCal, carbs;
    int calInt, proInt, carbInt, fatInt;
    Button subBtn;
    String genSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set up full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //call spinner methods
        addItemsToSpinner();
        addListenertoSpinner();

        //initialize custom font
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "Fonts/Calculator Bold.ttf");

        //initialize text views, edit texts and button
        //apply custom font

        ageET = (EditText) findViewById(R.id.ageET);
        ageET.setTypeface(custom_font);

        heightET = (EditText) findViewById(R.id.heightET);
        heightET.setTypeface(custom_font);

        weightET = (EditText) findViewById(R.id.weightET);
        weightET.setTypeface(custom_font);

        subBtn = (Button) findViewById(R.id.submitButton);
        subBtn.setTypeface(custom_font);

        activityTv = (TextView) findViewById(R.id.activityTV);
        activityTv.setTypeface(custom_font);

        genderTV = (TextView) findViewById(R.id.genderTV);
        genderTV.setTypeface(custom_font);

        ageTV = (TextView) findViewById(R.id.ageTV);
        ageTV.setTypeface(custom_font);

        weightTV = (TextView) findViewById(R.id.weightTV);
        weightTV.setTypeface(custom_font);

        heightTV = (TextView) findViewById(R.id.heightTV);
        heightTV.setTypeface(custom_font);

        detailsTV=(TextView) findViewById(R.id.detailsTV);
        detailsTV.setTypeface(custom_font);




        //avoids null pointer exception
        if (subBtn != null) {
            subBtn.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        //convert values taken from edit texts to double for more accurate calculation
        dubAge=Double.parseDouble(ageET.getText().toString());
        dubWeight=Double.parseDouble(weightET.getText().toString());
        dubHeight=Double.parseDouble(heightET.getText().toString());

        //start the intent to send data to results page
        Intent intent = new Intent(this, results.class);
        //putting all data in one bundle to send to next page
        Bundle b= new Bundle();

        //if user clicks 'male'
        if(genSelect.equals("male")) {
            //male calorie calculation
            //round the value
            calories = Math.round(((10 * dubWeight) + (6.25 * dubHeight) - (5 * dubAge) + 5) * reeMultiplier);

            //convert double value to int
            calInt = (int)calories;
        }
        //if 'female'
        else if (genSelect.equals("female")) {
            //female calculation
            //round the value
            calories = Math.round(((10 * dubWeight) + (6.25 * dubHeight) - (5 * dubAge) - 161) * reeMultiplier);

            //convert to int
            calInt = (int) calories;
        }

        kgToLbs =2.20462; //default pound value for one kg

        //get weight in pounds as calculations are carried out in pounds
        weightInLbs = dubWeight * kgToLbs;
        //protein calculation
        protein = weightInLbs * .825;
        //convert to int
        proInt = (int) protein;

        //fat calculation
        fat= Math.round((calories * .26)/9);
        //convert to int
        fatInt = (int) fat;

        //carbohydrate calculation
        carbCal= calories-((protein * 4)+(fat * .25));
        carbs = (carbCal/4.8);
        //convert to int
        carbInt = (int) carbs;

        //put values in the bundle
        b.putInt("totCals",calInt);
        b.putInt("totProtein",proInt);
        b.putInt("totFat",fatInt);
        b.putInt("totCarb", carbInt);

        //put bundle in the intent
        intent.putExtras(b);
        startActivity(intent);

        //close the page
        finish();
    }


    public void onRadioGroupClick(View v){
        //method to retrieve gender selection
        switch (v.getId()) {
            case R.id.radioMale:
                //if the male button is clicked return 'male'
                genSelect="male";

                break;




            case R.id.radioFemale:
                //same for female button
                genSelect="female";

                break;



        }
    }

    public void addItemsToSpinner() {
        //initialize spinner
        mySpinner = (Spinner) findViewById(R.id.spinner);

        //array adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.activity_level, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
    }



    public void addListenertoSpinner() {
        mySpinner = (Spinner) findViewById(R.id.spinner);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View arg1, int position, long arg3) {
                switch (position){
                    //switch statement to get level of activity from user and adjust 'REE' multiplier accordingly
                    //REE multiplier is used in calorie calculation to adjust caloric values based on activity level
                    case 0:

                        reeMultiplier=1.2;

                        break;

                    case 1:

                        reeMultiplier=1.375;

                        break;

                    case 2:

                        reeMultiplier=1.55;

                        break;

                    case 3:

                        reeMultiplier=1.725;

                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }




}
