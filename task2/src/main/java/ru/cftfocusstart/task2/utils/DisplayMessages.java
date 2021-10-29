package ru.cftfocusstart.task2.utils;

public enum DisplayMessages {

    FIGURE_TYPE("Тип фигуры: "),
    AREA("Площадь: "),
    PERIMETER("Периметр: "),
    RADIUS("Радиус: "),
    DIAMETER("Диаметр: "),
    DIAGONAL("Длина диагонали: "),
    SHORT_SIDE("Длина короткой стороны: "),
    LONG_SIDE("Длина длинной стороны: "),
    SIDE("Сторона: "),
    ANGLE(", противолежащий угол: ");

    public final String msg;
    DisplayMessages(String msg){
        this.msg = msg;
    }
}
