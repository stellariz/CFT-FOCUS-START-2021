package ru.cftfocusstart.task3.model.Field;

import ru.cftfocusstart.task3.Game.Game;
import ru.cftfocusstart.task3.Game.GameState;
import ru.cftfocusstart.task3.model.Cell.Cell;
import ru.cftfocusstart.task3.view.Windows.MainWindow;

public class PreGameState implements GameStateInterface {
    private final Game game;
    private final MainWindow mainWindow;

    public PreGameState(Game game, MainWindow mainWindow) {
        this.game = game;
        this.mainWindow = mainWindow;
        game.createNewField();
        mainWindow.createGameField();
        game.updateGameState(GameState.PREGAME);
    }

    @Override
    public void onChangingGameState() {
        game.getField().setNewGameState(new PlayingGameState(game, mainWindow));
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
}
