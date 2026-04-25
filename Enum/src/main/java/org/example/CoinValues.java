package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *Create an enum `Coin` with:
 *   PENNY, NICKEL, DIME, QUARTER
 *
 * Each coin has a value in cents.
 *
 * Write a method:
 *   totalValue(Coin[] coins)
 *   → returns total value in cents
 *
 * Example:
 *   totalValue([PENNY, QUARTER, DIME]) → 36 cents
 */
public enum CoinValues {

    PENNY(0.10),
    NICKEL(0.45),
    DIME(0.50),
    QUARTER(0.75);

    private final double value;

    public double getValue() {
        return this.value;
    }
    CoinValues(double value) {
        this.value = value;
    }

    public static double totalValue(CoinValues[] coinValues) {
        return
                Arrays.stream(coinValues).mapToDouble(CoinValues::getValue).sum();
    }

}

class TestCoinValues {
    public static void main(String[] args) {
        CoinValues[] coinValues = List.of(CoinValues.DIME, CoinValues.QUARTER, CoinValues.DIME).toArray(CoinValues[]::new);
        System.out.println("Total Coin Value is " + CoinValues.totalValue(coinValues));
    }
}
