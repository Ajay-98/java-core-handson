package Syncronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadSynchronization {

    private static int counter = 0;
    private static Object lock = new Object();
    public  static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(2);
            executor.submit(
                    () -> {
                        int i=0;
                        while(i<1001) {
                            synchronized (lock) {
                                counter++;
                                System.out.println("Counter Value for Thread 1 :" + counter);
                            }
                            i++;
                        }
                    }
            );

            executor.submit(
                    () -> {
                        int i = 0;
                        while (i < 1001) {
                            synchronized (lock) {
                                counter++;
                                System.out.println("Counter Value for Thread 2 :" + counter);
                            }
                            i++;
                        }
                    });


        executor.shutdown();
        System.out.println("Program is getting terminated as all threads are met " + executor.awaitTermination(5, TimeUnit.SECONDS));
    }
}
