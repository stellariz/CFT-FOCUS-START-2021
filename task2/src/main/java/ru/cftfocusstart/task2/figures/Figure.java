package ru.cftfocusstart.task2.figures;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.util.Precision;
import ru.cftfocusstart.task2.utils.DisplayedMessages;
import ru.cftfocusstart.task2.utils.PrintMode;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

@Slf4j
public abstract class Figure {

    protected final TypeOfFigure type;

    protected Figure(TypeOfFigure type, double[] args, int expectedParamsNumber) {
        this.type = type;
        checkArgs(args, expectedParamsNumber);
    }

    public TypeOfFigure getType() {
        log.info("Getting type of figure");
        return type;
    }

    public void getInfo(PrintMode printMode, String[] fileNames) throws FileNotFoundException {
        String commonInfo = getCommonInfo();
        String uniqueInfo = getUniqueInfo();
        switch (printMode) {
            case OFF:
                break;
            case CONSOLE_MODE:
                System.out.printf("%s%s", commonInfo, uniqueInfo);
                break;
            case FILE_MODE:
                try (PrintWriter printWriter = new PrintWriter(fileNames[1])) {
                    printWriter.printf("%s%s", commonInfo, uniqueInfo);
                }
                break;
        }
    }

    private String getCommonInfo() {
        log.info("Getting common info");
        StringBuilder sb = new StringBuilder(100);
        return sb.append(DisplayedMessages.FIGURE_TYPE.getMsg()).
                append(getType().getName()).append(System.lineSeparator()).
                append(DisplayedMessages.AREA.getMsg()).
                append(Precision.round(getArea(), 2)).
                append(System.lineSeparator()).
                append(DisplayedMessages.PERIMETER.getMsg()).
                append(Precision.round(getPerimeter(), 2)).
                append(System.lineSeparator()).toString();
    }

    private void checkArgs(double[] args, int expectedParamsNumber) {
        log.info("Checking negative and zero parameters");
        if (args == null) {
            throw new IllegalArgumentException("Null cannot be passed in parameters of figure!");
        }
        for (double param : args) {
            if (param <= 0.0) {
                throw new IllegalArgumentException("Parameter " + param + " less than or equal to zero!");
            }
        }
        log.info("Checking number of parameters");
        if (args.length != expectedParamsNumber) {
            throw new IllegalArgumentException("Incorrect number of parameters for " + getType() + ": " +
                    args.length + ", but expected: " + expectedParamsNumber);
        }
    }

    protected abstract double getArea();

    protected abstract double getPerimeter();

    protected abstract String getUniqueInfo();
}
