package ru.cftfocusstart.task2.figures;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CircleTest {

    private static final double[][] circleRadius = new double[][]{{1.0}, {3.0}, {4.0}, {5.0}};


    @ParameterizedTest
    @MethodSource("methodNegativeParamsProvider")
    void createCircle_throwsIllegalArgumentException_ifNegativeRadius(double[] args) {
        assertThrows(IllegalArgumentException.class, () -> new Circle(args));
    }

    static Stream<Arguments> methodNegativeParamsProvider() {
        return Stream.of(
                Arguments.of(new double[]{-10.0}),
                Arguments.of(new double[]{-1.0})
        );
    }


    @ParameterizedTest
    @NullAndEmptySource
    @MethodSource("methodIncorrectParamsSizeProvider")
    void createCircle_throwsIllegalArgumentException_ifIncorrectParamsSize(double[] args) {
        assertThrows(IllegalArgumentException.class, () -> new Circle(args));
    }

    static Stream<Arguments> methodIncorrectParamsSizeProvider() {
        return Stream.of(
                Arguments.of(new double[]{4.0, 5.0, 6.0}),
                Arguments.of(new double[]{4.0, 5.0})
        );
    }


    @ParameterizedTest
    @MethodSource("methodDataForAreaProvider")
    void getCircleArea(double[] args, double area) {
        assertEquals(area, new Circle(args).getArea());
    }

    static Stream<Arguments> methodDataForAreaProvider() {
        return Stream.of(
                Arguments.of(circleRadius[0], Math.PI),
                Arguments.of(circleRadius[1], 9 * Math.PI),
                Arguments.of(circleRadius[2], 16 * Math.PI),
                Arguments.of(circleRadius[3], 25 * Math.PI)
        );
    }


    @ParameterizedTest
    @MethodSource("methodDataForPerimeterProvider")
    void getCirclePerimeter(double[] args, double perimeter) {
        assertEquals(perimeter, new Circle(args).getPerimeter());
    }

    static Stream<Arguments> methodDataForPerimeterProvider() {
        return Stream.of(
                Arguments.of(circleRadius[0], 2 * Math.PI),
                Arguments.of(circleRadius[1], 6 * Math.PI),
                Arguments.of(circleRadius[2], 8 * Math.PI),
                Arguments.of(circleRadius[3], 10 * Math.PI)
        );
    }


    @ParameterizedTest
    @MethodSource("methodDataForRadiusAndDiameterProvider")
    void getCircleRadiusAndDiameter(double[] args, double radius, double diameter) {
        Circle circle = new Circle(args);
        assertEquals(radius, circle.getRadius());
        assertEquals(diameter, circle.getDiameter());
    }

    static Stream<Arguments> methodDataForRadiusAndDiameterProvider() {
        return Stream.of(
                Arguments.of(circleRadius[0], 1.0, 2.0),
                Arguments.of(circleRadius[1], 3.0, 6.0),
                Arguments.of(circleRadius[2], 4.0, 8.0),
                Arguments.of(circleRadius[3], 5.0, 10.0)
        );
    }
}
