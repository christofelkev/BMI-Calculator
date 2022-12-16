package org.chrivin.mybmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    // fields
    private TextView resultText;
    private Button calculateButton;
    private RadioButton maleBtn;
    private RadioButton femaleBtn;
    private EditText editTextAge;
    private EditText editTextFeet;
    private EditText editTextInch;
    private EditText editTextWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();

    }

    private void findViews(){
        resultText = findViewById(R.id.txtResult);
        maleBtn = findViewById(R.id.rbMale);
        femaleBtn = findViewById(R.id.rbFemale);
        editTextAge = findViewById(R.id.edtTxtAge);
        editTextFeet = findViewById(R.id.edtTxtFeet);
        editTextInch = findViewById(R.id.edtTxtInch);
        editTextWeight = findViewById(R.id.edtTxtWeight);
        calculateButton = findViewById(R.id.buttonCalculate);
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmiResult = calculateBmi();

                String ageText = editTextAge.getText().toString();
                int age = Integer.parseInt(ageText);
                if (age >= 18){
                    displayResult(bmiResult);
                }else {
                    displayGuidance(bmiResult);
                }

            }
        });
    }

    private double calculateBmi() {
        String feetText = editTextFeet.getText().toString();
        String inchText = editTextInch.getText().toString();
        String weightText = editTextWeight.getText().toString();

        // change input String to int
        int feet = Integer.parseInt(feetText);
        int inch = Integer.parseInt(inchText);
        int weight = Integer.parseInt(weightText);

        int totalInch = (feet * 12) + inch;

        // height in meter is the inches multiplied by 0.0254
        double heightInMeters = totalInch * 0.0254;

        // bmi formula = weight in kg divided by height in meter square
        return weight / (heightInMeters * heightInMeters);

    }

    private void displayResult(double bmi) {
        DecimalFormat myDecimalFormat = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormat.format(bmi);

        String fullResultString;
        if (bmi < 18.5){
            // display underweight
            fullResultString = bmiTextResult + " - You are underweight";
        }else if (bmi > 25){
            // display overweight
            fullResultString = bmiTextResult + " - You are overweight";
        }else{
            // display healthy
            fullResultString = bmiTextResult + " - You are healthy";
        }

        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormat = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormat.format(bmi);

        String fullResultString;
        if (maleBtn.isChecked()){
            // Display boy guidance
            fullResultString = bmiTextResult + " - as you are under 18, please consult with your doctor for the healthy range for boys";
        }else if (femaleBtn.isChecked()){
            // display female guidance
            fullResultString = bmiTextResult + " - as you are under 18, please consult with your doctor for the healthy range for girls";
        }else {
            // display general guidance
            fullResultString = bmiTextResult + " - as you are under 18, please consult with your doctor for the healthy range";
        }
        resultText.setText(fullResultString);
    }


}
