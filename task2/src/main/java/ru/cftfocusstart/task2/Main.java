package ru.cftfocusstart.task2;


import ru.cftfocusstart.task2.figures.Figure;
import ru.cftfocusstart.task2.utils.Parser;
import ru.cftfocusstart.task2.utils.Reader;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;


public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("myLogger");
        try {
            List<String> tmp = Reader.read();
            Figure a =  Parser.getFigure(tmp);
            a.getInfo(logger);
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
