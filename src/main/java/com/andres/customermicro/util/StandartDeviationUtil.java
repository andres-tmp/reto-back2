package com.andres.customermicro.util;

public class StandartDeviationUtil {

    static double variance(double[] arr, int n) {

        double sum = 0;

        for (int i = 0; i < n; i++)
            sum += arr[i];

        double mean = (double) sum / (double) n;

        double sqDiff = 0;
        for (int i = 0; i < n; i++)
            sqDiff += (arr[i] - mean) * (arr[i] - mean);

        return sqDiff / n;
    }

    public static double standardDeviation(double[] arr) {
        return Math.sqrt(variance(arr, arr.length));
    }


}
