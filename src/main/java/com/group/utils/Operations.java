package com.group.utils;

/**
 * Class
 *
 * @author Scoowy
 * @version 2022.01.16.2153
 */
public class Operations {
    public static double getPercentageFromValue(int total, int part) {
        return getPercentageFromValue((double) total, (double) part);
    }

    public static double getPercentageFromValue(double total, double part) {
        return (100.0 * part) / total;
    }
}
