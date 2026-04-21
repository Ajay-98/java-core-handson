package org.example;

public class DeamonThread2 {
    public static void main(String[] args) throws InterruptedException {
        Thread DeamonParentThread = new Thread(
                ()->{
                    Thread childThread = new Thread(
                            ()->{
                                System.out.println("Im a ChildThread");
                            }
                    );
                    childThread.start();
                    System.out.println("am i a DeamonThread ? - " + (childThread.isDaemon() ? "Yes" : "No"));
                }
        );

        DeamonParentThread.setDaemon(true);
        DeamonParentThread.start();

        DeamonParentThread.join();
    }
}
