package ru.cftfocusstart.task1.app;

import ru.cftfocusstart.task1.utils.TableBuilder;
import ru.cftfocusstart.task1.utils.TableSizeReader;

import java.io.PrintWriter;


public class Application {

    public void run() {
        try {
            int sizeOfTable = TableSizeReader.readSize(System.in);
            TableBuilder tableBuilder = new TableBuilder(sizeOfTable);
            PrintWriter printWriter = new PrintWriter(System.out, true);
            printWriter.println(tableBuilder.buildTable());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
