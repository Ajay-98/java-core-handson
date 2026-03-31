package org.example;

public class EventMain {


    public static void main(String[] args)
    {
        EventBus<String> bus = new EventBus<>();

        bus.subscribe(String.class, c-> System.out.println("Handlers 1 " + c));
        bus.subscribe(String.class, c-> System.out.println("Handlers 2 " + c));

        bus.publish("Hello");
    }
}
