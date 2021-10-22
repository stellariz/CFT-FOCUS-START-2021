package ru.cftfocusstart.task1.app;

import ru.cftfocusstart.task1.utils.TableBuilder;
import ru.cftfocusstart.task1.utils.TablePrinter;
import ru.cftfocusstart.task1.utils.TableSizeReader;

import java.util.NoSuchElementException;


public class Application {

    public void run() {
        try {
            int sizeOfTable = TableSizeReader.readSize(System.in);
            TableBuilder tableBuilder = new TableBuilder(sizeOfTable);
            TablePrinter.printTable(tableBuilder.buildTable(), System.out);
        } catch (IllegalArgumentException | NoSuchElementException | IllegalStateException e) {
            System.err.println(e.getMessage());
        }
    }
}
