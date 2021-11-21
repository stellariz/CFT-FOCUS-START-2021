package ru.cftfocusstart.task5;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class Consumer implements Runnable {
    private final int id;
    private final Storage storage;
    private final int consumerTime;

    @Override
    public void run() {
        while (true) {
            consume();
        }
    }

    private void consume() {
        try {
            Resource resource = storage.remove();
            log.info("Removed element with id={}. Current size queue is: {}", resource.getId(), storage.getSize());
            Thread.sleep(consumerTime);
            log.info("Consumer with id={} consumed resource with id={}", id, resource.getId());
        } catch (InterruptedException ignored) {
        }
    }
}
