package ru.cftfocusstart.task1.app;

import ru.cftfocusstart.task1.utils.*;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;


public class Application {

    public void run() {
        try {
            int sizeOfTable = Reader.readSize(System.in);
            TableBuilder tableBuilder = new TableBuilder(sizeOfTable);
            TablePrinter.printTable(tableBuilder.buildTable(), System.out);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Value is not a number!");
        } catch (NoSuchElementException e) {
            System.err.println("Input is empty!");
        }
    }
}
