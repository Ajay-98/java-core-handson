package org.example;

public class DeamonThreads {

    public static void main(String[] args) {
        executeDeamonThreads();
    }

    private static void executeDeamonThreads() {
        Thread thread1 = new Thread(() -> {
                System.out.println("thread 1");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
                System.out.println("thread 2");


        });
        thread1.setDaemon(true);
        thread1.start();
        thread2.start();

    }
}
