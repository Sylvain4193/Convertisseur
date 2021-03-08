package com.example.convertisseur.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.convertisseur.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_length = findViewById(R.id.button_length);
        button_length.setOnClickListener((v) -> {
            Intent intent = new Intent(this, Length.class);
            startActivity(intent);
        });

        Button button_temperature = findViewById(R.id.button_temperature);
        button_temperature.setOnClickListener((v) -> {
            Intent intent = new Intent(this, Temperature.class);
            startActivity(intent);
        });

        Button button_weight = findViewById(R.id.button_temperature_convert);
        button_weight.setOnClickListener((v) -> {
            Intent intent = new Intent(this, Weight.class);
            startActivity(intent);
        });
    }
}