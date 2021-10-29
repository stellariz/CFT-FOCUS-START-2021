package ru.cftfocusstart.task2.app;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task2.figures.Figure;
import ru.cftfocusstart.task2.utils.FigureCreator;

@Slf4j
public final class Application {

    static boolean doesPrintInConsole(String printKey) {
        log.info("Checking print key");
        if (printKey == null){
            throw new IllegalArgumentException("Print key is null");
        }
        switch (printKey) {
            case "-c":
                return true;
            case "-d":
                return false;
            default:
                throw new IllegalArgumentException("Unknown argument for print");
        }
    }

    static void checkNumberUserArguments(String[] args) {
        log.info("Checking number of arguments");
        if (args == null) {
            throw new IllegalArgumentException("Null cannot be passed in program's arguments");
        }
        if (args.length != 2) {
            throw new IllegalArgumentException("Incorrect number of program's arguments:  " + args.length
                    + ", but should be only two!");
        }
    }

    public static void run(String[] args) {
        log.info("Running app");
        try {
            checkNumberUserArguments(args);
            String printKey = args[0];
            String fileName = args[1];
            boolean print = doesPrintInConsole(printKey);
            Figure a = FigureCreator.getFigure(fileName);
            a.getInfo(print);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
