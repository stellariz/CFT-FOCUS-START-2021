package ru.cftfocusstart.task2.figures;

public enum TypesOfFigures {
    CIRCLE("Круг"),
    RECTANGLE("Прямоугольник"),
    TRIANGLE("Треугольник");

    public final String name;

    TypesOfFigures(String name) {
        this.name = name;
    }
}
