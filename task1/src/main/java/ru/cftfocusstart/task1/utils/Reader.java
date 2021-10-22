package ru.cftfocusstart.task1.utils;


import java.io.InputStream;
import java.util.Scanner;

public final class Reader {

    public static int readSize(InputStream is) {
        Scanner scanner = new Scanner(is);
        System.out.print("Please enter size of table: ");
        // reading number
        return scanner.nextInt();
    }
}
