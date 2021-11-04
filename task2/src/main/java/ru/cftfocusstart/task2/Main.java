package ru.cftfocusstart.task2;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task2.figures.Figure;
import ru.cftfocusstart.task2.utils.CheckerInputData;
import ru.cftfocusstart.task2.utils.FigureCreator;
import ru.cftfocusstart.task2.utils.PrintMode;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

@Slf4j
public class Main {

    private static void printInfo(String info, OutputStream outputStream) {
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.print(info);
        printWriter.flush();
    }

    private static void run(String[] args) {
        log.info("Running app");
        try {
            PrintMode printMode = CheckerInputData.getPrintMode(args);
            String[] fileNames = CheckerInputData.getFileNames(printMode, args);
            Figure a = FigureCreator.getFigure(fileNames[0]);
            switch (printMode) {
                case OFF:
                    break;
                case CONSOLE_MODE:
                    printInfo(a.getInfo(), System.out);
                    break;
                case FILE_MODE:
                    FileOutputStream fileOutputStream = new FileOutputStream(fileNames[1]);
                    printInfo(a.getInfo(), fileOutputStream);
                    fileOutputStream.close();
                    break;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        run(args);
    }
}
