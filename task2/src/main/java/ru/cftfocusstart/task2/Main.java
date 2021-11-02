package ru.cftfocusstart.task2;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task2.figures.Figure;
import ru.cftfocusstart.task2.utils.CheckerInputData;
import ru.cftfocusstart.task2.utils.FigureCreator;
import ru.cftfocusstart.task2.utils.PrintMode;

@Slf4j
public class Main {

    public static void run(String[] args) {
        log.info("Running app");
        try {
            PrintMode printMode = CheckerInputData.getPrintMode(args);
            String[] fileNames = CheckerInputData.getFileNames(printMode, args);
            Figure a = FigureCreator.getFigure(fileNames[0]);
            a.getInfo(printMode, fileNames);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        run(args);
    }
}
