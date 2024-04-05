package com.maxi.helpers;

import java.util.List;

public class Calculation {

    public static double totalPriceCart(List<Double> listPrices) {

        double total = 0;

        for (int i = 0; i < listPrices.size(); i++) {
            double price = listPrices.get(i);
            total = total + price;
        }

        return total;
    }
}
