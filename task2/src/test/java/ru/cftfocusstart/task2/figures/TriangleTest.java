package ru.cftfocusstart.task2.figures;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TriangleTest {

    private static final double[][] normalTriangleSides = new double[][]{{3.0, 4.0, 5.0}, {2.0, 3.0, 5.0}, {5.0, 5.0, 5.0}};


    @ParameterizedTest
    @MethodSource("methodNegativeAndZeroParamsProvider")
    void createTriangle_throwsIllegalArgumentException_ifNegativeOrZeroSides(double[] args) {
        assertThrows(IllegalArgumentException.class, () -> new Circle(args));
    }

    static Stream<Arguments> methodNegativeAndZeroParamsProvider() {
        return Stream.of(
                Arguments.of(new double[]{-1.0, 2.0, 4.0}),
                Arguments.of(new double[]{-1.0, -2.0, 4.0}),
                Arguments.of(new double[]{-1.0, -2.0, -4.0}),
                Arguments.of(new double[]{0.0, 1.0, 5.0}),
                Arguments.of(new double[]{0.0, 0.0, 0.0})
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @MethodSource("methodIncorrectParamsSizeProvider")
    void createTriangle_throwsIllegalArgumentException_ifIncorrectParamsNumber(double[] args) {
        assertThrows(IllegalArgumentException.class, () -> new Triangle(args));
    }

    static Stream<Arguments> methodIncorrectParamsSizeProvider() {
        return Stream.of(
                Arguments.of(new double[]{4.0, 5.0, 6.0, 7.0}),
                Arguments.of(new double[]{4.0, 5.0}),
                Arguments.of(new double[]{4.0})
        );
    }

    @ParameterizedTest
    @MethodSource("methodIncorrectSidesProvider")
    void createTriangle_throwsIllegalArgumentException_ifNotDoneTriangleRule(double[] args) {
        assertThrows(IllegalArgumentException.class, () -> new Triangle(args));
    }

    static Stream<Arguments> methodIncorrectSidesProvider() {
        return Stream.of(
                Arguments.of(new double[]{10.0, 20.0, 500.0}),
                Arguments.of(new double[]{1.0, 3.0, 5.0}),
                Arguments.of(new double[]{0.0, 0.0, 5.0})
        );
    }

    @ParameterizedTest
    @MethodSource("methodDataForAreaProvider")
    void getTriangleArea(double[] args, double area) {
        assertEquals(area, new Triangle(args).getArea());
    }

    static Stream<Arguments> methodDataForAreaProvider() {
        return Stream.of(
                Arguments.of(normalTriangleSides[0], 6.0),
                Arguments.of(normalTriangleSides[1], 0.0),
                Arguments.of(normalTriangleSides[2], 25. / 4 * Math.sqrt(3))
        );
    }

    @ParameterizedTest
    @MethodSource("methodDataForPerimeterProvider")
    void getTrianglePerimeter(double[] args, double perimeter) {
        assertEquals(perimeter, new Triangle(args).getPerimeter());
    }

    static Stream<Arguments> methodDataForPerimeterProvider() {
        return Stream.of(
                Arguments.of(normalTriangleSides[0], 12.0),
                Arguments.of(normalTriangleSides[1], 5.0),
                Arguments.of(normalTriangleSides[2], 15.0)
        );
    }

    @ParameterizedTest
    @MethodSource("methodDataForAnglesProvider")
    void getTriangleAngles(double[] args, double[] angles) {
        double[] myAngles = new Triangle(args).getAngles();
        for (int i = 0; i < 3; ++i) {
            assertEquals(angles[i], myAngles[i]);
        }
    }

    static Stream<Arguments> methodDataForAnglesProvider() {
        return Stream.of(
                Arguments.of(normalTriangleSides[0], new double[]{Math.toDegrees(Math.acos(4.0 / 5.0)),
                        Math.toDegrees(Math.acos(3.0 / 5.0)),
                        90.0}),
                Arguments.of(normalTriangleSides[1], new double[]{0.0, 0.0, 180.0}),
                Arguments.of(normalTriangleSides[2], new double[]{Math.toDegrees(Math.acos(1.5 / 3)),
                        Math.toDegrees(Math.acos(1.5 / 3)),
                        Math.toDegrees(Math.acos(1.5 / 3))})
        );
    }
}
