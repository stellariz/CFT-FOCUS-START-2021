package ru.cftfocusstart.task2.figures;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.util.Precision;
import ru.cftfocusstart.task2.utils.DisplayedMessages;

@Slf4j
public final class Rectangle extends Figure {

    private static final int PARAMETERS_NUMBER = 2;
    private final double firstSideLength;
    private final double secondSideLength;

    public Rectangle(double[] params) {
        super(TypeOfFigure.RECTANGLE, params, PARAMETERS_NUMBER);
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
        return Math.sqrt(firstSideLength * firstSideLength + secondSideLength * secondSideLength);
    }

    @Override
    protected String getUniqueInfo() {
        log.info("Getting unique info about rectangle");
        StringBuilder sb = new StringBuilder(200);
        return sb.append(DisplayedMessages.DIAGONAL.getMsg())
                .append(Precision.round(getLengthDiagonal(), 2))
                .append(System.lineSeparator())
                .append(DisplayedMessages.LONG_SIDE.getMsg())
                .append(Precision.round(getLongSide(), 2))
                .append(System.lineSeparator())
                .append(DisplayedMessages.SHORT_SIDE.getMsg())
                .append(Precision.round(getShortSide(), 2))
                .append(System.lineSeparator())
                .toString();
    }

    @Override
    public double getArea() {
        log.info("Getting area of rectangle");
        return firstSideLength * secondSideLength;
    }

    @Override
    public double getPerimeter() {
        log.info("Getting perimeter of rectangle");
        return (firstSideLength + secondSideLength) * 2;
    }
}
