package ru.cftfocusstart.task1.utils;

public final class TableBuilder {
    private static final String PIPE = "|";
    private static final String MINUS = "-";
    private static final String PLUS = "+";
    private static final String WHITESPACE = " ";

    private final String line;

    private final int sizeOfCell;
    private final int sizeOfTable;
    private final int sizeOfFirstColumn;

    private int coefficient = 1;

    public TableBuilder(int sizeOfCell, int sizeOfTable) {
        this.sizeOfCell = sizeOfCell;
        this.sizeOfTable = sizeOfTable;
        this.sizeOfFirstColumn = sizeOfCell == 1 ? sizeOfCell : sizeOfCell - 1;
        line = buildLine();
    }


    private int countSpacesForCell(int i){
        return sizeOfCell - Integer.toString(i).length();
    }


    private String buildHead(){
        StringBuilder stringBuilder = new StringBuilder(WHITESPACE.repeat(sizeOfFirstColumn));
        for (int i = 1; i <= sizeOfTable; ++i){
            stringBuilder.append(PIPE).append(WHITESPACE.repeat(countSpacesForCell(i))).append(i);
        }
        return stringBuilder.toString() + '\n';
    }


    private String buildLine(){
        String lineForTheFirstColumn = MINUS.repeat(sizeOfFirstColumn);
        return lineForTheFirstColumn + (PLUS + MINUS.repeat(sizeOfCell)).repeat(sizeOfTable) + '\n';
    }


    private String buildRow(){
        int numSpacesInFirstCol = sizeOfFirstColumn - Integer.toString(coefficient).length();
        StringBuilder stringBuilder = new StringBuilder(WHITESPACE.repeat(numSpacesInFirstCol)).append(coefficient);
        for (int i = 1; i <= sizeOfTable; ++i){
            stringBuilder.append(PIPE).append(WHITESPACE.repeat(countSpacesForCell(i*coefficient))).append(i*coefficient);
        }
        ++coefficient;
        return stringBuilder.toString() + '\n';
    }


    public String buildTable(){
        StringBuilder stringBuilder = new StringBuilder(buildHead());
        for (int i = 0; i < sizeOfTable; ++i){
            stringBuilder.append(line);
            stringBuilder.append(buildRow());
        }
        stringBuilder.append(line);
        return stringBuilder.toString();
    }
}
