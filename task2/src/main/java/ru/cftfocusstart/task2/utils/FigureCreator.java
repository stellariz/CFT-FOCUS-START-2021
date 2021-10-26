package ru.cftfocusstart.task2.utils;

import ru.cftfocusstart.task2.figures.Circle;
import ru.cftfocusstart.task2.figures.Figure;
import ru.cftfocusstart.task2.figures.Rectangle;
import ru.cftfocusstart.task2.figures.Triangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public final class FigureCreator {

    private FigureCreator() {
    }


    public static Figure getFigure(String fileName) throws FileNotFoundException {
        List<String> args = getFigureParamsFromFile(new FileInputStream(fileName));
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


    static List<String> getFigureParamsFromFile(FileInputStream fileInputStream) {
        List<String> args = new ArrayList<>();
        Scanner scanner = new Scanner(fileInputStream);
        while (scanner.hasNextLine()) {
            args.add(scanner.nextLine());
        }
        if (args.size() != 2) {
            throw new IllegalArgumentException("Should be two strings in file!");
        }
        return args;
    }
}
