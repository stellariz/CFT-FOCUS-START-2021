package ru.cftfocusstart.task2.utils;

import ru.cftfocusstart.task2.figures.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public final class FigureCreator {

    private static List<String> getFigureParams(String filename) throws FileNotFoundException {
        List<String> args = new LinkedList<>();
        Scanner scanner = new Scanner(new FileInputStream(filename));
        while (scanner.hasNextLine()) {
            args.add(scanner.nextLine());
        }
        return args;
    }


    public static Figure getFigure(String fileName) throws FileNotFoundException {
        Figure figure;
        List<String> args = getFigureParams(fileName);
        checkNumberOfStrings(args);
        double[] params = Arrays.stream(args.get(1).split(" ")).mapToDouble(Double::parseDouble).toArray();
        switch (args.get(0)) {
            case "CIRCLE":
                figure = new Circle(params);
                break;
            case "TRIANGLE":
                figure = new Triangle(params);
                break;
            case "RECTANGLE":
                figure = new Rectangle(params);
                break;
            default:
                throw new IllegalArgumentException("There is no figure: \"" + args.get(0) + "\"");
        }
        checkArgs(figure.getType(), params);
        return figure;
    }


    private static void checkNumberOfStrings(List<String> args) {
        if (args.size() > 2 || args.isEmpty()) {
            throw new IllegalArgumentException("Should be only two strings in file!");
        }
    }


    private static void checkPosValue(double[] args) {
        for (double param : args) {
            if (param < 0.0) {
                throw new IllegalArgumentException("Parameters should be positive. Please re-enter.");
            }
        }
    }


    private static void checkArgs(TypesOfFigures type, double[] args) {
        switch (type) {
            case CIRCLE:
                if (args.length != 1) {
                    throw new IllegalArgumentException("Incorrect number of parameters for circle.");
                }
                break;
            case RECTANGLE:
                if (args.length != 2) {
                    throw new IllegalArgumentException("Incorrect number of parameters for rectangle.");
                }
                break;
            case TRIANGLE:
                if (args.length != 3) {
                    throw new IllegalArgumentException("Incorrect number of parameters for triangle.");
                }
                checkTriangleSides(args);
                break;
        }
        checkPosValue(args);
    }


    private static void checkTriangleSides(double[] args) {
        for (int i = 0; i < args.length; ++i) {
            if (args[i] >= args[(i + 1) % 3] + args[(i + 1) % 3]) {
                throw new IllegalArgumentException("Triangle with incorrect sides.");
            }
        }
    }

}
