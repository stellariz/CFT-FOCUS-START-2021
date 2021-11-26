package ru.cftfocusstart.task5;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class Application {
    private static final int consumerCount;
    private static final int producerCount;
    private static final int consumerTime;
    private static final int producerTime;
    private static final int storageSize;

    static {
        try (InputStream is = Application.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            consumerCount = Integer.parseInt(properties.getProperty("CONSUMERS_COUNT"));
            producerCount = Integer.parseInt(properties.getProperty("PRODUCERS_COUNT"));
            consumerTime = Integer.parseInt(properties.getProperty("CONSUMER_TIME"));
            producerTime = Integer.parseInt(properties.getProperty("PRODUCER_TIME"));
            storageSize = Integer.parseInt(properties.getProperty("STORAGE_SIZE"));
        } catch (IOException e) {
            throw new IllegalArgumentException("Incorrect properties file", e);
        }
    }

    public static void main(String[] args) {
        try {
            validateInputData();
            Storage storage = new Storage(storageSize);
            for (int i = 0; i < producerCount; ++i) {
                new Thread(new Producer(i, storage, producerTime)).start();
            }
            for (int i = 0; i < consumerCount; ++i) {
                new Thread(new Consumer(i, storage, consumerTime)).start();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private static void validateInputData() {
        if (consumerCount <= 0) {
            throw new IllegalArgumentException("Consumer count can't be less than or equal to zero");
        }
        if (producerCount <= 0) {
            throw new IllegalArgumentException("Producer count can't be less than or equal to zero");
        }
        if (consumerTime <= 0) {
            throw new IllegalArgumentException("Consumer time can't be less than or equal to zero");
        }
        if (producerTime <= 0) {
            throw new IllegalArgumentException("Producer time can't be less than or equal to zero");
        }
        if (storageSize <= 0) {
            throw new IllegalArgumentException("Storage size can't be less than or equal to zero");
        }
    }
}
