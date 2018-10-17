package com.example.u170011.mycalorieapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class results extends AppCompatActivity implements View.OnClickListener {


    TextView calDisplay, proDisplay, carbDisplay, fatDisplay, calTV, proTV, fatTV, carbTV, adjustTV, macroReqTV;
    String calString, proString, fatString, carbString, calStr2, fatStr2, proStr2, carbStr2;
    double adjustmentMultiplier, calDub, proDub, carbDub,fatDub, calAdjust, fatAdjust, proAdjust, carbAdjust;
    int calories, carbs, fat, protein, calAdjInt, fatAdjInt, carbAdjInt, proAdjInt;
    Spinner adjustmentSpinner;
    Button adSubBtn;
    Boolean adjustment;
    SharedPreferences userData;

    @Override
    public void onClick(View view) {
        //convert values to doubles for accurate adjustments
        calDub=(double) calories;
        proDub=(double) protein;
        carbDub=(double) carbs;
        fatDub=(double) fat;

        //if the user wishes to adjust their values (lose weight/ gain weight)
        if(adjustment) {

            //alter with adjustment multiplier
            calAdjust = (calDub * adjustmentMultiplier);
            fatAdjust = (fatDub * adjustmentMultiplier);
            proAdjust = (proDub * adjustmentMultiplier);
            carbAdjust = (carbDub * adjustmentMultiplier);

            //change to int
            calAdjInt = (int) calAdjust;
            fatAdjInt = (int) fatAdjust;
            carbAdjInt = (int) carbAdjust;
            proAdjInt = (int) proAdjust;

            //change to string
            calStr2 = Integer.toString(calAdjInt);
            fatStr2 = Integer.toString(fatAdjInt);
            carbStr2 = Integer.toString(carbAdjInt);
            proStr2 = Integer.toString(proAdjInt);

            //set up intent to send new values to be displayed in alter activity
            Intent alterIntent = new Intent(this, AlterActivity.class);
            Bundle alterBundle = new Bundle();
            alterBundle.putString("calAlt", calStr2);
            alterBundle.putString("fatAlt", fatStr2);
            alterBundle.putString("carbAlt", carbStr2);
            alterBundle.putString("proAlt", proStr2);
            alterIntent.putExtras(alterBundle);
            startActivity(alterIntent);

            //finish the page
            finish();
        }
        //no adjustment necessary
        else{
            //get the shared prefs
            userData = getApplicationContext().getSharedPreferences("details",0);
            SharedPreferences.Editor editor = userData.edit();

            //put the unaltered details in shared prefs
            editor.putString("userCalAlt",calString);
            editor.putString("userProAlt",proString);
            editor.putString("userCarbAlt",carbString);
            editor.putString("userFatAlt",fatString);
            editor.apply();
            finish();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //set up full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_results);

        //call spinner methods
        addListenertoSpinner();
        addItemsToSpinner();

        //initialize custom font
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "Fonts/Calculator Bold.ttf");

        //initialize text views, edit texts etc
        //apply custom font
        calDisplay = (TextView) findViewById(R.id.calDisplay);
        calDisplay.setTypeface(custom_font);

        proDisplay = (TextView) findViewById(R.id.proDisplay);
        proDisplay.setTypeface(custom_font);

        carbDisplay = (TextView) findViewById(R.id.carbDisplay);
        carbDisplay.setTypeface(custom_font);

        fatDisplay = (TextView) findViewById(R.id.fatDisplay);
        fatDisplay.setTypeface(custom_font);

        calTV=(TextView) findViewById(R.id.dailyCalTV) ;
        calTV.setTypeface(custom_font);

        proTV=(TextView)findViewById(R.id.proteinTV);
        proTV.setTypeface(custom_font);

        fatTV=(TextView)findViewById(R.id.fatTV);
        fatTV.setTypeface(custom_font);

        carbTV=(TextView)findViewById(R.id.carbTV);
        carbTV.setTypeface(custom_font);

        adjustTV=(TextView) findViewById(R.id.adjustmetTV);
        adjustTV.setTypeface(custom_font);

        macroReqTV=(TextView) findViewById(R.id.macroReqTV);
        macroReqTV.setTypeface(custom_font);



        adSubBtn = (Button) findViewById(R.id.adJustmentSubmitBtn);

        if (adSubBtn!=null){
            adSubBtn.setOnClickListener(this);
            adSubBtn.setTypeface(custom_font);
        }

        //get the values from the main activity bundle
        Bundle b =getIntent().getExtras();
        calories = b.getInt("totCals");
        carbs = b.getInt("totCarb");
        fat = b.getInt("totFat");
        protein = b.getInt("totProtein");

        //convert to strings
        calString=Integer.toString(calories);
        proString=Integer.toString(protein);
        fatString=Integer.toString(fat);
        carbString=Integer.toString(carbs);

        //set the text in the text views to the string values
        calDisplay.setText(calString);
        proDisplay.setText(proString);
        carbDisplay.setText(carbString);
        fatDisplay.setText(fatString);



    }
    public void addItemsToSpinner() {
        //spinner for adjustments
        adjustmentSpinner = (Spinner) findViewById(R.id.adjustmentSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.adjustments, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adjustmentSpinner.setAdapter(adapter);
    }

    public void addListenertoSpinner() {
        adjustmentSpinner=(Spinner) findViewById(R.id.adjustmentSpinner);
        adjustmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View arg1, int position, long arg3) {
                //boolean adjustment that returns true when user wants to lose/gain weight, returns false when no adjustment
                adjustment=false;

                //switch statement to assign value to each position of the spinner
                //returns the appropriate adjustment multiplier based on the users preferred goal
                switch (position){
                    case 0:
                        adjustment=true;

                        adjustmentMultiplier=.8;

                        break;

                    case 1:
                        adjustment=false;

                        break;

                    case 2:
                        adjustment=true;

                        adjustmentMultiplier=1.2;

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
