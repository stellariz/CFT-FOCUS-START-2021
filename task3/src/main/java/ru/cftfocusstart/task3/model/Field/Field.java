package ru.cftfocusstart.task3.model.Field;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task3.model.Cell.Cell;
import ru.cftfocusstart.task3.model.Cell.CellMatrix;
import ru.cftfocusstart.task3.model.Cell.CellState;
import ru.cftfocusstart.task3.model.Cell.ViewCellState;

import java.util.List;

@Slf4j
public class Field {
    private CellMatrix cellMatrix;

    private FieldEventListener fieldListener;
    private GameState gameState;

    private int totalFlags;
    private int numberOfOpenCells;
    private boolean isExploded;

    public void generateNewField() {
        cellMatrix = new CellMatrix();
    }

    public Cell getCell(int x, int y) {
        return cellMatrix.getCell(x, y);
    }

    public int getBombsWithFlags() {
        return ConfigField.getTotalBombs() - totalFlags;
    }

    public GameState getGameState() {
        return gameState;
    }

    public boolean isExploded() {
        return isExploded;
    }

    public void setFieldListener(FieldEventListener fieldListener) {
        this.fieldListener = fieldListener;
    }

    public void setNewGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setNumberOfOpenCells(int numberOfOpenCells) {
        this.numberOfOpenCells = numberOfOpenCells;
    }

    public void setExploded(boolean exploded) {
        isExploded = exploded;
    }

    public void resetTotalFlags() {
        if (totalFlags != 0) {
            this.totalFlags = 0;
            fieldListener.resetFlags();
        }
    }

    public void generateBombs(Cell firstOpenedCell) {
        log.debug("Generating bombs");
        cellMatrix.generateBombsExcludingCell(firstOpenedCell);
    }

    void markCell(Cell cell) {
        if (cell.getViewCellState() == ViewCellState.CLOSED && totalFlags < ConfigField.getTotalBombs()) {
            log.debug("Marked cell " + cell.getX() + " " + cell.getY());
            cell.setViewCellState(ViewCellState.FLAGGED);
            ++totalFlags;
            fieldListener.updateMarkedCellView(cell);
        }
    }

    void unmarkCell(Cell cell) {
        if (cell.getViewCellState() == ViewCellState.FLAGGED) {
            log.debug("Unmarked cell " + cell.getX() + " " + cell.getY());
            cell.setViewCellState(ViewCellState.CLOSED);
            --totalFlags;
            fieldListener.updateUnmarkedCellView(cell);
        }
    }

    void openCell(Cell openedCell) {
        recursivelyOpenCellsAroundEmptyCell(openedCell);
        if (checkWinCondition()) {
            log.debug("You won!");
            gameState.onChangingGameState();
        }
    }

    private void recursivelyOpenCellsAroundEmptyCell(Cell openedCell) {
        if (openedCell.getViewCellState() == ViewCellState.CLOSED) {
            log.debug("Opened cell " + openedCell.getX() + " " + openedCell.getY());
            if (openedCell.getCellState() == CellState.BOMB) {
                log.debug("BOOM!");
                fieldListener.updateOpenedCellView(openedCell);
                isExploded = true;
                gameState.onChangingGameState();
            } else {
                openedCell.setViewCellState(ViewCellState.OPENED);
                ++numberOfOpenCells;
                fieldListener.updateOpenedCellView(openedCell);
                if (openedCell.getCellState() == CellState.ZERO) {
                    for (Cell curCell : cellMatrix.getCellsAround(openedCell)) {
                        recursivelyOpenCellsAroundEmptyCell(curCell);
                    }
                }
            }
        }
    }

    void openCellsAroundNumber(Cell cell) {
        if (cell.getViewCellState() == ViewCellState.OPENED) {
            int flagsCounterAroundCell = 0;
            List<Cell> cellsAround = cellMatrix.getCellsAround(cell);
            for (Cell curCell : cellsAround) {
                if (curCell.getViewCellState() == ViewCellState.FLAGGED) {
                    ++flagsCounterAroundCell;
                }
            }
            if (cell.getCellState().getValue() == flagsCounterAroundCell) {
                for (Cell curCell : cellsAround) {
                    openCell(curCell);
                }
            }
        }
    }

    private boolean checkWinCondition() {
        log.debug(String.valueOf(isExploded));
        log.debug(String.valueOf(numberOfOpenCells));
        return numberOfOpenCells + ConfigField.getTotalBombs() == ConfigField.getWidth() * ConfigField.getLength();
    }
}
