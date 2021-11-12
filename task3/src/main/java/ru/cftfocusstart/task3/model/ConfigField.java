package ru.cftfocusstart.task3.model;

public class ConfigField {
    private static int length;
    private static int width;
    private static int totalBombs;

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

    public static int getTotalBombs() {
        if (totalBombs == 0) {
            throw new IllegalArgumentException("Number of bombs wasn't be initalized");
        }
        return totalBombs;
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

    public static void setTotalBombs(int totalBombs) {
        if (!isCorrectBombsNumber(totalBombs)) {
            throw new IllegalArgumentException("Number of bombs is incorrect with number of columns and rows");
        }
        ConfigField.totalBombs = totalBombs;
    }

    private static boolean isCorrectBombsNumber(int totalBombs) {
        return totalBombs <= getLength() * getWidth() && totalBombs > 0;
    }
}
