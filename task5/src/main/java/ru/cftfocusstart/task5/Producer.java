package ru.cftfocusstart.task5;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@AllArgsConstructor
public class Producer implements Runnable {
    private final int id;
    private final Storage storage;
    private final int producerTime;

    @Override
    public void run() {
        while (true) {
            produce();
        }
    }

    private void produce() {
        try {
            storage.put(new Resource());
            log.info("Thread with id = {} produced element and put it in queue", id);
            Thread.sleep(producerTime);
        } catch (InterruptedException e) {
            throw new IllegalArgumentException("Thread was interrupted" + e.getMessage(), e);
        }
    }
}