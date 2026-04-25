package org.example;

import java.util.TreeMap;

public enum TafficeLight {
    RED("Stop"),
    GREEN("Go"),
    YELLOW("Wait Now");

    private final String msg;
    
    TafficeLight(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

}

class testtrafficelight {
    public static void main(String[] args) {
        TafficeLight test1 = TafficeLight.GREEN;
        TafficeLight test2 = TafficeLight.YELLOW;
        TafficeLight test3 = TafficeLight.RED;

        System.out.println(" Whats the Message of GREEN " + test1.getMsg());
        System.out.println(" Whats the Message of YELLOW " + test2.getMsg());
        System.out.println(" Whats the Message of RED " + test3.getMsg());
    }
}
