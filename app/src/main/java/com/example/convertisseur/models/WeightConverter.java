package com.example.convertisseur.models;

import java.util.ArrayList;

public class WeightConverter {

    public static class Result {
        final public float pound;
        final public float kilogram;
        final public float gram;

        private Result(float pound, float kilogram, float gram) {
            this.pound = pound;
            this.kilogram = kilogram;
            this.gram = gram;
        }
    }

    private ArrayList<WeightConverter.Result> conversionsLog = new ArrayList<>();

    public WeightConverter.Result convertFromKilogram(float valueK) {
        float valueP = valueK * 2.20462f;
        float valueG = valueK * 1000;

        WeightConverter.Result res = new WeightConverter.Result(valueP, valueK, valueG);
        conversionsLog.add(res);
        return res;
    }

    public WeightConverter.Result convertFromPound(float valueP) {
        float valueK = valueP * 0.453592f;
        float valueG = valueP * 453.592f;

        WeightConverter.Result res = new WeightConverter.Result(valueP, valueK, valueG);
        conversionsLog.add(res);
        return res;
    }

    public WeightConverter.Result convertFromGram(float valueG) {
        float valueK = valueG * 0.001f;
        float valueP = valueG * 0.00220462f;

        WeightConverter.Result res = new WeightConverter.Result(valueP, valueK, valueG);
        conversionsLog.add(res);
        return res;
    }
}

