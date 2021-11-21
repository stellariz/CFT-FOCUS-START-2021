package ru.cftfocusstart.task5;

public class Resource {
    private final int id;
    volatile private static int resourceCounter;
    private static final Object lock = new Object();

    public Resource() {
        synchronized (lock) {
            this.id = resourceCounter++;
        }
    }

    public int getId() {
        return id;
    }
}
