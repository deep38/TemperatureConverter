package com.collegepractical.temperatureconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText fromTemperatureEditText;
    AppCompatTextView toTemperatureTextView;

    Spinner fromUnitSpinner;
    Spinner toUnitSpinner;

    Button convertButton;

    Converter converter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fromTemperatureEditText = findViewById(R.id.fromTemperatureEditText);
        toTemperatureTextView = findViewById(R.id.toTemperatureEditText);
        fromUnitSpinner = findViewById(R.id.fromUnitSpinner);
        toUnitSpinner = findViewById(R.id.toUnitSpinner);
        convertButton = findViewById(R.id.convertButton);

        converter = new Converter();

        ArrayAdapter<TemperatureUnit> unitAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, TemperatureUnit.values());

        fromUnitSpinner.setAdapter(unitAdapter);
        toUnitSpinner.setAdapter(unitAdapter);

        convertButton.setOnClickListener(view -> {
            updateTemperature();
        });

        fromUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateTemperature();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        toUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateTemperature();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void updateTemperature() {
        String fromTemperature = fromTemperatureEditText.getText().toString();
        if (!fromTemperature.equals("")) {
            String toTemperature = getToTemperature(fromTemperature);
            toTemperatureTextView.setText(toTemperature);
            Toast.makeText(this, "Converted to " + toUnitSpinner.getSelectedItem() + ": " + toTemperature, Toast.LENGTH_LONG).show();
        } else {
            toTemperatureTextView.setText("");
        }

    }

    private String getToTemperature(String fromTemperature) {
        String convertedTemperature;
        double temperature = Double.parseDouble(fromTemperature);

        TemperatureUnit fromUnit = (TemperatureUnit) fromUnitSpinner.getSelectedItem();
        TemperatureUnit toUnit = (TemperatureUnit) toUnitSpinner.getSelectedItem();

        convertedTemperature = String.format( Locale.US, "%.2f", converter.convert(temperature, fromUnit, toUnit));

        return convertedTemperature;
    }

}