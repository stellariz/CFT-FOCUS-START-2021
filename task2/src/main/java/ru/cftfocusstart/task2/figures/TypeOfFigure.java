package ru.cftfocusstart.task2.figures;

public enum TypeOfFigure {

    CIRCLE("Круг"),
    RECTANGLE("Прямоугольник"),
    TRIANGLE("Треугольник");

    private final String name;

    TypeOfFigure(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
