package ru.cftfocusstart.task2.app;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task2.figures.Figure;
import ru.cftfocusstart.task2.utils.FigureCreator;

@Slf4j
public class Application {

    private static boolean doesPrintInConsole(String printKey) {
        log.info("Reading user's arguments");
        switch (printKey) {
            case "-c":
                return true;
            case "-d":
                return false;
            default:
                throw new IllegalArgumentException("Unknown argument for print");
        }
    }

    private static void checkLengthUserArguments(String[] args) {
        log.info("Checking number of arguments");
        if (args == null) {
            throw new IllegalArgumentException("Null cannot be passed in program's arguments");
        }
        if (args.length != 2) {
            throw new IllegalArgumentException("Incorrect number of parameters for rectangle: " + args.length
                    + ", but should be only two!");
        }
    }

    public static void run(String[] args) {
        log.info("Running app");
        try {
            checkLengthUserArguments(args);
            String printKey = args[0];
            String fileName = args[1];
            Figure a = FigureCreator.getFigure(fileName);
            a.logInfo(doesPrintInConsole(printKey));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
