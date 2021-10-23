package ru.cftfocusstart.task2.utils;

import ru.cftfocusstart.task2.figures.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public final class FigureCreator {

    private FigureCreator() {
    }


    public static Figure getFigure(String fileName) throws FileNotFoundException {
        List<String> args = getFigureParamsFromFile(fileName);
        double[] params = Arrays.stream(args.get(1).split(" ")).mapToDouble(Double::parseDouble).toArray();
        switch (args.get(0)) {
            case "CIRCLE":
                return new Circle(params);
            case "TRIANGLE":
                return new Triangle(params);
            case "RECTANGLE":
                return new Rectangle(params);
            default:
                throw new IllegalArgumentException("There is no figure: \"" + args.get(0) + "\"");
        }
    }


    private static List<String> getFigureParamsFromFile(String filename) throws FileNotFoundException {
        List<String> args = new LinkedList<>();
        Scanner scanner = new Scanner(new FileInputStream(filename));
        while (scanner.hasNextLine()) {
            args.add(scanner.nextLine());
        }
        if (args.size() != 2) {
            throw new IllegalArgumentException("Should be two strings in file!");
        }
        return args;
    }
}
