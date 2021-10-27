package ru.cftfocusstart.task1.utils;


import java.io.InputStream;
import java.util.Scanner;

public final class TableSizeReader {

    private TableSizeReader() {
    }


    public static int readSize(InputStream is) {
        Scanner scanner = new Scanner(is);
        System.out.print("Please enter size of table: ");
        while (!scanner.hasNextInt()) {
            String line = scanner.nextLine();
            System.out.print("\"" + line + "\" is not a number! Please enter the number again: ");
        }
        return scanner.nextInt();
    }
}
