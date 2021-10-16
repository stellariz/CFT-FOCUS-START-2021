package ru.cftfocusstart.task1;

import java.util.Scanner;

public final class Reader {

    public static int read(){
        int sizeOfTable;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter size of table: ");
        sizeOfTable = scanner.nextInt();
        scanner.close();
        return sizeOfTable;
    }
}
