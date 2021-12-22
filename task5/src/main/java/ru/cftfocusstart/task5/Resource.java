package ru.cftfocusstart.task5;

public class Resource {
    private final int id;
    volatile private static int resourceCounter;

    public Resource() {
        synchronized (this) {
            this.id = resourceCounter++;
        }
    }

    public int getId() {
        return id;
    }
}
