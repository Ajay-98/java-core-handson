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

    public String getMessage(TafficeLight getMessage) {
        return switch (getMessage)
        {
            case RED -> RED.msg;
            case GREEN -> GREEN.msg;
            case YELLOW -> YELLOW.msg;
        };
    }

}

class testtrafficelight {
    public static void main(String[] args) {
        TafficeLight test1 = TafficeLight.GREEN;
        TafficeLight test2 = TafficeLight.YELLOW;
        TafficeLight test3 = TafficeLight.RED;

        System.out.println(" Whats the Message of GREEN " + test1.getMessage(test1));
        System.out.println(" Whats the Message of YELLOW " + test2.getMsg());
        System.out.println(" Whats the Message of RED " + test3.getMsg());
    }
}
