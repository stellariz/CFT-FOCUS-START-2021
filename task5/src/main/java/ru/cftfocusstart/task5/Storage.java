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
            wait();
        }
        queue[size++] = resource;
        log.info("Element with id = {} added in queue. Current size queue is: {}", resource.getId(), getSize());
        notifyAll();
    }

    synchronized public void remove() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        Resource resource = queue[--size];
        log.info("Element with id = {} removed from queue. Current size queue is: {}", resource.getId(), getSize());
        notifyAll();
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }
}
