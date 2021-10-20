package ru.cftfocusstart.task2.figures;


import ru.cftfocusstart.task2.utils.LogMessages;

import java.util.logging.Logger;

public final class Circle extends Figure {
    private final double radius;

    public Circle(double[] params){
        super(TypesOfFigures.CIRCLE);
        radius = params[0];
    }

    @Override
    public void getInfo(Logger logger) {
        super.getInfo(logger);
        logger.info(LogMessages.RADIUS.msg + radius);
        logger.info(LogMessages.DIAMETER.msg + getDiameter());
    }

    @Override
    protected double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    protected double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    private double getDiameter(){
        return  2 * radius;
    }
}
