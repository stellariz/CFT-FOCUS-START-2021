package ru.cftfocusstart.task1.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class Reader {

    public static int read(){
        int sizeOfTable = 0;
        String line = "";
        System.out.print("Please enter size of table: ");
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
            line = bufferedReader.readLine();
            sizeOfTable = Integer.parseInt(line);
        } catch (IOException e){
            System.err.println("Can't read from keyboard: " + e.getMessage());
        } catch (NumberFormatException e){
            System.err.println("\"" + line + "\" is not a number!");
        }
        return sizeOfTable;
    }
}
