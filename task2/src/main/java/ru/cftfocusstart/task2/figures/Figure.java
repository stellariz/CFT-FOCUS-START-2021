package ru.cftfocusstart.task2.figures;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.util.Precision;
import ru.cftfocusstart.task2.utils.DisplayMessages;

@Slf4j
public abstract class Figure {

    protected final TypeOfFigure type;
    protected boolean degenerate = false;
    protected boolean point = false;

    protected Figure(TypeOfFigure type, double[] args) {
        checkArgs(args);
        this.type = type;
    }

    public TypeOfFigure getType() {
        log.info("Getting type of figure");
        return type;
    }

    public void getInfo(boolean doesDisplayInfo) {
        String commonInfo = getCommonInfo();
        String uniqueInfo = getUniqueInfo();
        if (doesDisplayInfo) {
            System.out.printf("%s%s", commonInfo, uniqueInfo);
        }
    }

    private String getCommonInfo() {
        log.info("Getting common info");
        StringBuilder sb = new StringBuilder(100);
        return sb.append(DisplayMessages.FIGURE_TYPE.msg).append(getType().name).append(System.lineSeparator()).
                append(DisplayMessages.AREA.msg).append(Precision.round(getArea(), 2)).append(System.lineSeparator()).
                append(DisplayMessages.PERIMETER.msg).append(Precision.round(getPerimeter(), 2)).
                append(System.lineSeparator()).toString();
    }

    private void checkArgs(double[] args) {
        checkArgsNumberForFigure(args);
        checkNegativeParamsAndFigureDegeneracy(args);
    }

    private void checkNegativeParamsAndFigureDegeneracy(double[] args) {
        log.info("Counting negative and zero parameters");
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

    protected abstract double getArea();

    protected abstract double getPerimeter();

    protected abstract String getUniqueInfo();

    protected abstract void checkArgsNumberForFigure(double[] args);
}
