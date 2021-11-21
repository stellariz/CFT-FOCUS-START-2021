package ru.cftfocusstart.task5;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
            throw new IllegalArgumentException("Incorrect properties file");
        }
    }

    public static void main(String[] args) {
        Storage storage = new Storage(storageSize);
        for (int i = 0; i < producerCount; ++i) {
            new Thread(new Producer(i, storage, producerTime)).start();
        }
        for (int i = 0; i < consumerCount; ++i) {
            new Thread(new Consumer(i, storage, consumerTime)).start();
        }
    }
}
