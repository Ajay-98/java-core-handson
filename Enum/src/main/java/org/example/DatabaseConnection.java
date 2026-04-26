package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public enum DatabaseConnection {

    INSTANCE;
    public enum Status{
        ACTIVE,INACTIVE,TERMINATED,IDLE;
    }

    private Status currentStatus = Status.IDLE;
    private final AtomicInteger counter = new AtomicInteger(0);

    public void connect() {

        if(currentStatus == Status.TERMINATED)
        {
            throw new IllegalStateException("Connection is Terminated");
        }
        currentStatus = Status.ACTIVE;
        counter.incrementAndGet();
        System.out.println("Connected to Database : " + counter.get()) ;
    }

    public void disconnect() {
        if(currentStatus == Status.TERMINATED)
        {
            throw new IllegalStateException("Connection is Already Terminated");
        }
        currentStatus = Status.TERMINATED;
        counter.decrementAndGet();
        System.out.println("Disconnected from Database : " + counter.get()) ;
    }
    public Status getCurrentStatus() {
        return currentStatus;
    }

    public AtomicInteger getCounter() {
        return counter;
    }

    public void reserCounter()
    {
        counter.set(0);
    }

}

class TestDatabaseConnection {
    public static void main(String[] args) throws InterruptedException {

        // ✅ Basic usage — no getInstance() needed!
        DatabaseConnection db = DatabaseConnection.INSTANCE;
        db.connect();
        System.out.println("Status: " + db.getCurrentStatus());  // ACTIVE
        db.disconnect();
        System.out.println("Status: " + db.getCurrentStatus());  // IDLE

        db.reserCounter();

        // ✅ Thread safety test — 100 threads, all get SAME instance
        System.out.println("\n=== Thread Safety Test ===");

        DatabaseConnection[] results = new DatabaseConnection[100];

        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            final int idx = i;
            threads[idx] = new Thread(() -> {
                results[idx] = DatabaseConnection.INSTANCE;
            });
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        // ✅ Verify all 100 threads got the same instance
        boolean allSame = true;
        for (DatabaseConnection result : results) {
            if (result != DatabaseConnection.INSTANCE) {
                allSame = false;
                break;
            }
        }

        System.out.println("All 100 threads got same instance: " + allSame);
        // → true ✅
    }
}
