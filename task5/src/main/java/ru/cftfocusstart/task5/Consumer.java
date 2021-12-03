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
            storage.remove();
            log.info("Thread with id = {} consumed element from queue", id);
            Thread.sleep(consumerTime);
        } catch (InterruptedException e) {
            log.info("Thread was interrupted");
            Thread.currentThread().interrupt();
        }
    }
}
