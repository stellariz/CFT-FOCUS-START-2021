package ru.cftfocusstart.task3.model.Field;

import ru.cftfocusstart.task3.model.Cell.Cell;
import ru.cftfocusstart.task3.model.Game.Game;
import ru.cftfocusstart.task3.view.Windows.MainWindow;

public class PlayingGameState implements GameState {
    private final Game game;
    private final MainWindow mainWindow;

    public PlayingGameState(Game game, MainWindow mainWindow) {
        this.game = game;
        this.mainWindow = mainWindow;
        game.getGameTimer().startTimer();
    }

    @Override
    public void onChangingGameState() {
        if (!game.isFieldExploded()) {
            WinningGameState winningGameState = new WinningGameState(game, mainWindow);
            game.getField().setNewGameState(winningGameState);
            winningGameState.createWindow();
        } else {
            LosingGameState losingGameState = new LosingGameState(game, mainWindow);
            game.getField().setNewGameState(losingGameState);
            losingGameState.createWindow();

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
}
