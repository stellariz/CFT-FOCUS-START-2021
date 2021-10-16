package ru.cftfocusstart.task1;

public class Main {
    public static void main(String[] args) {
        int sizeOfTable = Reader.read();
        try {
            ArgumentsChecker.checkSizeOfTableAndCell(sizeOfTable);
            int sizeOfCell = SizeOfCellCounter.countSizeOfCell(sizeOfTable);
            TableBuilder tableBuilder = new TableBuilder(sizeOfCell, sizeOfTable);
            TablePrinter.printTable(tableBuilder.buildTable());
        } catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
    }
}
