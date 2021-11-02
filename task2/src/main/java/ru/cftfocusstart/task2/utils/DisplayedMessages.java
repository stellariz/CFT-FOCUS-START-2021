package ru.cftfocusstart.task2.utils;

public enum DisplayedMessages {

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

    private final String msg;

    DisplayedMessages(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
