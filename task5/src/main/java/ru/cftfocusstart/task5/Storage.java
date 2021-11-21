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
        log.info("Thread puts element with id={} in queue", resource.getId());
        queue[size++] = resource;
        notifyAll();
    }

    synchronized public Resource remove() throws InterruptedException {
        while (isEmpty()) {
            log.info("Thread's is sleeping");
            wait();
        }
        log.info("Thread gets element with id={} from queue", queue[size - 1].getId());
        Resource resource = queue[--size];
        notifyAll();
        return resource;
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
