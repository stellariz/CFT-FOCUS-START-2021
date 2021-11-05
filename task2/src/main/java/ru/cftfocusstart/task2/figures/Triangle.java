package ru.cftfocusstart.task2.figures;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.util.Precision;
import ru.cftfocusstart.task2.utils.DisplayedMessages;

import java.util.Arrays;

@Slf4j
public final class Triangle extends Figure {

    private static final int PARAMETERS_NUMBER = 3;
    private final double[] sides;
    private boolean degenerate = false;

    public Triangle(double[] params) {
        super(TypeOfFigure.TRIANGLE, params, PARAMETERS_NUMBER);
        checkTriangleRule(params);
        sides = Arrays.copyOf(params, params.length);
    }

    private void checkTriangleRule(double[] args) {
        log.info("Checking triangle rule and is the figure and angle");
        for (int i = 0; i < args.length; ++i) {
            if (Double.compare(args[i], args[(i + 1) % 3] + args[(i + 2) % 3]) == 1) {
                throw new IllegalArgumentException("No such triangle exists!");
            }
            if (Double.compare(args[i], args[(i + 1) % 3] + args[(i + 2) % 3]) == 0) {
                degenerate = true;
            }
        }
    }

    public double[] getAngles() {
        log.info("Getting angles of triangle");
        double[] angles = new double[]{0.0, 0.0, 0.0};
        for (int i = 0; i < 3; ++i) {
            angles[i] = cosTheorem(sides[i], sides[(i + 1) % 3], sides[(i + 2) % 3]);
        }
        return angles;
    }

    private double cosTheorem(double a, double b, double c) {
        return Math.toDegrees(Math.acos((b * b + c * c - a * a) / (2 * b * c)));
    }

    @Override
    protected String getUniqueInfo() {
        log.info("Getting unique info about triangle");
        double[] angles = getAngles();
        StringBuilder sb = new StringBuilder(200);
        for (int i = 0; i < 3; ++i) {
            sb.append(DisplayedMessages.SIDE.getMsg())
                    .append(Precision.round(sides[i], 2))
                    .append(DisplayedMessages.ANGLE.getMsg())
                    .append(Precision.round(angles[i], 2))
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public double getArea() {
        log.info("Getting area of triangle");
        if (degenerate) {
            return 0.0;
        }
        double p = getPerimeter() / 2;
        return Math.sqrt(p * (p - sides[0]) * (p - sides[1]) * (p - sides[2]));
    }

    @Override
    public double getPerimeter() {
        log.info("Getting perimeter of triangle");
        if (degenerate) {
            if (Arrays.stream(sides).max().isPresent()) {
                return Arrays.stream(sides).max().getAsDouble();
            } else {
                throw new IllegalArgumentException("Max side doesn't exist!");
            }
        }
        return sides[0] + sides[1] + sides[2];
    }
}
