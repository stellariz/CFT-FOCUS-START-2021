package ru.cftfocusstart.task3.model.Field;

import ru.cftfocusstart.task3.model.Cell.Cell;
import ru.cftfocusstart.task3.model.Game.Game;

public class PreGameState implements GameState {
    private final Game game;

    public PreGameState(Game game) {
        this.game = game;
        game.resetTimer();
        game.createNewField();
        if (game.getRecordsTable().getTableOfRecords() == null) {
            game.getRecordsTable().initTable();
        }
    }

    @Override
    public void onChangingGameState() {
        game.setGameState(new PlayingGameState(game));
    }

    @Override
    public void openCellsAroundNumber(Cell cell) {
    }

    @Override
    public void openCell(Cell cell) {
        game.getField().generateBombs(cell);
        game.getField().openCell(cell);
        onChangingGameState();
    }

    @Override
    public void markCell(Cell cell) {
        game.getField().markCell(cell);
    }

    @Override
    public void unmarkCell(Cell cell) {
        game.getField().unmarkCell(cell);
    }

    @Override
    public String toString() {
        return "PreGameState";
    }
}
