package org.example;

/**
 * how to create a Singleton pattern in Java
 */
public class SingletonPattern {
    /**
     * MAke all the attributes as Private.
      */
    private static SingletonPattern instance;
    SingletonPattern() {}
    public static SingletonPattern getInstance() {
        if(instance == null) {
            instance = new SingletonPattern();
        }
        return instance;
    }
}
/**
 *  Above class is not Thread safe, as if 2 threads were to access this class for creating an instance, in multithreading
 *  Environment.
 *  2 thread will simultaneously access the if(insta === null) condition and think that no instance is created, and
 *  both of them create a instance each, and we have duplicate isntance created. so to avoid that we can have
 *  below synchronied method lvl.

 */

class ThreadSafeSingleton{

    private static ThreadSafeSingleton instance;
    ThreadSafeSingleton(){}
    public static synchronized ThreadSafeSingleton getInstance(){
        if(instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }
}