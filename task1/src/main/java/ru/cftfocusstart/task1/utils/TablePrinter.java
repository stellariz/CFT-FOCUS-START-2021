package ru.cftfocusstart.task1.utils;


import java.io.OutputStream;
import java.io.PrintWriter;

public final class TablePrinter {

    private TablePrinter() {
    }


    public static void printTable(String table, OutputStream os) {
        PrintWriter printWriter = new PrintWriter(os, true);
        printWriter.println(table);
    }
}
