package ru.cftfocusstart.task2.figures;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.util.Precision;
import ru.cftfocusstart.task2.utils.LogMessages;

@Slf4j
public abstract class Figure {

    protected final TypesOfFigures type;
    protected boolean degenerate = false;
    protected boolean point = false;

    protected Figure(TypesOfFigures type, double[] args) {
        checkArgs(args);
        this.type = type;
    }


    public void logInfo(boolean doesDisplayInfo) {
        log.info("Getting info about figure");
        if (doesDisplayInfo) {
            System.out.printf("%s%s", getCommonInfo(), getUniqueInfo());
        }
    }

    private String getCommonInfo() {
        log.info("Getting common info");
        StringBuilder sb = new StringBuilder(100);
        return sb.append(LogMessages.FIGURE_TYPE.msg).append(getType().name).append(System.lineSeparator()).
                append(LogMessages.AREA.msg).append(Precision.round(getArea(), 2)).append(System.lineSeparator()).
                append(LogMessages.PERIMETER.msg).append(Precision.round(getPerimeter(), 2)).
                append(System.lineSeparator()).toString();
    }

    protected abstract String getUniqueInfo();

    protected abstract double getArea();

    protected abstract double getPerimeter();

    public TypesOfFigures getType() {
        return type;
    }

    protected void checkArgs(double[] args) {
        checkParamsSize(args);
        countNegativeAndZeroesParams(args);
    }

    protected abstract void checkParamsSize(double[] args);

    private void countNegativeAndZeroesParams(double[] args) {
        int counter = 0;
        for (double param : args) {
            if (param < 0.0) {
                throw new IllegalArgumentException("Parameter " + param + " is negative!");
            }
            if (param == 0.0) {
                degenerate = true;
                ++counter;
            }
        }
        if (counter == args.length) {
            point = true;
        }
    }
}
