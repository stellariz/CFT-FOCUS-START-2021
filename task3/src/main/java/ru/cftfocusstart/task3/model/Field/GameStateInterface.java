package ru.cftfocusstart.task3.model.Field;

import ru.cftfocusstart.task3.model.Cell.Cell;

public interface GameStateInterface {
    void onChangingGameState();
    void openCellsAroundNumber(Cell cell);
    void openCell(Cell cell);
    void markCell(Cell cell);
    void unmarkCell(Cell cell);
}
