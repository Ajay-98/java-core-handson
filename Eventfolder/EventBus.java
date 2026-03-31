package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/***
 * Create an EventBus that maps event types (Class<T>) to a chain of Consumer<T> handlers.
 * Support subscribe() and publish(). Multiple subscribers for the same event should all fire in registration order.
 * Use method chaining via Consumer.andThen().
 */
public class EventBus<T> {

    Map<Class<T>, Consumer<T>> handlers = new HashMap<>();


    public void subscribe(Class type, Consumer handler)
    {
        handlers.merge(
                type,
                handler,
                (existing, next) -> ((Consumer<T>)existing ).andThen((Consumer<T>)next )
        );
    }

    public void publish(T event)
    {
        // this event thats needs to be publish, is being handled here.
        // i need to fetch exsiting handlers from the Map.
        Consumer handler = handlers.get(event.getClass());

        if (handler != null)
        {
            handler.accept(event);
        }
    }
}

