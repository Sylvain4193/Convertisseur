package com.example.convertisseur.models;

import java.util.ArrayList;

public class TemperatureConverter {

    public static class Result {
        final public float celsius;
        final public float fahrenheit;
        final public float kelvin;

        private Result(float celsius, float fahrenheit, float kelvin) {
            this.celsius = celsius;
            this.fahrenheit = fahrenheit;
            this.kelvin = kelvin;
        }
    }

    private ArrayList<Result> conversionsLog = new ArrayList<>();

    public Result convertFromCelsius(float valueC) {
        float valueF = valueC * 1.8f + 32;
        float valueK = valueC + 273f;
        Result res = new Result(valueC, valueF, valueK);

        conversionsLog.add(res);
        return res;
    }

    public Result convertFromFahrenheit(float valueF) {
        float valueC = (valueF - 32) / 1.8f;
        float valueK = valueF * 1.8f + 32 + 275;
        Result res = new Result(valueC, valueF, valueK);

        conversionsLog.add(res);
        return res;
    }

    public Result convertFromKelvin(float valueK) {
        float valueC = valueK - 275;
        float valueF = (valueK - 32 - 275)/1.8f;
        Result res = new Result(valueC, valueF, valueK);

        conversionsLog.add(res);
        return res;
    }
}