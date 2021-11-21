package ru.cftfocusstart.task5;

public class Resource {
    private int id;
    volatile private static int resourceCounter;

    public Resource() {
        generateId();
    }

    private synchronized void generateId() {
        id = resourceCounter++;
    }

    public int getId() {
        return id;
    }
}
