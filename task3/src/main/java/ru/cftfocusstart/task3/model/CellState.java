package ru.cftfocusstart.task3.model;

public enum CellState {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    BOMB(-1),
    ;

    private final int value;

    CellState(int value) {
        this.value = value;
    }

    public CellState getNextState() {
        return CellState.values()[this.ordinal() + 1];
    }

    public int getValue() {
        return value;
    }
}
