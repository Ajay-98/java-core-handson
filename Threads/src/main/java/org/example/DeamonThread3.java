package org.example;

public class DeamonThread3 {
    public static void main(String[] args) {

        Thread DeamonDCThread = new Thread(
            () -> {

                while(true) {
                System.out.println("[*] Running Garbage Collector");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        );
        DeamonDCThread.setDaemon(true);

        Thread UsrThread = new Thread(
                ()-> {

                        System.out.println("Doing some Work");
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                }
        );
        DeamonDCThread.start();
        UsrThread.start();

    }
}
