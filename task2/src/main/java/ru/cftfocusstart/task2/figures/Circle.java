package ru.cftfocusstart.task2.figures;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.util.Precision;
import ru.cftfocusstart.task2.utils.DisplayMessages;

@Slf4j
public final class Circle extends Figure {

    private final double radius;

    public Circle(double[] params) {
        super(TypeOfFigure.CIRCLE, params);
        radius = params[0];
    }

    public double getRadius() {
        log.info("Getting radius of circle");
        return radius;
    }

    public double getDiameter() {
        log.info("Getting diameter of circle");
        return 2 * getRadius();
    }

    @Override
    protected String getUniqueInfo() {
        log.info("Getting unique info about circle");
        StringBuilder sb = new StringBuilder(150);
        return sb.append(DisplayMessages.RADIUS.msg).append(Precision.round(getRadius(), 2)).
                append(System.lineSeparator()).
                append(DisplayMessages.DIAMETER.msg).append(Precision.round(getDiameter(), 2)).
                append(System.lineSeparator()).toString();
    }

    @Override
    public double getArea() {
        log.info("Getting area of circle");
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        log.info("Getting perimeter of circle");
        return 2 * Math.PI * getRadius();
    }

    @Override
    protected void checkArgsNumberForFigure(double[] args) {
        log.info("Checking number of parameters for circle");
        if (args == null) {
            throw new IllegalArgumentException("Null cannot be passed in arguments!");
        }
        if (args.length != 1) {
            throw new IllegalArgumentException("Incorrect number of parameters for rectangle: " + args.length
                    + ", but should be only one!");
        }
    }
}
