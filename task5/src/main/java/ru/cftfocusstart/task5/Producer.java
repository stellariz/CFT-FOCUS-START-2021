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
            Thread.sleep(producerTime);
            Resource resource = new Resource();
            log.info("Producer with id={} produced resource with id={}", id, resource.getId());
            storage.put(resource);
            log.info("Added element with id={}. Current size queue is: {}", resource.getId(), storage.getSize());
        } catch (InterruptedException ignored) {
        }
    }
}