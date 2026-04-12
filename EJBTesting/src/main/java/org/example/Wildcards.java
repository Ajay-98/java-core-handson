package org.example;

import java.util.List;

public class Wildcards {


    public static void printall(List<?> toPrint){
        for (Object o : toPrint) {
            System.out.println(o);
        }
    }
    public static void test(String[] args) {

        printall(List.of("a","b","c"));
        printall(List.of(1,2,3,4,5));
        printall(List.of(0.34,0.4,0.6,0.9,1.34314));
        printall(List.of(13L, 45L, 67L));
    }
}
