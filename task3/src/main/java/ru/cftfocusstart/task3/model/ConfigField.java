package ru.cftfocusstart.task3.model;

public class ConfigField {
    private static int length;
    private static int width;

    private ConfigField() {
    }

    public static int getLength() {
        if (length == 0) {
            throw new IllegalArgumentException("Length wasn't be initialized");
        }
        return length;
    }

    public static int getWidth() {
        if (width == 0) {
            throw new IllegalArgumentException("Width wasn't be initialized");
        }
        return width;
    }

    public static void setSizeOfField(int width, int length) {
        if (width <= 0) {
            throw new IllegalArgumentException("Incorrect width of field");
        }
        if (length <= 0) {
            throw new IllegalArgumentException("Incorrect length of field");
        }
        ConfigField.width = width;
        ConfigField.length = length;
    }
}
