package org.example;/*
2) We want to add a new function called "getBiggestChange" to the StockCollection class. This function calculates and returns the largest change in stock price between any two consecutive days in the price records of a stock along with the dates of the change in a list. For example, let's consider the following price records of a stock:

Price Records:
Price:  110         112         90          105
Date:   2023-06-29  2023-07-01  2023-06-25  2023-07-06

Stock price changes (sorted based on date):
Date:     2023-06-25  ->  2023-06-29  ->  2023-07-01 ->  2023-07-06
Price:        90      ->      110     ->     112     ->     105
Change:              +20             +2              -7

In this case, the biggest change in the stock price was +20, which occurred between 2023-06-25 and 2023-06-29. In this case, the function should return [20, "2023-06-25", "2023-06-29"]

Two days are considered consecutive if there are no other days' data in between them in the price records based on their dates.

To assist you in testing this new function, we have provided the testGetBiggestChange function.
*/

/*
We are developing a stock trading data management software that tracks the prices of different stocks over time and provides useful statistics.

The program includes three classes: `Stock`, `PriceRecord`, and `StockCollection`.

Classes:
* The `Stock` class represents data about a specific stock.
* The `PriceRecord` class holds information about a single price record for a stock.
* The `StockCollection` class manages a collection of price records for a particular stock and provides methods to retrieve useful statistics about the stock's prices.

To begin with, we present you with two tasks:
1-1) Read through and understand the code below. Please take as much time as necessary, and feel free to run the code.
1-2) The test for StockCollection is not passing due to a bug in the code. Make the necessary changes to StockCollection to fix the bug.
*/

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

class Stock {
    /** Data about a particular stock. */
    String symbol; // String, the symbol of the stock
    String name; // String, the name of the stock

    Stock(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Stock stock = (Stock) other;
        return symbol.equals(stock.symbol) && name.equals(stock.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(symbol, name);
    }
}

class PriceRecord {
    /** Data and methods about a single price record of a stock. */
    Stock stock; // Stock object representing the stock
    int price; // int, the price of the stock
    String date; // String, the date of the price record is of the format "YYYY-MM-DD"

    PriceRecord(Stock stock, int price, String date) {
        this.stock = stock;
        this.price = price;
        this.date = date;
    }
}

class StockCollection {
    /**
     * Data for a collection of price records for a particular stock, and methods for
     * getting useful statistics about the stock's prices.
     */
    ArrayList<PriceRecord> priceRecords = new ArrayList<>(); // list of PriceRecord objects, the price records for this particular stock
    Stock stock; // Stock, the Stock this StockCollection is for

    public void getBiggestChange(Object[][] stock_recrodsObjects)
    {
        Arrays.stream(stock_recrodsObjects).flatMap(Arrays::stream).forEach(System.out::println);

    }

    StockCollection(Stock stock) {
        this.stock = stock;
    }

    int getNumPriceRecords() {
        /** Returns the number of PriceRecords in this StockCollection */
        return priceRecords.size();
    }

    void addPriceRecord(PriceRecord priceRecord) {
        /** Adds a PriceRecord to this StockCollection. */
        if (!priceRecord.stock.equals(this.stock)) {
            throw new IllegalArgumentException("PriceRecord's Stock is not the same as the StockCollection's");
        }
        priceRecords.add(priceRecord);
    }

    int getMaxPrice() {
        /** Return the maximum price recorded in this StockCollection. */

        int max_price = -1;
        try
        {
            max_price = priceRecords.stream().mapToInt(record -> record.price).max().
                    getAsInt();
        }
        catch(NoSuchElementException e)
        {
            return max_price;
        }

        return max_price;
    }

    int getMinPrice() {
        /** Return the minimum price recorded in this StockCollection. */
        int min_price =-1;
        try {
            min_price =  priceRecords.stream().mapToInt(record -> record.price).min().getAsInt();
        }
        catch(NoSuchElementException e)
        {
            return min_price;
        }

        return min_price;

    }

    double getAvgPrice() {
        /** Return the average price recorded in this StockCollection. */
        double avg = -1.0;

        try{
            avg = priceRecords.stream().mapToInt(record -> record.price).average().getAsDouble();
        }
        catch(NoSuchElementException e)
        {
            return avg;
        }
        return avg;
        //return (double)total / priceRecords.size();
    }
}

public class Solution {

