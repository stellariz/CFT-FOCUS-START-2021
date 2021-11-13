package ru.cftfocusstart.task3.model.Field;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task3.Game.GameState;
import ru.cftfocusstart.task3.model.Cell.Cell;
import ru.cftfocusstart.task3.model.Cell.CellMatrix;
import ru.cftfocusstart.task3.model.Cell.CellState;
import ru.cftfocusstart.task3.model.Cell.ViewCellState;
import ru.cftfocusstart.task3.view.ClickProcessing.FieldEventListener;
import ru.cftfocusstart.task3.view.GameState.GameStateListener;

import java.util.List;
import java.util.Random;

@Slf4j
public class Field {
    private CellMatrix cellMatrix;

    private FieldEventListener fieldListener;
    private GameStateListener gameStateListener;

    private GameState gameState;
    private int totalFlags;
    private int numberOfOpenCells;
    private boolean isExploded;

    public Field() {
        cellMatrix = new CellMatrix();
    }

    public void generateNewField() {
        cellMatrix = new CellMatrix();
    }

    public Cell getCell(int x, int y) {
        return cellMatrix.getCell(x, y);
    }

    public int getBombsWithFlags() {
        return ConfigField.getTotalBombs() - totalFlags;
    }

    public boolean isExploded() {
        return isExploded;
    }

    public void setFieldListener(FieldEventListener fieldListener) {
        this.fieldListener = fieldListener;
    }

    public void setGameStateListener(GameStateListener gameStateListener) {
        this.gameStateListener = gameStateListener;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setNumberOfOpenCells(int numberOfOpenCells) {
        this.numberOfOpenCells = numberOfOpenCells;
    }

    private void generateBombsExcludingCell(Cell firstOpenedCell) {
        log.debug("Generating bombs");
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
        for (int j = 0; j < ConfigField.getWidth(); ++j) {
            for (int i = 0; i < ConfigField.getLength(); ++i) {
                System.out.print(getCell(i, j).getCellState() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void plantBomb(Cell bombCell) {
        bombCell.setCellState(CellState.BOMB);
        for (Cell cell : cellMatrix.getCellsAround(bombCell)) {
            if (cell.getCellState() != CellState.BOMB) {
                cell.setCellState(cell.getCellState().getNextState());
            }
        }
    }

    public void markCell(Cell cell) {
        if (gameState == GameState.PLAYING || gameState == GameState.PREGAME) {
            if (cell.getViewCellState() == ViewCellState.CLOSED && totalFlags < ConfigField.getTotalBombs()) {
                cell.setViewCellState(ViewCellState.FLAGGED);
                ++totalFlags;
                fieldListener.updateMarkCellView(cell);
            }
            if (checkWinCondition()) {
                log.debug("You won!");
                gameStateListener.onChangingGameState();
            }
        }
    }

    public void unmarkCell(Cell cell) {
        if (gameState == GameState.PLAYING || gameState == GameState.PREGAME) {
            if (cell.getViewCellState() == ViewCellState.FLAGGED) {
                cell.setViewCellState(ViewCellState.CLOSED);
                --totalFlags;
                fieldListener.updateUnmarkCellView(cell);
            }
        }
    }

    public void openCell(Cell openedCell) {
        if (gameState == GameState.PREGAME) {
            generateBombsExcludingCell(openedCell);
            gameStateListener.onChangingGameState();
        }
        if (gameState == GameState.PLAYING) {
            recursivelyOpenCellsAround(openedCell);
            if (checkWinCondition()) {
                log.debug("You won!");
                gameStateListener.onChangingGameState();
            }
        }
    }

    private void recursivelyOpenCellsAround(Cell openedCell) {
        if (gameState == GameState.PLAYING) {
            if (openedCell.getViewCellState() == ViewCellState.CLOSED) {
                openedCell.setViewCellState(ViewCellState.OPENED);
                ++numberOfOpenCells;
                fieldListener.viewOpenCell(openedCell);
                if (openedCell.getCellState() == CellState.ZERO) {
                    for (Cell curCell : cellMatrix.getCellsAround(openedCell)) {
                        recursivelyOpenCellsAround(curCell);
                    }
                } else if (openedCell.getCellState() == CellState.BOMB) {
                    log.debug("BOOM!");
                    isExploded = true;
                    gameStateListener.onChangingGameState();
                }
            }
        }
    }

    public void openCellsAroundNumber(Cell cell) {
        if (gameState == GameState.PLAYING) {
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
    }

    private boolean checkWinCondition() {
        log.debug(String.valueOf(isExploded));
        log.debug(String.valueOf(numberOfOpenCells));
        return numberOfOpenCells + ConfigField.getTotalBombs() == ConfigField.getWidth() * ConfigField.getLength();
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
}
