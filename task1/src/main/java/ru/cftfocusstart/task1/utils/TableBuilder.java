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
    private final int tableDimension;


    public TableBuilder(int tableDimension) {
        checkTableDimension(tableDimension);
        this.tableDimension = tableDimension;
        this.cellSize = countCellSize(tableDimension);
        this.line = buildLine();
    }


    private int countSpacesForCell(int i) {
        return cellSize - Integer.toString(i).length();
    }


    private String buildHead() {
        StringBuilder stringBuilder = new StringBuilder(getTableLength());
        stringBuilder.append(WHITESPACE.repeat(getFirstColumnSize()));
        for (int i = 1; i <= tableDimension; ++i) {
            stringBuilder.append(VERTICAL_SEPARATOR).
                    append(WHITESPACE.repeat(countSpacesForCell(i))).
                    append(i);
        }
        return stringBuilder.append(System.lineSeparator()).toString();
    }


    private String buildLine() {
        String lineForTheFirstColumn = HORIZONTAL_SEPARATOR.repeat(getFirstColumnSize());
        return lineForTheFirstColumn + (INTERSECTION + HORIZONTAL_SEPARATOR.repeat(cellSize)).
                repeat(tableDimension) + System.lineSeparator();
    }


    private String buildRow(int coefficient) {
        int numSpacesInFirstCol = getFirstColumnSize() - Integer.toString(coefficient).length();
        StringBuilder stringBuilder = new StringBuilder(getTableLength());
        stringBuilder.append(WHITESPACE.repeat(numSpacesInFirstCol)).
                append(coefficient);
        for (int i = 1; i <= tableDimension; ++i) {
            stringBuilder.append(VERTICAL_SEPARATOR).
                    append(WHITESPACE.repeat(countSpacesForCell(i * coefficient))).
                    append(i * coefficient);
        }
        return stringBuilder.append(System.lineSeparator()).toString();
    }


    public String buildTable() {
        int coefficient = 1;
        StringBuilder stringBuilder = new StringBuilder(getTableSize());
        stringBuilder.append(buildHead());
        for (int i = 0; i < tableDimension; ++i) {
            stringBuilder.append(line).
                    append(buildRow(coefficient++));
        }
        return stringBuilder.append(line).toString();
    }


    private int countCellSize(int tableSize) {
        int maxNumber = tableSize * tableSize;
        return Integer.toString(maxNumber).length();
    }


    private int getFirstColumnSize() {
        return Integer.toString(tableDimension).length();
    }


    private int getTableLength() {
        return getFirstColumnSize() + (cellSize + 1) * tableDimension + 1;
    }


    private int getTableSize() {
        return (tableDimension + 1) * (getTableLength() + line.length());
    }


    private void checkTableDimension(int tableDimension) {
        if (tableDimension < MIN_TABLE_SIZE || tableDimension > MAX_TABLE_SIZE) {
            throw new IllegalArgumentException("Incorrect size of table. Please re-enter.");
        }
    }
}
