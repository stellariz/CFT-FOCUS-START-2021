package ru.cftfocusstart.task1;

public final class ArgumentsChecker {

    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 32;

    public static void checkSizeOfTableAndCell(int sizeOfTable) throws IllegalArgumentException {
        if (sizeOfTable < MIN_SIZE || sizeOfTable > MAX_SIZE){
            throw new IllegalArgumentException("Incorrect size of table. Please re-enter.");
        }
    }
}
