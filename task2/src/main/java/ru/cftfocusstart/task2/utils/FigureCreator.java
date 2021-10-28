package ru.cftfocusstart.task2.utils;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task2.figures.Circle;
import ru.cftfocusstart.task2.figures.Figure;
import ru.cftfocusstart.task2.figures.Rectangle;
import ru.cftfocusstart.task2.figures.Triangle;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Slf4j
public final class FigureCreator {

    private FigureCreator() {
    }


    public static Figure getFigure(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        List<String> args = getFigureParams(fis);
        fis.close();
        double[] params = Arrays.stream(args.get(1).split(" ")).mapToDouble(Double::parseDouble).toArray();
        switch (args.get(0)) {
            case "CIRCLE":
                log.info("Creating circle");
                return new Circle(params);
            case "TRIANGLE":
                log.info("Creating triangle");
                return new Triangle(params);
            case "RECTANGLE":
                log.info("Creating rectangle");
                return new Rectangle(params);
            default:
                throw new IllegalArgumentException("There is no figure: \"" + args.get(0) + "\"");
        }
    }


    private static List<String> getFigureParams(InputStream inputStream) {
        log.info("Reading file");
        List<String> args = new ArrayList<>();
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            args.add(scanner.nextLine());
        }
        if (args.size() != 2) {
            throw new IllegalArgumentException("Should be two strings in file!");
        }
        return args;
    }
}
