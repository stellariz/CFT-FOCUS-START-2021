package ru.cftfocusstart.task2.figures;


import ru.cftfocusstart.task2.utils.LogMessages;


import java.util.logging.Logger;

public final class Rectangle extends Figure {

    private final double firstSideLength;
    private final double secondSideLength;


    public Rectangle(double[] params){
        super(TypesOfFigures.RECTANGLE);
        firstSideLength = params[0];
        secondSideLength = params[1];
    }

    @Override
    public void getInfo(Logger logger) {
        super.getInfo(logger);
        logger.info( LogMessages.DIAGONAL.msg + getLengthDiagonal());
        logger.info( LogMessages.LONG_SIDE.msg + getLongSide());
        logger.info(LogMessages.SHORT_SIDE.msg + getShortSide());
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
