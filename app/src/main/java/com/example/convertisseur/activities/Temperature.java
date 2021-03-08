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
import com.example.convertisseur.models.TemperatureConverter;

import java.util.Locale;


public class Temperature extends AppCompatActivity {

    EditText input;
    EditText output;
    TemperatureConverter temperatureConverter;
    static final String PREF_TEMP_INPUT = "temperature.input";
    static final String PREF_TEMP_OUTPUT = "temperature.output";
    private static final String PREFS_SPINNER1 = "temperature.spinner1";
    private static final String PREFS_SPINNER2 = "temperature.spinner2";


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_temperature);
            input = findViewById(R.id.input_temperature);
            output = findViewById(R.id.output_temperature);

            temperatureConverter();
        }



    void temperatureConverter() {
        // initialize model
        temperatureConverter = new TemperatureConverter();

        Spinner spinner1 = (Spinner) findViewById(R.id.temperature_spinner_1);
        Spinner spinner2 = (Spinner) findViewById(R.id.temperature_spinner_2);

        Resources res = getResources();
        String[] labels = res.getStringArray(R.array.spinner_temperature_1);

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
        input.setText(preferences.getString(PREF_TEMP_INPUT, input.getText().toString()));
        output.setText(preferences.getString(PREF_TEMP_OUTPUT, output.getText().toString()));

        // setup UI interactions
        btn.setOnClickListener((v) -> {
            try {
                String spinText = spinner1.getSelectedItem().toString();
                String spinText2 = spinner2.getSelectedItem().toString();

                float inputValue = Float.parseFloat(input.getText().toString());
                float outputValue;
                if (spinText.equals(labels[2])) {
                    if (spinText2.equals(labels[2]))
                        outputValue = temperatureConverter.convertFromFahrenheit(inputValue).fahrenheit;
                    else if (spinText2.equals(labels[1]))
                        outputValue = temperatureConverter.convertFromFahrenheit(inputValue).kelvin;
                    else
                        outputValue = temperatureConverter.convertFromFahrenheit(inputValue).celsius;
                }
                else if (spinText.equals(labels[1])) {
                    if (spinText2.equals(labels[2]))
                        outputValue = temperatureConverter.convertFromKelvin(inputValue).fahrenheit;
                    else if (spinText2.equals(labels[1]))
                        outputValue = temperatureConverter.convertFromKelvin(inputValue).kelvin;
                    else
                        outputValue = temperatureConverter.convertFromKelvin(inputValue).celsius;
                }
                else{
                    if(spinText2.equals(labels[2]))
                        outputValue = temperatureConverter.convertFromCelsius(inputValue).fahrenheit;
                    else if(spinText2.equals(labels[1]))
                        outputValue = temperatureConverter.convertFromCelsius(inputValue).kelvin;
                    else
                        outputValue = temperatureConverter.convertFromCelsius(inputValue).celsius;
                }

                output.setText(String.format(Locale.getDefault(), getString(R.string.format), outputValue));
            } catch (Exception e) {
                output.setText(R.string.invalid_input);
            }

            // save the state
            getPreferences(MODE_PRIVATE)
                    .edit()
                    .putString(PREF_TEMP_INPUT, input.getText().toString())
                    .putString(PREF_TEMP_OUTPUT, output.getText().toString())
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