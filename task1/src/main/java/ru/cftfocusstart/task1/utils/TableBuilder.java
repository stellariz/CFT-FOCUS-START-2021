package ru.cftfocusstart.task1.utils;

public final class TableBuilder {
    private static final int MIN_TABLE_SIZE = 1;
    private static final int MAX_TABLE_SIZE = 32;

    private static final String VERTICAL_SEPARATOR = "|";
    private static final String HORIZONTAL_SEPARATOR = "-";
    private static final String INTERSECTION = "+";
    private static final String WHITESPACE = " ";

    private final String line;

    private final int cellSize;
    private final int tableSize;


    public TableBuilder(int tableSize) {
        checkTableSize(tableSize);
        this.tableSize = tableSize;
        this.cellSize = countCellSize(tableSize);
        line = buildLine();
    }


    private int countSpacesForCell(int i) {
        return cellSize - Integer.toString(i).length();
    }


    private String buildHead() {
        StringBuilder stringBuilder = new StringBuilder(WHITESPACE.repeat(getFirsColumnSize()));
        for (int i = 1; i <= tableSize; ++i) {
            stringBuilder.append(VERTICAL_SEPARATOR).
                    append(WHITESPACE.repeat(countSpacesForCell(i))).
                    append(i);
        }
        return stringBuilder.append(System.lineSeparator()).toString();
    }


    private String buildLine() {
        String lineForTheFirstColumn = HORIZONTAL_SEPARATOR.repeat(getFirsColumnSize());
        return lineForTheFirstColumn + (INTERSECTION + HORIZONTAL_SEPARATOR.repeat(cellSize)).repeat(tableSize) +
                System.lineSeparator();
    }


    private String buildRow(int coefficient) {
        int numSpacesInFirstCol = getFirsColumnSize() - Integer.toString(coefficient).length();
        StringBuilder stringBuilder = new StringBuilder(WHITESPACE.repeat(numSpacesInFirstCol)).append(coefficient);
        for (int i = 1; i <= tableSize; ++i) {
            stringBuilder.append(VERTICAL_SEPARATOR).
                    append(WHITESPACE.repeat(countSpacesForCell(i * coefficient))).
                    append(i * coefficient);
        }
        return stringBuilder.append(System.lineSeparator()).toString();
    }


    public String buildTable() {
        int coefficient = 1;
        StringBuilder stringBuilder = new StringBuilder(buildHead());
        for (int i = 0; i < tableSize; ++i) {
            stringBuilder.append(line).
                    append(buildRow(coefficient++));
        }
        return stringBuilder.append(line).toString();
    }


    private int countCellSize(int tableSize) {
        int maxNumber = tableSize * tableSize;
        return Integer.toString(maxNumber).length();
    }


    private int getFirsColumnSize() {
        return Integer.toString(tableSize).length();
    }


    private void checkTableSize(int tableSize) throws IllegalArgumentException {
        if (tableSize < MIN_TABLE_SIZE || tableSize > MAX_TABLE_SIZE) {
            throw new IllegalArgumentException("Incorrect size of table. Please re-enter.");
        }
    }
}
