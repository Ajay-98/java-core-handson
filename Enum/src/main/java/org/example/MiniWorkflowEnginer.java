package org.example;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *Build a complete Workflow Engine using Enums.
 *
 * Create enum `TaskStatus`:
 *   PENDING, RUNNING, PAUSED, COMPLETED, FAILED, RETRYING
 *
 * Create enum `TaskPriority`:
 *   LOW, MEDIUM, HIGH, CRITICAL
 *
 * Create enum `WorkflowEngine` (Singleton):
 *   - maintains a PriorityQueue of tasks
 *   - processes CRITICAL first, then HIGH, etc.
 *   - failed tasks auto-retry up to 3 times (RETRYING state)
 *   - after 3 failures → FAILED permanently
 *   - supports pause() and resume() per task
 *   - tracks metrics: completed, failed, retried counts
 *
 * Run with 20 concurrent tasks of mixed priorities.
 * Print final metrics report.
 * 	`x₹
 */
public class MiniWorkflowEnginer {
    private static final Object priority_queue_lock = new Object();
    public static void main(String[] args) {
        WorkflowEngine engine = WorkflowEngine.INSTANCE;
        PriorityQueue<Task> t = engine.getTasks();
        t.add(new Task(1, TaskStatus.PENDING,TaskPriority.CRITICAL));
        t.add(new Task(2, TaskStatus.FAILED,TaskPriority.HIGH));
        t.add(new Task(3, TaskStatus.RUNNING,TaskPriority.MEDIUM));
        t.add(new Task(4, TaskStatus.RETYING,TaskPriority.CRITICAL));
        t.add(new Task(5, TaskStatus.PAUSED,TaskPriority.CRITICAL));
        t.add(new Task(6, TaskStatus.PAUSED,TaskPriority.LOW));
        t.add(new Task(7, TaskStatus.FAILED,TaskPriority.LOW));
        t.add(new Task(8, TaskStatus.RUNNING,TaskPriority.LOW));
        t.add(new Task(9, TaskStatus.RUNNING,TaskPriority.LOW));
        t.add(new Task(10, TaskStatus.COMPLETED,TaskPriority.MEDIUM));
        t.add(new Task(11, TaskStatus.PENDING,TaskPriority.MEDIUM));
        t.add(new Task(12, TaskStatus.FAILED,TaskPriority.LOW));
        t.add(new Task(13, TaskStatus.FAILED,TaskPriority.HIGH));
        t.add(new Task(14, TaskStatus.PAUSED,TaskPriority.HIGH));
        t.add(new Task(15, TaskStatus.PENDING,TaskPriority.CRITICAL));
        t.add(new Task(16, TaskStatus.PAUSED,TaskPriority.MEDIUM));
        for (int thread_itr =0; thread_itr<=20; thread_itr++) {
            Thread thread = new Thread(
                    ()->{
                        synchronized (priority_queue_lock) {
                            Task t1 = t.remove();
                            AtomicInteger retrycount = new AtomicInteger(0);

                            if (t1.getStatus() == TaskStatus.FAILED) {
                                t1.setStatus(TaskStatus.RETYING);
                                while (retrycount.get() <= 3) {
                                    System.out.println("[*] Retrying the Queue Message of ID" + t1.getId() + " With Priority " + t1.getPriority());
                                    retrycount.getAndIncrement();
                                    try {
                                        Thread.sleep(3000);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                t1.setStatus(TaskStatus.FAILED);
                                System.out.println("[*] Setting the ID " + t1.getId() + " to FAILED");
                            }
                            if(t1.getStatus() == TaskStatus.PAUSED){
                                System.out.println("[*] The ID " + t1.getId() + " Resumed now");
                                t.add(resume(t1));
                            } else if (t1.getStatus() == TaskStatus.RUNNING) {

                                System.out.println("[*] The ID " + t1.getId() + " is Running, waiting for it to Finish");
                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                t1.setStatus(TaskStatus.COMPLETED);
                                t.add(t1);
                            }
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            retrycount.set(0);
                        }

                    }
            );
            thread.start();
        }

        t.forEach(System.out::println);



    }
    private static Task pause(Task t) {
        t.setStatus(TaskStatus.PAUSED);
        return t;
    }

    private static Task resume(Task t) {
        t.setStatus(TaskStatus.RUNNING);
        return t;
    }

}

enum TaskStatus{ PENDING, RUNNING, PAUSED, COMPLETED, FAILED, RETYING; }

enum TaskPriority{LOW(4), MEDIUM(3), HIGH(2), CRITICAL(1);

    private int priority;
    TaskPriority(int priority) {
        this.priority = priority;
    }
    public int getPriority() {
        return priority;
    }
}

enum WorkflowEngine{
    INSTANCE;

    private PriorityQueue<Task> tasks = new PriorityQueue<>(
            Comparator.comparing(Task::getPriority)
    );

    public PriorityQueue<Task> getTasks() {
        return tasks;
    }

    public void setTasks(PriorityQueue<Task> tasks) {
        this.tasks = tasks;
    }
}

class Task{
    private int id;
    private TaskStatus status;
    private TaskPriority priority;

    public Task(int id, TaskStatus status, TaskPriority priority) {
        this.id = id;
        this.status = status;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }
}