    public static void main(String[] args) {
        StockCollection s = new StockCollection(new Stock("AAPL", "Apple Inc."));
        Object[][] priceData = { { 110, "2023-06-29" }, { 112, "2023-07-01" }, { 90, "2023-06-28" },
                { 105, "2023-07-06" } };
        s.getBiggestChange(priceData);
       // testPriceRecord();
      //  testStockCollection();
      //  testGetBiggestChange();
    }

    public static void testPriceRecord() {
        // Test basic PriceRecord functionality
        System.out.println("Running testPriceRecord");
        Stock testStock = new Stock("AAPL", "Apple Inc.");
        PriceRecord testPriceRecord = new PriceRecord(testStock, 100, "2023-07-01");

        Assert.assertEquals(testPriceRecord.stock, testStock);
        Assert.assertEquals(testPriceRecord.price, 100);
        Assert.assertEquals(testPriceRecord.date, "2023-07-01");
    }

    private static StockCollection makeStockCollection(Stock stock, Object[][] priceData) {
        StockCollection stockCollection = new StockCollection(stock);
        for (Object[] priceRecordData : priceData) {
            PriceRecord priceRecord = new PriceRecord(stock, (int) priceRecordData[0], (String) priceRecordData[1]);
            stockCollection.addPriceRecord(priceRecord);
        }
        return stockCollection;
    }

    public static void testStockCollection() {
        System.out.println("Running testStockCollection");
        // Test basic StockCollection functionality
        Stock testStock = new Stock("AAPL", "Apple Inc.");
        StockCollection stockCollection = new StockCollection(testStock);

        Assert.assertEquals(0, stockCollection.getNumPriceRecords());
        Assert.assertEquals(-1, stockCollection.getMaxPrice());
        Assert.assertEquals(-1, stockCollection.getMinPrice());
        Assert.assertEquals(-1.0, stockCollection.getAvgPrice(), 0.001);

        /*
         * Price Records: Price: 110 112 90 105 Date: 2023-06-29 2023-07-01 2023-06-28
         * 2023-07-06
         */
        Object[][] priceData = { { 110, "2023-06-29" }, { 112, "2023-07-01" }, { 90, "2023-06-28" },
                { 105, "2023-07-06" } };
        testStock = new Stock("AAPL", "Apple Inc.");
        stockCollection = makeStockCollection(testStock, priceData);

        Assert.assertEquals(priceData.length, stockCollection.getNumPriceRecords());
        Assert.assertEquals(112, stockCollection.getMaxPrice());
        Assert.assertEquals(90, stockCollection.getMinPrice());
        Assert.assertEquals(104.25, stockCollection.getAvgPrice(), 0.1);
    }

    public static void testGetBiggestChange() {
        // Test the getBiggestChange method
        System.out.println("Running testGetBiggestChange");
        Stock testStock = new Stock("AAPL", "Apple Inc.");
        StockCollection stockCollection = new StockCollection(testStock);

        //Assert.assertNull(stockCollection.getBiggestChange());

        /*
         * Price Records: Price: 110 112 90 105 Date: 2023-06-29 2023-07-01 2023-06-25
         * 2023-07-06
         */
        Object[][] priceData = { { 110, "2023-06-29" }, { 112, "2023-07-01" }, { 90, "2023-06-25" },
                { 105, "2023-07-06" } };
        stockCollection = makeStockCollection(testStock, priceData);

      //  Assert.assertArrayEquals(new Object[] { 20, "2023-06-25", "2023-06-29" }, stockCollection.getBiggestChange());

        /*
         * Price Records: Price: 200 210 190 180 Date: 2000-01-04 1999-12-30 2000-01-03
         * 2000-01-01
         */
        Object[][] priceData2 = { { 200, "2000-01-04" }, { 210, "1999-12-30" }, { 190, "2000-01-03" },
                { 180, "2000-01-01" } };
        stockCollection = makeStockCollection(testStock, priceData2);

        //Assert.assertArrayEquals(new Object[] { -30, "1999-12-30", "2000-01-01" }, stockCollection.getBiggestChange());
    }
}