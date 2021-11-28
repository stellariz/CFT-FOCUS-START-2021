package ru.cftfocusstart.task3.model.Field;

import ru.cftfocusstart.task3.model.Cell.Cell;
import ru.cftfocusstart.task3.model.Game.Game;

public class WinningGameState implements GameState {
    private final Game game;

    public WinningGameState(Game game) {
        this.game = game;
        game.getGameTimer().stopTimer();
        game.isNewRecord();
    }

    @Override
    public void onChangingGameState() {
        game.setGameState(new PreGameState(game));
    }

    @Override
    public void openCellsAroundNumber(Cell cell) {
    }

    @Override
    public void openCell(Cell cell) {
    }

    @Override
    public void markCell(Cell cell) {
    }

    @Override
    public void unmarkCell(Cell cell) {
    }

    @Override
    public String toString() {
        return "WinningGameState";
    }
}
