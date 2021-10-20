package ru.cftfocusstart.task2.utils;

public enum LogMessages {

    FIGURE_TYPE("Типы фигуры: "),
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
    LogMessages(String msg){
        this.msg = msg;
    }
}
