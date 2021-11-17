package ru.cftfocusstart.task3.model.Cell;

import ru.cftfocusstart.task3.model.Field.ConfigField;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public List<Cell> getCellsAround(Cell centerCell) {
        List<Cell> list = new ArrayList<>();
        for (int j = centerCell.getY() - 1; j <= centerCell.getY() + 1; ++j) {
            for (int i = centerCell.getX() - 1; i <= centerCell.getX() + 1; ++i) {
                if (checkInRange(i, j)) {
                    list.add(getCell(i, j));
                }
            }
        }
        return list;
    }

    public void generateBombsExcludingCell(Cell firstOpenedCell) {
        Random random = new Random();
        for (int i = 0; i < ConfigField.getTotalBombs(); ++i) {
            while (true) {
                int x = random.nextInt(ConfigField.getLength());
                int y = random.nextInt(ConfigField.getWidth());
                Cell chosenCell = getCell(x, y);
                if (chosenCell != firstOpenedCell && chosenCell.getCellState() != CellState.BOMB) {
                    plantBomb(chosenCell);
                    break;
                }
            }
        }
    }

    private void plantBomb(Cell bombCell) {
        bombCell.setCellState(CellState.BOMB);
        for (Cell cell : getCellsAround(bombCell)) {
            if (cell.getCellState() != CellState.BOMB) {
                cell.setCellState(cell.getCellState().getNextState());
            }
        }
    }

    private boolean checkInRange(int x, int y) {
        return x >= 0 && x < ConfigField.getLength() &&
                y >= 0 && y < ConfigField.getWidth();
    }
}
