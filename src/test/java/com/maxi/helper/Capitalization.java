package com.maxi.helper;

public class Capitalization {
    
    public static String firstCharUpperCase(String text){
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }
}
