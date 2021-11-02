package ru.cftfocusstart.task2.utils;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task2.figures.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Slf4j
public final class FigureCreator {

    private static final String SEPARATOR = " ";
    private static final int STRINGS_NUMBER_IN_FILE = 2;

    private FigureCreator() {
    }

    public static Figure getFigure(String fileName) throws IOException {
        List<String> args = null;
        double[] params;
        TypeOfFigure readType;
        try (FileInputStream fis = new FileInputStream(fileName)) {
            args = getFigureParams(fis);
            params = Arrays.stream(args.get(1).split(SEPARATOR)).mapToDouble(Double::parseDouble).toArray();
            readType = TypeOfFigure.valueOf(args.get(0));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Incorrect parameter input format");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("There is no figure: \"" + args.get(0) + "\"");
        }
        switch (readType) {
            case CIRCLE:
                log.info("Creating circle");
                return new Circle(params);
            case TRIANGLE:
                log.info("Creating triangle");
                return new Triangle(params);
            case RECTANGLE:
                log.info("Creating rectangle");
                return new Rectangle(params);
            default:
                assert false;
                return null;
        }
    }

    private static List<String> getFigureParams(InputStream inputStream) {
        log.info("Reading file");
        List<String> args = new ArrayList<>();
        Scanner scanner = new Scanner(inputStream);
        for (int i = 0; i < STRINGS_NUMBER_IN_FILE; ++i) {
            if (scanner.hasNextLine()) {
                args.add(scanner.nextLine());
            } else
                break;
        }
        if (args.size() != STRINGS_NUMBER_IN_FILE) {
            throw new IllegalArgumentException("Should be two strings in file!");
        }
        return args;
    }
}
