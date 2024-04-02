package com.maxi.helper;

public class Formatters {
    
    public static double getFormattedAmount(String amount){
        double price = Double.parseDouble(amount.substring(1));
        return price;
    }
}
