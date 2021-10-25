package ru.cftfocusstart.task2.figures;


import ru.cftfocusstart.task2.utils.LogMessages;


import java.util.logging.Logger;

public final class Rectangle extends Figure {

    private final double firstSideLength;
    private final double secondSideLength;


    public Rectangle(double[] params) {
        super(TypesOfFigures.RECTANGLE, params);
        firstSideLength = params[0];
        secondSideLength = params[1];
    }

    @Override
    public void getInfo(Logger logger) {
        super.getInfo(logger);
        logger.info(LogMessages.DIAGONAL.msg + getLengthDiagonal());
        logger.info(LogMessages.LONG_SIDE.msg + getLongSide());
        logger.info(LogMessages.SHORT_SIDE.msg + getShortSide());
    }

    @Override
    public double getArea() {
        return firstSideLength * secondSideLength;
    }

    @Override
    public double getPerimeter() {
        if (degenerate) {
            return getLongSide();
        }
        return (firstSideLength + secondSideLength) * 2;
    }

    @Override
    protected void checkParamsSize(double[] args) {
        if (args == null) {
            throw new IllegalArgumentException("Null cannot be passed in arguments!");
        }
        if (args.length != 2) {
            throw new IllegalArgumentException("Incorrect number of parameters for rectangle: " + args.length
                    + ", but should be only two!");
        }
    }

    public double getShortSide() {
        return Math.min(firstSideLength, secondSideLength);
    }

    public double getLongSide() {
        return Math.max(firstSideLength, secondSideLength);
    }

    public double getLengthDiagonal() {
        if (degenerate) {
            return 0.0;
        }
        return Math.sqrt(firstSideLength * firstSideLength + secondSideLength * secondSideLength);
    }
}
