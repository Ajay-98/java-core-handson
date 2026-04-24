package Syncronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CounterSynchronized {
    private static int counter = 0;
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException{

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(
                () ->
                {
                    incrementUnSafe();
                    System.out.println("Counter is " + counter);
                }
        );
        executor.submit(
                ()->{
                incrementUnSafe();
        System.out.println("Counter is " + counter);
                }
        );
    }

    public static void incrementUnSafe(){
        counter++;
    }

    public synchronized void incrementSafe(){
        counter++;
    }

    public void incrementSafeBlock(){
        /**
         * below line is not sourrouned by sync block or sync method
         * so any of the threads can use this print statement
         */
        System.out.println("Counter value is " + counter);

        synchronized (lock){
            counter++;
        }
    }


}
