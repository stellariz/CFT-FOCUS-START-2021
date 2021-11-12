package ru.cftfocusstart.task3.model;

import java.util.ArrayList;
import java.util.List;

public class CellMatrix {
    private final Cell[][] matrix;

    public CellMatrix() {
        matrix = new Cell[ConfigField.getWidth()][ConfigField.getLength()];
        for (int j = 0; j < ConfigField.getWidth(); ++j) {
            for (int i = 0; i < ConfigField.getLength(); ++i) {
                matrix[j][i] = new Cell(i, j);
                matrix[j][i].setCellState(CellState.ZERO);
                matrix[j][i].setViewCellState(ViewCellState.CLOSED);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return matrix[y][x];
    }

    public void setCellState(int x, int y, CellState cellState) {
        matrix[x][y].setCellState(cellState);
    }

    public List<Cell> getCellsAround(Cell centerCell) {
        List<Cell> list = new ArrayList<>();
        for (int j = centerCell.getY() - 1; j <= centerCell.getY() + 1; ++j) {
            for (int i = centerCell.getX() - 1; i <= centerCell.getX() + 1; ++i) {
                if (checkInRange(i,j)) {
                    list.add(getCell(i,j));
                }
            }
        }
        return list;
    }

    private boolean checkInRange(int x, int y) {
        return x >= 0 && x < ConfigField.getLength() &&
               y >= 0 &&y < ConfigField.getWidth();
    }
}
