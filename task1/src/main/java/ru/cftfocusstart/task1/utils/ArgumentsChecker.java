package ru.cftfocusstart.task1.utils;

public final class ArgumentsChecker {

    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 32;

    public static void checkTableSize(int tableSize) throws IllegalArgumentException {
        if (tableSize < MIN_SIZE || tableSize > MAX_SIZE){
            throw new IllegalArgumentException("Incorrect size of table. Please re-enter.");
        }
    }
}
