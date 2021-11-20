package ru.cftfocusstart.task5;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Producer implements Runnable {
    private int producerTime = 500;
    private final int id;
    private final Storage storage;
    volatile private static int resourceCounter;

    public Producer(int id, Storage storage) {
        this.id = id;
        this.storage = storage;
    }

    @Override
    public void run() {
        while (true) {
            produce();
        }
    }

    synchronized private void produce() {
        try {
            Thread.sleep(producerTime);
            log.info("Producer with id={} produced resource with id={}", id, resourceCounter++);
            storage.put(new Resource(resourceCounter));
        } catch (InterruptedException ignored) {
        }
    }
}
