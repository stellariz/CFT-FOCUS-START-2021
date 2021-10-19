package ru.cftfocusstart.task2.figures;


import java.util.logging.Logger;

public final class Rectangle extends Figure {

    private final double firstSideLength;
    private final double secondSideLength;


    public Rectangle(double firstSideLength, double secondSideLength){
        super(TypesOfFigures.RECTANGLE);
        this.firstSideLength = firstSideLength;
        this.secondSideLength = secondSideLength;
    }

    @Override
    public void getInfo(Logger logger) {

    }

    @Override
    protected double getArea() {
        return firstSideLength * secondSideLength;
    }

    @Override
    protected double getPerimeter() {
        return (firstSideLength + secondSideLength) * 2;
    }

    private double getShortSide(){
        return Math.min(firstSideLength, secondSideLength);
    }

    private double getLongSide(){
        return Math.max(firstSideLength, secondSideLength);
    }

    private double getLengthDiagonal(){
        return Math.sqrt(firstSideLength * firstSideLength + secondSideLength * secondSideLength);
    }
}
