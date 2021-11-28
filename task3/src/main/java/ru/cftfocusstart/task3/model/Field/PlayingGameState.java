package ru.cftfocusstart.task3.model.Field;

import ru.cftfocusstart.task3.model.Cell.Cell;
import ru.cftfocusstart.task3.model.Game.Game;

public class PlayingGameState implements GameState {
    private final Game game;

    public PlayingGameState(Game game) {
        this.game = game;
        game.getGameTimer().startTimer();
    }

    @Override
    public void onChangingGameState() {
        if (!game.isFieldExploded()) {
            game.setGameState(new WinningGameState(game));
        } else {
            game.setGameState(new LosingGameState(game));
        }
    }

    @Override
    public void openCellsAroundNumber(Cell cell) {
        game.getField().openCellsAroundNumber(cell);
    }

    @Override
    public void openCell(Cell cell) {
        game.getField().openCell(cell);
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
        return "PlayingGameState";
    }
}
