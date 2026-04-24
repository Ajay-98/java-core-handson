package Syncronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static Syncronization.CounterSynchronized.incrementUnSafe;

public class StaticSync {

    private static int counter = 0;

    // Version 1 - Unsafe static increment
    public static void incrementUnsafe() {
        counter++;
    }

    // Version 2 - Safe static increment
    public static synchronized void incrementSafe() {
        counter++;
    }

    public static void main(String[] args) throws InterruptedException {
        // Test both versions with 2 threads x 1000 increments
        // Print final counter for each

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(
                ()-> { try { for (int i = 0; i < 1001; i++) { incrementSafe(); } }
               finally { System.out.println("Counter is " + counter); } }
        );
        executorService.submit(
                ()-> { try { for (int i = 0; i < 1001; i++) { incrementSafe(); } }
                    finally { System.out.println("Counter is " + counter); } }
        );
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);

        ExecutorService executorService2 = Executors.newFixedThreadPool(2);
        executorService2.submit(
                ()-> { try { for ( int i=0;i<1001;i++) { incrementUnSafe(); } }
                    finally { System.out.println("Safe counter is " + counter); } }
        );
        executorService2.submit(
                ()-> { try { for ( int i=0;i<1001;i++) { incrementUnSafe(); }}
                finally {System.out.println(" Safe Counter is " + counter);} }
        );
        executorService2.shutdown();
        executorService2.awaitTermination(5, TimeUnit.SECONDS);

    }
}
