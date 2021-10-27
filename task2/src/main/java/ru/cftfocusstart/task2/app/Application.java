package ru.cftfocusstart.task2.app;

import lombok.extern.log4j.Log4j2;
import ru.cftfocusstart.task2.figures.Figure;
import ru.cftfocusstart.task2.utils.FigureCreator;

@Log4j2
public class Application {
    public static void run(String fileName) {
        try {
            Figure a = FigureCreator.getFigure(fileName);
            a.logInfo();
        } catch (Exception e) {
            log.error(e);
        }
    }
}
