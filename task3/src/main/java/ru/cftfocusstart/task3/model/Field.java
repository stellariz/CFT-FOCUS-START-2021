package ru.cftfocusstart.task3.model;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task3.view.FieldListener;

import java.util.Random;

@Slf4j
public class Field {
    private CellMatrix cellMatrix;
    private final int totalBombs;
    private FieldListener fieldListener;
    private int totalFlags;

    public Field(int totalBombs) {
        if (!isCorrectBombsNumber(totalBombs)) {
            throw new IllegalArgumentException("Number of bomber incorrect with number of columns and rows");
        }
        this.totalBombs = totalBombs;
        initField();
    }

    private void initField() {
        cellMatrix = new CellMatrix();
        log.debug("Generating bombs");
        generateBombs();
    }

    public Cell getCell(int x, int y) {
        return cellMatrix.getCell(x, y);
    }

    public int getTotalBombs() {
        return totalBombs;
    }

    private boolean isCorrectBombsNumber(int totalBombs) {
        return totalBombs <= ConfigField.getLength() * ConfigField.getWidth() && totalBombs > 0;
    }

    private void generateBombs() {
        Random random = new Random();
        for (int i = 0; i < totalBombs; ++i) {
            while (true) {
                int x = random.nextInt(ConfigField.getLength());
                int y = random.nextInt(ConfigField.getWidth());
                Cell chosenCell = getCell(x, y);
                if (chosenCell.getCellState() != CellState.BOMB) {
                    plantBomb(chosenCell);
                    break;
                }
            }
        }
    }

    private void plantBomb(Cell bombCell) {
        bombCell.setCellState(CellState.BOMB);
        for (Cell cell : cellMatrix.getCellsAroundWithoutBombs(bombCell)) {
            if (cell.getCellState() != CellState.BOMB) {
                cell.setCellState(cell.getCellState().getNextState());
            }
        }
    }

    public void markCell(Cell cell) {
        if (cell.getViewCellState() == ViewCellState.CLOSED && totalFlags < totalBombs) {
            cell.setViewCellState(ViewCellState.FLAGGED);
            totalFlags++;
            fieldListener.updateMarkCellView(cell);
        }
    }

    public void unmarkCell(Cell cell) {
        if (cell.getViewCellState() == ViewCellState.FLAGGED) {
            cell.setViewCellState(ViewCellState.CLOSED);
            totalFlags--;
            fieldListener.updateUnmarkCellView(cell);
        }
    }

    public void openCell(Cell cell) {
        if (cell.getViewCellState() == ViewCellState.CLOSED) {
            cell.setViewCellState(ViewCellState.OPENED);
            fieldListener.openCell(cell);
            if (cell.getCellState() == CellState.ZERO) {
                for (Cell curCell : cellMatrix.getCellsAroundWithoutBombs(cell)) {
                    openCell(curCell);
                }
            }
        }
    }

    public void setFieldListener(FieldListener fieldListener) {
        this.fieldListener = fieldListener;
    }

    public int getBombsWithFlags() {
        return totalBombs - totalFlags;
    }
}
