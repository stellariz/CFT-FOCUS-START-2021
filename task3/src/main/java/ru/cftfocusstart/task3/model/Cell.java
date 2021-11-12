package ru.cftfocusstart.task3.model;

public class Cell {
    private final int x;
    private final int y;

    private CellState cellState;
    private ViewCellState viewCellState;

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

    public void setViewCellState(ViewCellState newViewCellState) {
        viewCellState = newViewCellState;
    }

    public ViewCellState getViewCellState() {
        return viewCellState;
    }
}
