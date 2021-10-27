package ru.cftfocusstart.task2.figures;


import org.apache.commons.math3.util.Precision;
import ru.cftfocusstart.task2.utils.LogMessages;

public final class Rectangle extends Figure {

    private final double firstSideLength;
    private final double secondSideLength;


    public Rectangle(double[] params) {
        super(TypesOfFigures.RECTANGLE, params);
        firstSideLength = params[0];
        secondSideLength = params[1];
    }


    @Override
    protected String getUniqueInfo() {
        StringBuilder sb = new StringBuilder(200).append(System.lineSeparator());
        return sb.append(LogMessages.DIAGONAL.msg).append(Precision.round(getLengthDiagonal(), 2)).
                append(System.lineSeparator()).
                append(LogMessages.LONG_SIDE.msg).append(Precision.round(getLongSide(), 2)).
                append(System.lineSeparator()).
                append(LogMessages.SHORT_SIDE.msg).append(Precision.round(getShortSide(), 2)).toString();
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
