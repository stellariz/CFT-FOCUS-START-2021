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
        List<String> args = getFigureParams(fileName);
        double[] params = Arrays.stream(args.get(1).split(" ")).mapToDouble(Double::parseDouble).toArray();
        switch (args.get(0)) {
            case "CIRCLE":
                checkArgs(TypesOfFigures.CIRCLE, params);
                return new Circle(params);
            case "TRIANGLE":
                checkArgs(TypesOfFigures.TRIANGLE, params);
                return new Triangle(params);
            case "RECTANGLE":
                checkArgs(TypesOfFigures.RECTANGLE, params);
                return new Rectangle(params);
            default:
                throw new IllegalArgumentException("There is no figure: \"" + args.get(0) + "\"");
        }
    }


    private static List<String> getFigureParams(String filename) throws FileNotFoundException {
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


    private static void checkPosParams(double[] args) {
        for (double param : args) {
            if (param < 0.0) {
                throw new IllegalArgumentException("Parameter " + param + " is negative!");
            }
        }
    }


    private static void checkArgs(TypesOfFigures type, double[] args) {
        switch (type) {
            case CIRCLE:
                if (args.length != 1) {
                    throw new IllegalArgumentException("Incorrect number of parameters for circle: " + args.length
                            + ", but should be only one!");
                }
                break;
            case RECTANGLE:
                if (args.length != 2) {
                    throw new IllegalArgumentException("Incorrect number of parameters for rectangle: " + args.length
                            + ", but should be only two!");
                }
                break;
            case TRIANGLE:
                if (args.length != 3) {
                    throw new IllegalArgumentException("Incorrect number of parameters for for triangle: " + args.length
                            + ", but should be only three!");
                }
                checkTriangleSides(args);
                break;
        }
        checkPosParams(args);
    }


    private static void checkTriangleSides(double[] args) {
        for (int i = 0; i < args.length; ++i) {
            if (Double.compare(args[i], args[(i + 1) % 3] + args[(i + 2) % 3]) == 1) {
                throw new IllegalArgumentException("No such triangle exists!");
            }
        }
    }
}
