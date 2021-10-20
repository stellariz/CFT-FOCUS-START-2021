package ru.cftfocusstart.task2.utils;

import ru.cftfocusstart.task2.figures.*;

import java.util.Arrays;
import java.util.List;


public class Parser {

    public static Figure getFigure(List<String> args){
        double[] params = Arrays.stream(args.get(1).split(" ")).mapToDouble(Double::parseDouble).toArray();
        switch (args.get(0)){
            case "CIRCLE":
                return new Circle(params);
            case "TRIANGLE":
                return new Triangle(params);
            case "RECTANGLE":
                return new Rectangle(params);
            default:
                throw new IllegalArgumentException("There is no figure!");
        }
    }

}
