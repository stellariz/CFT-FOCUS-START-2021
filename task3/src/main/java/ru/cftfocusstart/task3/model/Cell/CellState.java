package ru.cftfocusstart.task3.model.Cell;

public enum CellState {
    ZERO,
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    BOMB,
    ;

    public CellState getNextState() {
        return CellState.values()[this.ordinal() + 1];
    }

    public int getValue() {
        return this.ordinal();
    }
}
