package ru.cftfocusstart.task2.figures;


import ru.cftfocusstart.task2.utils.LogMessages;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public final class Triangle extends Figure {

    private final double[] sides;

    public Triangle(double[] params) {
        super(TypesOfFigures.TRIANGLE);
        sides = Arrays.copyOf(params, params.length);
    }


    @Override
    public void getInfo(Logger logger) {
        super.getInfo(logger);
        Map<Double, Double> sidesAndAngles = countAngles();
        for (var k : sidesAndAngles.entrySet()) {
            logger.info(LogMessages.SIDE.msg + k.getKey() + LogMessages.ANGLE.msg + k.getValue());
        }
    }

    @Override
    public double getArea() {
        double p = getPerimeter() / 2;
        return Math.sqrt(p * (p - sides[0]) * (p - sides[1]) * (p - sides[2]));
    }

    @Override
    public double getPerimeter() {
        return sides[0] + sides[1] + sides[2];
    }

    private Map<Double, Double> countAngles() {
        Map<Double, Double> sideAndAngle = new HashMap<>();
        for (int i = 0; i < 3; ++i) {
            sideAndAngle.put(sides[i], theoremCos(sides[i], sides[(i + 1) % 3], sides[(i + 2) % 3]));
        }
        return sideAndAngle;
    }

    private double theoremCos(double a, double b, double c) {
        return Math.toDegrees(Math.acos((b * b + c * c - a * a) / (2 * b * c)));
    }

}
