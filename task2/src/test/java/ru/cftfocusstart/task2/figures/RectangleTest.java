package ru.cftfocusstart.task2.figures;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RectangleTest {

    private static final double[][] rectangleSides = {{4.0, 5.0}, {5.0, 4.0}, {4.0, 4.0}, {5.0, 0.0}, {0.0, 0.0}};

    @ParameterizedTest
    @MethodSource("methodNegativeParamsProvider")
    void createRectangle_throwsIllegalArgumentException_ifNegativeSides(double[] args) {
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(args));
    }

    static Stream<Arguments> methodNegativeParamsProvider() {
        return Stream.of(
                Arguments.of(new double[]{-3.0, 1.0}),
                Arguments.of(new double[]{3.0, -1.0}),
                Arguments.of(new double[]{-3.0, -1.0})
        );
    }


    @ParameterizedTest
    @NullAndEmptySource
    @MethodSource("methodIncorrectParamsSizeProvider")
    void createRectangle_throwsIllegalArgumentException_ifIncorrectParamsSize(double[] args) {
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(args));
    }

    static Stream<Arguments> methodIncorrectParamsSizeProvider() {
        return Stream.of(
                Arguments.of(new double[]{4.0, 5.0, 6.0}),
                Arguments.of(new double[]{4.0})
        );
    }


    @ParameterizedTest
    @MethodSource("methodDataForAreaProvider")
    void getRectangleArea(double[] args, double area) {
        assertEquals(area, new Rectangle(args).getArea());
    }


    static Stream<Arguments> methodDataForAreaProvider() {
        return Stream.of(
                Arguments.of(rectangleSides[0], 20.0),
                Arguments.of(rectangleSides[1], 20.0),
                Arguments.of(rectangleSides[2], 16.0),
                Arguments.of(rectangleSides[3], 0.0),
                Arguments.of(rectangleSides[4], 0.0)
        );
    }

    @ParameterizedTest
    @MethodSource("methodDataForPerimeterProvider")
    void getRectanglePerimeter(double[] args, double perimeter) {
        assertEquals(perimeter, new Rectangle(args).getPerimeter());
    }


    static Stream<Arguments> methodDataForPerimeterProvider() {
        return Stream.of(
                Arguments.of(rectangleSides[0], 18.0),
                Arguments.of(rectangleSides[1], 18.0),
                Arguments.of(rectangleSides[2], 16.0),
                Arguments.of(rectangleSides[3], 5.0),
                Arguments.of(rectangleSides[4], 0.0)
        );
    }


    @ParameterizedTest
    @MethodSource("methodDataForShortAndLongSideProvider")
    void getRectangleShortAndLongSide(double[] args, double longSide, double shortSide) {
        Rectangle rectangle = new Rectangle(args);
        assertEquals(longSide, rectangle.getLongSide());
        assertEquals(shortSide, rectangle.getShortSide());
    }


    static Stream<Arguments> methodDataForShortAndLongSideProvider() {
        return Stream.of(
                Arguments.of(rectangleSides[0], 5.0, 4.0),
                Arguments.of(rectangleSides[1], 5.0, 4.0),
                Arguments.of(rectangleSides[2], 4.0, 4.0),
                Arguments.of(rectangleSides[3], 5.0, 0.0),
                Arguments.of(rectangleSides[4], 0.0, 0.0)
        );
    }


    @ParameterizedTest
    @MethodSource("methodDataForDiagonalLengthProvider")
    void getRectangleDiagonalLength(double[] args, double diagonalLength) {
        assertEquals(diagonalLength, new Rectangle(args).getLengthDiagonal());
    }


    static Stream<Arguments> methodDataForDiagonalLengthProvider() {
        return Stream.of(
                Arguments.of(rectangleSides[0], Math.sqrt(41)),
                Arguments.of(rectangleSides[1], Math.sqrt(41)),
                Arguments.of(rectangleSides[2], Math.sqrt(32)),
                Arguments.of(rectangleSides[3], 0.0),
                Arguments.of(rectangleSides[4], 0.0)
        );
    }
}