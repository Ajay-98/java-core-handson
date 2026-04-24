package Syncronization;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumer {

    private static final List<Integer> buffer = new LinkedList<>();
    private static final int MAX_SIZE = 3;
    private static final Object lock = new Object();

    // Producer — adds items to buffer
    public static void produce() throws InterruptedException {
        int item = 0;
        while (true) {
            synchronized (lock) {
                while (buffer.size() == MAX_SIZE) {
                    System.out.println("Buffer full! Producer waiting...");
                    lock.wait();  // releases lock and waits
                }
                buffer.add(item++);
                System.out.println("Produced: " + item + " | Buffer: " + buffer);
                lock.notify(); // wake up consumer
            }
            Thread.sleep(500);
        }
    }

    // Consumer — removes items from buffer
    public static void consume() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (buffer.isEmpty()) {
                    System.out.println("Buffer empty! Consumer waiting...");
                    lock.wait();  // releases lock and waits
                }
                int to_consume = buffer.removeFirst();
                System.out.println("Consumed: " + to_consume );
                lock.notify();
                // YOUR CODE HERE — remove item, print, notify
            }
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(
                ()-> {
                    try {
                        produce();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        executorService.submit(
                ()->
                {
                        try {
                            consume();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                }
        );


        // YOUR CODE HERE
        // Launch producer and consumer as separate threads
    }
}