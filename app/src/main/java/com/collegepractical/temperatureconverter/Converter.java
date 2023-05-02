package com.collegepractical.temperatureconverter;

import android.util.Log;

import java.util.HashMap;
import java.util.Locale;

public class Converter {
     private HashMap<TemperatureUnit, HashMap<TemperatureUnit, Double>> factors = new HashMap<>();
     private HashMap<TemperatureUnit, HashMap<TemperatureUnit, Double>> values = new HashMap<>();

     public Converter() {
          /// Celsius to Kelvin -
          /// Celsius to Fahrenheit - ((9/5) * C) + 32
          /// Kelvin to Celsius - K - 273.15
          /// Kelvin to Fahrenheit  - (K - 273.15) * 9/5 + 32
          /// Fahrenheit to Celsius -
          /// Fahrenheit to Kelvin - (( 5/9 ) * K - 273.15) - 32

          HashMap<TemperatureUnit, Double> fromCelsiusFactors = new HashMap<>();
          fromCelsiusFactors.put(TemperatureUnit.Kelvin, 1d);
          fromCelsiusFactors.put(TemperatureUnit.Fahrenheit, 9.0 / 5);
          fromCelsiusFactors.put(TemperatureUnit.Celsius, 1d);

          HashMap<TemperatureUnit, Double> fromKelvinFactors = new HashMap<>();
          fromKelvinFactors.put(TemperatureUnit.Kelvin, 1d);
          fromKelvinFactors.put(TemperatureUnit.Fahrenheit, 9.0 / 5);
          fromKelvinFactors.put(TemperatureUnit.Celsius, 1d);

          HashMap<TemperatureUnit, Double> fromFahrenheitFactors = new HashMap<>();
          fromFahrenheitFactors.put(TemperatureUnit.Kelvin, 5.0 / 9);
          fromFahrenheitFactors.put(TemperatureUnit.Fahrenheit, 1d);
          fromFahrenheitFactors.put(TemperatureUnit.Celsius, 5.0 / 9);

          factors.put(TemperatureUnit.Celsius, fromCelsiusFactors);
          factors.put(TemperatureUnit.Kelvin, fromKelvinFactors);
          factors.put(TemperatureUnit.Fahrenheit, fromFahrenheitFactors);

          HashMap<TemperatureUnit, Double> fromCelsiusValues = new HashMap<>();
          fromCelsiusValues.put(TemperatureUnit.Kelvin, 273.15);
          fromCelsiusValues.put(TemperatureUnit.Fahrenheit, 32d);
          fromCelsiusValues.put(TemperatureUnit.Celsius, 0d);

          HashMap<TemperatureUnit, Double> fromKelvinValues = new HashMap<>();
          fromKelvinValues.put(TemperatureUnit.Kelvin, 0d);
          fromKelvinValues.put(TemperatureUnit.Fahrenheit, 32d);
          fromKelvinValues.put(TemperatureUnit.Celsius, -273.15);

          HashMap<TemperatureUnit, Double> fromFahrenheitValues = new HashMap<>();
          fromFahrenheitValues.put(TemperatureUnit.Kelvin, -32d);
          fromFahrenheitValues.put(TemperatureUnit.Fahrenheit, 0d);
          fromFahrenheitValues.put(TemperatureUnit.Celsius, -32d);

          values.put(TemperatureUnit.Celsius, fromCelsiusValues);
          values.put(TemperatureUnit.Kelvin, fromKelvinValues);
          values.put(TemperatureUnit.Fahrenheit, fromFahrenheitValues);
     }

     public double convert(double temperature, TemperatureUnit from, TemperatureUnit to) throws NullPointerException {

          if (from == TemperatureUnit.Kelvin && to != TemperatureUnit.Celsius) {
               temperature = convert(temperature, TemperatureUnit.Kelvin, TemperatureUnit.Celsius);
               from = TemperatureUnit.Celsius;
          } else if (from != TemperatureUnit.Celsius && to == TemperatureUnit.Kelvin) {
               temperature = convert(temperature, from, TemperatureUnit.Celsius);
               from = TemperatureUnit.Celsius;
          }

          if (from == TemperatureUnit.Fahrenheit) {
               return (temperature + values.get(from).get(to)) * factors.get(from).get(to);
          }
          return temperature * factors.get(from).get(to) + values.get(from).get(to);
     }
}
