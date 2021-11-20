package ru.cftfocusstart.task5;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Consumer implements Runnable {
    private final int id;
    private int consumerTime = 500;
    private final Storage storage;

    public Consumer(int id, Storage storage) {
        this.id = id;
        this.storage = storage;
    }

    @Override
    public void run() {
        while (true) {
            consume();
        }
    }

    private void consume() {
        try {
            Resource res = storage.remove();
            Thread.sleep(consumerTime);
            log.info("Consumer with id={} consumed resource with id={}", id, res.getId());
        } catch (InterruptedException ignored) {
        }
    }
}
