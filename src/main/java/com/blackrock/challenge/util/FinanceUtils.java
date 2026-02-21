package com.blackrock.challenge.util;

public class FinanceUtils {

    private FinanceUtils() {
        
    }

    
    //   Rounds the given amount up to the next multiple of 100.
    //   Example: 250 -> 300, 375 -> 400
    
    public static double roundUpToNextHundred(double amount) {
        return Math.ceil(amount / 100.0) * 100;
    }

    
    //  Calculates the remanent amount to be invested.
    //   Remanent = ceiling - original amount
     
    public static double calculateRemanent(double amount) {
        double ceiling = roundUpToNextHundred(amount);
        return ceiling - amount;
    }
}
