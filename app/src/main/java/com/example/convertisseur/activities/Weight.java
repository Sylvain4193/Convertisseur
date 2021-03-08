package com.example.convertisseur.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.convertisseur.R;
import com.example.convertisseur.models.WeightConverter;

import java.util.Locale;

public class Weight extends AppCompatActivity {

    EditText input;
    EditText output;
    WeightConverter weightConverter;
    static final String PREF_WEIGHT_INPUT = "weight.input";
    static final String PREF_WEIGHT_OUTPUT = "weight.output";
    private static final String PREFS_SPINNER1 = "weight.spinner1";
    private static final String PREFS_SPINNER2 = "weight.spinner2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        input = findViewById(R.id.input_temperature);
        output = findViewById(R.id.output_temperature);

        weightConverter();

    }


    void weightConverter() {
        // initialize model
        weightConverter = new WeightConverter();

        Spinner spinner1 = (Spinner) findViewById(R.id.temperature_spinner_1);
        Spinner spinner2 = (Spinner) findViewById(R.id.temperature_spinner_2);

        Resources res = getResources();
        String[] labels = res.getStringArray(R.array.spinner_weight_1);

        // fetch UI views
        final Button btn = findViewById(R.id.button_temperature_convert);
        final EditText input = findViewById(R.id.input_temperature);
        final TextView output = findViewById(R.id.output_temperature);

        // Retrieve spinner position from sharedpreferences
        SharedPreferences sharedPref = getSharedPreferences(PREFS_SPINNER1, MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner1",-1);
        if(spinnerValue != -1)
            // set the value of the spinner
            spinner1.setSelection(spinnerValue);

        // Retrieve spinner2 position from sharedpreferences
        SharedPreferences sharedPref3 = getSharedPreferences(PREFS_SPINNER2, MODE_PRIVATE);
        int spinnerValue2 = sharedPref3.getInt("userChoiceSpinner2",-1);
        if(spinnerValue2 != -1)
            // set the value of the spinner
            spinner2.setSelection(spinnerValue2);


        // restore UI state from SharedPreferences
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        input.setText(preferences.getString(PREF_WEIGHT_INPUT, input.getText().toString()));
        output.setText(preferences.getString(PREF_WEIGHT_OUTPUT, output.getText().toString()));

        // setup UI interactions
        btn.setOnClickListener((v) -> {
            try {
                String spinText = spinner1.getSelectedItem().toString();
                String spinText2 = spinner2.getSelectedItem().toString();

                float inputValue = Float.parseFloat(input.getText().toString());
                float outputValue;
                if (spinText.equals(labels[2])) {
                    if (spinText2.equals(labels[2]))
                        outputValue = weightConverter.convertFromGram(inputValue).gram;
                    else if (spinText2.equals(labels[1]))
                        outputValue = weightConverter.convertFromGram(inputValue).pound;
                    else
                        outputValue = weightConverter.convertFromGram(inputValue).kilogram;
                }
                else if (spinText.equals(labels[1])) {
                    if (spinText2.equals(labels[2]))
                        outputValue = weightConverter.convertFromPound(inputValue).gram;
                    else if (spinText2.equals(labels[1]))
                        outputValue = weightConverter.convertFromPound(inputValue).pound;
                    else
                        outputValue = weightConverter.convertFromPound(inputValue).kilogram;
                }
                    else{
                    if(spinText2.equals(labels[2]))
                        outputValue = weightConverter.convertFromKilogram(inputValue).gram;
                    else if(spinText2.equals(labels[1]))
                        outputValue = weightConverter.convertFromKilogram(inputValue).pound;
                    else
                        outputValue = weightConverter.convertFromKilogram(inputValue).kilogram;
                    }

                    output.setText(String.format(Locale.getDefault(), getString(R.string.format), outputValue));
            } catch (Exception e) {
                output.setText(R.string.invalid_input);
            }

            // save the state
            getPreferences(MODE_PRIVATE)
                    .edit()
                    .putString(PREF_WEIGHT_INPUT, input.getText().toString())
                    .putString(PREF_WEIGHT_OUTPUT, output.getText().toString())
                    .apply();

            // save input spinner position to SharedPreferences
            int userChoice = spinner1.getSelectedItemPosition();
            SharedPreferences sharedPref2 = getSharedPreferences(PREFS_SPINNER1,0);
            SharedPreferences.Editor prefEditor = sharedPref2.edit();
            prefEditor.putInt("userChoiceSpinner1", userChoice);
            prefEditor.commit();
            // save input spinner2 position to SharedPreferences
            int userChoice2 = spinner2.getSelectedItemPosition();
            SharedPreferences sharedPref4 = getSharedPreferences(PREFS_SPINNER2,0);
            SharedPreferences.Editor prefEditor2 = sharedPref4.edit();
            prefEditor2.putInt("userChoiceSpinner2", userChoice2);
            prefEditor2.commit();
        });
    }
}