package ru.cftfocusstart.task1;

public final class TableBuilder {
    public static final char PIPE = '|';
    public static final char MINUS = '-';
    public static final char PLUS = '+';

    private final int sizeOfCell;
    private final int sizeOfTable;

    private final int sizeOfFirstColumn;
    private int coefficient = 1;

    public TableBuilder(int sizeOfCell, int sizeOfTable) {
        this.sizeOfCell = sizeOfCell;
        this.sizeOfTable = sizeOfTable;
        this.sizeOfFirstColumn = sizeOfCell == 1 ? sizeOfCell : sizeOfCell - 1;
    }

    public int getSizeOfCell() {
        return sizeOfCell;
    }


    public int getSizeOfTable(){
        return sizeOfTable;
    }


    private String buildRow(){
        int sizeRowForTheFirstColumn = sizeOfFirstColumn - Integer.toString(coefficient).length();
        StringBuilder stringBuilder = new StringBuilder(" ".repeat(sizeRowForTheFirstColumn)).append(coefficient);
        for (int i = 1; i <= sizeOfTable; ++i){
            int curSizeOfCell = sizeOfCell - Integer.toString(i*coefficient).length();
            stringBuilder.append(PIPE).append(" ".repeat(curSizeOfCell)).append(i*coefficient);
        }
        ++coefficient;
        return stringBuilder.toString() + '\n';
    }


    private String buildHead(){
        StringBuilder stringBuilder = new StringBuilder(" ".repeat(sizeOfFirstColumn));
        for (int i = 1; i <= sizeOfTable; ++i){
            stringBuilder.append(PIPE).append(" ".repeat(sizeOfCell - Integer.toString(i).length())).append(i);
        }
        return stringBuilder.toString() + '\n';
    }


    private String buildLine(){
        String lineForTheFirstColumn = String.valueOf(MINUS).repeat(sizeOfFirstColumn);
        return lineForTheFirstColumn + (PLUS + String.valueOf(MINUS).repeat(sizeOfCell)).repeat(sizeOfTable) + '\n';
    }

    public String buildTable(){
        StringBuilder stringBuilder = new StringBuilder(buildHead());
        for (int i = 0; i < sizeOfTable; ++i){
            stringBuilder.append(buildLine());
            stringBuilder.append(buildRow());
        }
        stringBuilder.append(buildLine());
        return stringBuilder.toString();
    }
}
