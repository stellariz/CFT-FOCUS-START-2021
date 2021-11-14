package ru.cftfocusstart.task3.model.Field;

import ru.cftfocusstart.task3.Game.Game;
import ru.cftfocusstart.task3.Game.GameState;
import ru.cftfocusstart.task3.model.Cell.Cell;
import ru.cftfocusstart.task3.view.Windows.LoseWindow;
import ru.cftfocusstart.task3.view.Windows.MainWindow;
import ru.cftfocusstart.task3.view.Windows.WinWindow;

public class PlayingGameState implements GameStateInterface {
    private final Game game;
    private final MainWindow mainWindow;

    public PlayingGameState(Game game, MainWindow mainWindow) {
        this.game = game;
        this.mainWindow = mainWindow;
        game.setGameState(GameState.PLAYING);
    }

    @Override
    public void onChangingGameState() {
        if (!game.isFieldExploded()) {
            WinningGameState winningGameState = new WinningGameState(game, mainWindow);
            game.getField().setNewGameState(winningGameState);
            WinWindow winWindow = new WinWindow(mainWindow);
            winWindow.setNewGameListener(e -> winningGameState.onChangingGameState());
            winWindow.setVisible(true);
        } else {
            LosingGameState losingGameState = new LosingGameState(game, mainWindow);
            game.getField().setNewGameState(losingGameState);
            LoseWindow loseWindow = new LoseWindow(mainWindow);
            loseWindow.setNewGameListener(e -> losingGameState.onChangingGameState());
            loseWindow.setVisible(true);
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
