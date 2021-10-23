package ru.cftfocusstart.task2.figures;


import ru.cftfocusstart.task2.utils.LogMessages;

import java.util.logging.Logger;

public final class Circle extends Figure {

    private final double radius;

    public Circle(double[] params) {
        super(TypesOfFigures.CIRCLE, params);
        radius = params[0];
    }

    @Override
    public void getInfo(Logger logger) {
        super.getInfo(logger);
        logger.info(LogMessages.RADIUS.msg + radius);
        logger.info(LogMessages.DIAMETER.msg + getDiameter());
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
        if (args.length != 1) {
            throw new IllegalArgumentException("Incorrect number of parameters for circle: " + args.length
                    + ", but should be only one!");
        }
    }

    public double getDiameter() {
        return 2 * radius;
    }
}
