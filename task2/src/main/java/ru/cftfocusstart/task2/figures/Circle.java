package ru.cftfocusstart.task2.figures;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.util.Precision;
import ru.cftfocusstart.task2.utils.DisplayedMessages;

@Slf4j
public final class Circle extends Figure {

    private static final int PARAMETERS_NUMBER = 1;
    private final double radius;

    public Circle(double[] params) {
        super(TypeOfFigure.CIRCLE, params, PARAMETERS_NUMBER);
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
        return sb.append(DisplayedMessages.RADIUS.getMsg()).
                append(Precision.round(getRadius(), 2)).
                append(System.lineSeparator()).
                append(DisplayedMessages.DIAMETER.getMsg()).
                append(Precision.round(getDiameter(), 2)).
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
}
