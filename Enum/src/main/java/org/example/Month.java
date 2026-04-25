package org.example;

import java.util.EnumSet;
import java.util.Enumeration;

public enum Month {
    JANUARY(31),
    FEBRUARY(28),
    MARCH(31),
    APRIL(30),
    MAY(31),
    JUNE(30),
    JULY(31),
    AUGUST(30),
    SEPTEMBER(31),
    OCTOBER(30),
    NOVEMBER(31),
    DECEMBER(30);


    private final int totalDays;

    Month(int totalDays){
        this.totalDays = totalDays;
    }
    public int getTotalDays(){
        return this.totalDays;
    }
    public int getTotalDaysUpTo(){
        int totalDaysUpto =0;
        for (Month m:Month.values()){
            totalDaysUpto+=m.getTotalDays();
            if(m.equals(this)){
                return totalDaysUpto;
            }
        }
        return totalDaysUpto;

    }
}

class TestMonthEnum{
    public static void main(String[] args)  {
        System.out.println("JANUARY  → " + Month.JANUARY.getTotalDaysUpTo());    // 31
        System.out.println("FEBRUARY → " + Month.FEBRUARY.getTotalDaysUpTo());   // 59
        System.out.println("MARCH    → " + Month.MARCH.getTotalDaysUpTo());      // 90
        System.out.println("JUNE     → " + Month.JUNE.getTotalDaysUpTo());       // 181
        System.out.println("DECEMBER → " + Month.DECEMBER.getTotalDaysUpTo());   // 365

    // ✅ Explicit assertion
    int march = Month.MARCH.getTotalDaysUpTo();
        if (march != 90)
            throw new AssertionError("Expected 90 but got " + march);

        System.out.println("All assertions passed ✅");
    }
}