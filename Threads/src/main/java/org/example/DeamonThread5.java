package org.example;

import java.util.concurrent.atomic.AtomicBoolean;

public class DeamonThread5 {

    private static AtomicBoolean running = new AtomicBoolean(false);
    private static Thread DeamonMonitorThread = new Thread(
            ()-> {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("System Status is " + running.get());
                }
            }
    );



    public static void main(String[] args) {
        DeamonMonitorThread.setDaemon(true);
        DeamonMonitorThread.start();
        startUSerProcess(()->
        {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            running.set(true);
            System.out.println("System Status is " + running.get());
        });

    }

    private static void startUSerProcess(Runnable r) {
        Thread t = new Thread(r);
        t.start();
    }
}
