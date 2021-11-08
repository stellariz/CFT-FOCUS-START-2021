package ru.cftfocusstart.task3.model;

public class Cell {
    private final int x;
    private final int y;
    boolean flagged;
    private CellState cellState;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setFlag(boolean state) {
        flagged = state;
    }

    public boolean getFlag() {
        return flagged;
    }
}
