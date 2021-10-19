package ru.cftfocusstart.task1.app;

import ru.cftfocusstart.task1.utils.*;

public class Application implements Runnable{

    @Override
    public void run() {
        int sizeOfTable = Reader.read();
        try {
            ArgumentsChecker.checkSizeOfTableAndCell(sizeOfTable);
        } catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
            return;
        }
        int sizeOfCell = SizeOfCellCounter.countSizeOfCell(sizeOfTable);
        TableBuilder tableBuilder = new TableBuilder(sizeOfCell, sizeOfTable);
        TablePrinter.printTable(tableBuilder.buildTable());
    }
}
