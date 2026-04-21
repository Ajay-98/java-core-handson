package org.example;

import java.util.concurrent.*;

public class DeamonThread4 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

       ExecutorService executor = Executors.newFixedThreadPool(3,new CustomDeamonThread());

       for ( int i = 0; i < 3; i++ ) {
           executor.submit(
                   ()-> System.out.println("Is this Deamon Thread : " + (Thread.currentThread().isDaemon() ? "Yes" : "No"))
           );
       }
       executor.shutdown();
       executor.awaitTermination(5000, TimeUnit.MICROSECONDS);

    }
}
