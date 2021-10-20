package ru.cftfocusstart.task2.utils;

import ru.cftfocusstart.task2.figures.*;

import java.util.Arrays;
import java.util.List;


public final class Parser {

    public static Figure getFigure(List<String> args){
        Figure figure;
        CheckerArguments.checkNumberOfStrings(args);
        double[] params = Arrays.stream(args.get(1).split(" ")).mapToDouble(Double::parseDouble).toArray();
        switch (args.get(0)){
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
                throw new IllegalArgumentException("There is no figure!");
        }
        CheckerArguments.checkArgs(figure.getType(), params);
        return figure;
    }

}
