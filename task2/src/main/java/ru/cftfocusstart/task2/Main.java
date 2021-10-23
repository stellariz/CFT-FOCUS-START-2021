package ru.cftfocusstart.task2;


import ru.cftfocusstart.task2.figures.Figure;
import ru.cftfocusstart.task2.utils.FigureCreator;

import java.io.IOException;
import java.util.logging.Logger;


public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("myLogger");
        try {
            Figure a = FigureCreator.getFigure(args[0]);
            a.getInfo(logger);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
