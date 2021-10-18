package ru.cftfocusstart.task1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class Reader {

    public static int read(){
        int sizeOfTable = 0;
        System.out.print("Please enter size of table: ");
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
            sizeOfTable = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e){
            System.err.println("Can't read from keyboard: " + e.getMessage());
        } catch (NumberFormatException e){
            System.err.println("Incorrect input: " + e.getMessage());
        }
        return sizeOfTable;
    }
}
