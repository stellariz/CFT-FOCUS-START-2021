package ru.cftfocusstart.task5;

public enum PropertiesName {
    PRODUCERS_COUNT("producersCount"),
    CONSUMERS_COUNT("consumersCount"),
    PRODUCER_TIME("producerTime"),
    CONSUMER_TIME("consumerTime"),
    STORAGE_SIZE("storageSize");

    private final String propertyName;

    PropertiesName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
