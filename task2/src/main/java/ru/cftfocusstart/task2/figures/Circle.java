package ru.cftfocusstart.task2.figures;

import org.apache.commons.math3.util.Precision;
import ru.cftfocusstart.task2.utils.LogMessages;

public final class Circle extends Figure {

    private final double radius;

    public Circle(double[] params) {
        super(TypesOfFigures.CIRCLE, params);
        radius = params[0];
    }

    @Override
    protected String getUniqueInfo() {
        StringBuilder sb = new StringBuilder(150).append(System.lineSeparator());
        return sb.append(LogMessages.RADIUS.msg).append(Precision.round(getRadius(), 2)).
                append(System.lineSeparator()).
                append(LogMessages.DIAMETER.msg).append(Precision.round(getDiameter(), 2)).toString();
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    protected void checkParamsSize(double[] args) {
        if (args == null) {
            throw new IllegalArgumentException("Null cannot be passed in arguments!");
        }
        if (args.length != 1) {
            throw new IllegalArgumentException("Incorrect number of parameters for rectangle: " + args.length
                    + ", but should be only one!");
        }
    }

    public double getRadius() {
        return radius;
    }

    public double getDiameter() {
        return 2 * getRadius();
    }
}
