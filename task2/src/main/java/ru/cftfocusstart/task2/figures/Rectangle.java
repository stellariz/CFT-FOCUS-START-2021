package ru.cftfocusstart.task2.figures;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.util.Precision;
import ru.cftfocusstart.task2.utils.DisplayMessages;

@Slf4j
public final class Rectangle extends Figure {

    private final double firstSideLength;
    private final double secondSideLength;


    public Rectangle(double[] params) {
        super(TypeOfFigure.RECTANGLE, params);
        firstSideLength = params[0];
        secondSideLength = params[1];
    }

    public double getShortSide() {
        log.info("Getting short side of rectangle");
        return Math.min(firstSideLength, secondSideLength);
    }

    public double getLongSide() {
        log.info("Getting long side of rectangle");
        return Math.max(firstSideLength, secondSideLength);
    }

    public double getLengthDiagonal() {
        log.info("Getting diagonal's length of rectangle");
        if (degenerate) {
            return 0.0;
        }
        return Math.sqrt(firstSideLength * firstSideLength + secondSideLength * secondSideLength);
    }

    @Override
    protected String getUniqueInfo() {
        log.info("Getting unique info about rectangle");
        StringBuilder sb = new StringBuilder(200);
        return sb.append(DisplayMessages.DIAGONAL.msg).append(Precision.round(getLengthDiagonal(), 2)).
                append(System.lineSeparator()).
                append(DisplayMessages.LONG_SIDE.msg).append(Precision.round(getLongSide(), 2)).
                append(System.lineSeparator()).
                append(DisplayMessages.SHORT_SIDE.msg).append(Precision.round(getShortSide(), 2)).
                append(System.lineSeparator()).toString();
    }

    @Override
    public double getArea() {
        log.info("Getting area of rectangle");
        return firstSideLength * secondSideLength;
    }

    @Override
    public double getPerimeter() {
        log.info("Getting perimeter of rectangle");
        if (degenerate) {
            return getLongSide();
        }
        return (firstSideLength + secondSideLength) * 2;
    }

    @Override
    protected void checkArgsNumberForFigure(double[] args) {
        log.info("Checking number of parameters for rectangle");
        if (args == null) {
            throw new IllegalArgumentException("Null cannot be passed in arguments!");
        }
        if (args.length != 2) {
            throw new IllegalArgumentException("Incorrect number of parameters for rectangle: " + args.length
                    + ", but should be only two!");
        }
    }
}
