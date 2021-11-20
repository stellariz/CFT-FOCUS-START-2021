package ru.cftfocusstart.task5;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Storage {
    private final int capacity;
    volatile private int size;
    private final Resource[] queue;

    public Storage(int capacity) {
        this.capacity = capacity;
        queue = new Resource[capacity];
    }

    synchronized public void put(Resource resource) throws InterruptedException {
        while (isFull()) {
            log.info("Thread's sleeping");
            wait();
        }
        log.info("Thread wakes up");
        queue[size++] = resource;
        log.info("Added element. Current size queue is: {}", size);
        notifyAll();
    }

    synchronized public Resource remove() throws InterruptedException {
        while (isEmpty()) {
            log.info("Thread's is sleeping");
            wait();
        }
        log.info("Thread wakes up");
        Resource resource = queue[--size];
        log.info("Removed element. Current size queue is: {}", size);
        notifyAll();
        return resource;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
