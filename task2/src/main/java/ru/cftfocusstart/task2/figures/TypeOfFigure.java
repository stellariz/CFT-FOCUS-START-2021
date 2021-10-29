package ru.cftfocusstart.task2.figures;

public enum TypeOfFigure {

    CIRCLE("Круг"),
    RECTANGLE("Прямоугольник"),
    TRIANGLE("Треугольник");

    public final String name;

    TypeOfFigure(String name) {
        this.name = name;
    }
}
