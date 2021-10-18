package ru.cftfocusstart.task1.utils;

public final class SizeOfCellCounter {

    public static int countSizeOfCell(int sizeOfTable){
        int maxNumber = sizeOfTable * sizeOfTable;
        return Integer.toString(maxNumber).length();
    }
}
