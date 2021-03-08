package com.example.convertisseur.models;

import java.util.ArrayList;

public class LengthConverter {

        public static class Result {
            final public float miles;
            final public float kilometer;
            final public float nautical_miles;

            private Result(float miles, float kilometer, float nautical_miles) {
                this.miles = miles;
                this.kilometer = kilometer;
                this.nautical_miles = nautical_miles;
            }
        }

        private ArrayList<LengthConverter.Result> conversionsLog = new ArrayList<>();

        public LengthConverter.Result convertFromMiles(float valueK) {
            float valueP = valueK * 0.868976f;
            float valueG = valueK * 1.60934f;

            LengthConverter.Result res = new LengthConverter.Result(valueK, valueP, valueG);
            conversionsLog.add(res);
            return res;
        }

        public LengthConverter.Result convertFromKilometer(float valueP) {
            float valueK = valueP * 0.621371f;
            float valueG = valueP * 0.539957f;

            LengthConverter.Result res = new LengthConverter.Result(valueK, valueP, valueG);
            conversionsLog.add(res);
            return res;
        }

        public LengthConverter.Result convertFromNauticalMiles(float valueG) {
            float valueK = valueG * 1.15078f;
            float valueP = valueG * 1.852f;

            LengthConverter.Result res = new LengthConverter.Result(valueK, valueP, valueG);
            conversionsLog.add(res);
            return res;
        }
}
