package ru.cftfocusstart.task2.utils;


import ru.cftfocusstart.task2.figures.TypesOfFigures;

import java.util.List;

public final class CheckerArguments {

    public static void checkArgs(TypesOfFigures type, double[] args){
        switch (type){
            case CIRCLE:
                if (args.length != 1){
                    throw new IllegalArgumentException("Incorrect number of parameters for circle.");
                }
                break;
            case RECTANGLE:
                if (args.length != 2) {
                    throw new IllegalArgumentException("Incorrect number of parameters for rectangle.");
                }
                break;
            case TRIANGLE:
                if (args.length != 3){
                    throw new IllegalArgumentException("Incorrect number of parameters for triangle.");
                }
                checkTriangleSides(args);
                break;
        }
    }

    public static void checkNumberOfStrings(List<String> args){
        if (args.size() > 2 || args.isEmpty()){
            throw new IllegalArgumentException("Should be only two strings in file!");
        }
    }

    private static void checkTriangleSides(double[] args){
        for (int i = 0; i < args.length; ++i){
            if (args[i] >= args[(i+1) % 3] + args[(i+1) % 3]){
                throw new IllegalArgumentException("Triangle with incorrect sides.");
            }
        }
    }

    private static void checkPosValue(double[] args){
        for (double param : args){
            if (param < 0.0){
                throw new IllegalArgumentException("Parameters should be positive. Please re-enter.");
            }
        }
    }
}
