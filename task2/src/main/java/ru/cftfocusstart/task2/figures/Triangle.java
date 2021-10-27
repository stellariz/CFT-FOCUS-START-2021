package ru.cftfocusstart.task2.figures;


import org.apache.commons.math3.util.Precision;
import ru.cftfocusstart.task2.utils.LogMessages;

import java.util.Arrays;

public final class Triangle extends Figure {

    private final double[] sides;

    public Triangle(double[] params) {
        super(TypesOfFigures.TRIANGLE, params);
        checkIfAngle(params);
        checkTriangleRule(params);
        sides = Arrays.copyOf(params, params.length);
    }


    @Override
    protected String getUniqueInfo() {
        double[] angles = getAngles();
        StringBuilder sb = new StringBuilder(200).append(System.lineSeparator());
        for (int i = 0; i < 3; ++i) {
            sb.append(LogMessages.SIDE.msg).append(Precision.round(sides[i], 2)).
                    append(LogMessages.ANGLE.msg).append(Precision.round(angles[i], 2)).append(System.lineSeparator());
        }
        return sb.toString();
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


    private void checkIfAngle(double[] args) {
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
