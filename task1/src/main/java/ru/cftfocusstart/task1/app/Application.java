package ru.cftfocusstart.task1.app;

import ru.cftfocusstart.task1.utils.*;

import java.io.IOException;

public class Application {

    public void run() {
        try {
            int sizeOfTable = Reader.read();
            ArgumentsChecker.checkTableSize(sizeOfTable);
            TableBuilder tableBuilder = new TableBuilder(sizeOfTable);
            TablePrinter.printTable(tableBuilder.buildTable());
        } catch (IllegalArgumentException  e) {
            System.err.println(e.getMessage());
        }
    }
}
