package ru.cftfocusstart.task2.figures;


import ru.cftfocusstart.task2.utils.LogMessages;

import java.util.Arrays;
import java.util.logging.Logger;


public final class Triangle extends Figure {

    private final double[] sides;

    public Triangle(double[] params) {
        super(TypesOfFigures.TRIANGLE, params);
        countZeroSides(params);
        checkTriangleRule(params);
        sides = Arrays.copyOf(params, params.length);
    }


    @Override
    public void getInfo(Logger logger) {
        super.getInfo(logger);
        double[] angles = getAngles();
        for (int i = 0; i < 3; ++i) {
            logger.info(LogMessages.SIDE.msg + sides[i] + LogMessages.ANGLE.msg + angles[i]);
        }
    }

    @Override
    public double getArea() {
        if (degenerate) {
            return 0.0;
        }
        double p = getPerimeter() / 2;
        return Math.sqrt(p * (p - sides[0]) * (p - sides[1]) * (p - sides[2]));
    }

    @Override
    public double getPerimeter() {
        if (degenerate) {
            if (Arrays.stream(sides).max().isPresent()) {
                return Arrays.stream(sides).max().getAsDouble();
            } else {
                throw new IllegalArgumentException("Max side doesn't exist!");
            }
        }
        return sides[0] + sides[1] + sides[2];
    }

    @Override
    protected void checkParamsSize(double[] args) {
        if (args == null) {
            throw new IllegalArgumentException("Null cannot be passed in arguments!");
        }
        if (args.length != 3) {
            throw new IllegalArgumentException("Incorrect number of parameters for rectangle: " + args.length
                    + ", but should be only three!");
        }
    }

    public double[] getAngles() {
        double[] angles = new double[]{0.0, 0.0, 0.0};
        if (point) {
            return angles;
        }
        for (int i = 0; i < 3; ++i) {
            angles[i] = cosTheorem(sides[i], sides[(i + 1) % 3], sides[(i + 2) % 3]);
        }
        return angles;
    }


    private double cosTheorem(double a, double b, double c) {
        return Math.toDegrees(Math.acos((b * b + c * c - a * a) / (2 * b * c)));
    }


    private void checkTriangleRule(double[] args) {
        for (int i = 0; i < args.length; ++i) {
            if (Double.compare(args[i], args[(i + 1) % 3] + args[(i + 2) % 3]) == 1) {
                throw new IllegalArgumentException("No such triangle exists!");
            }
            if (Double.compare(args[i], args[(i + 1) % 3] + args[(i + 2) % 3]) == 0) {
                degenerate = true;
            }
        }
    }


    private void countZeroSides(double[] args) {
        int counter = 0;
        for (double side : args) {
            if (side == 0.0) {
                ++counter;
            }
        }
        if (counter == 1) {
            throw new IllegalArgumentException("Angle is not a triangle!");
        }
    }
}
