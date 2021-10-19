package ru.cftfocusstart.task2.figures;

import java.util.logging.Logger;

public final class Circle extends Figure {
    private final double radius;

    public Circle(double radius){
        super(TypesOfFigures.CIRCLE);
        this.radius = radius;
    }

    @Override
    public void getInfo(Logger logger) {

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
