package ru.cftfocusstart.task3.model.Field;

import ru.cftfocusstart.task3.model.Game.Game;
import ru.cftfocusstart.task3.model.Cell.Cell;
import ru.cftfocusstart.task3.view.Windows.LoseWindow;
import ru.cftfocusstart.task3.view.Windows.MainWindow;

public class LosingGameState implements GameState {
    private final Game game;
    private final MainWindow mainWindow;

    public LosingGameState(Game game, MainWindow mainWindow) {
        this.game = game;
        this.mainWindow = mainWindow;
        game.getGameTimer().stopTimer();
    }

    @Override
    public void onChangingGameState() {
        mainWindow.closeCells();
        game.getField().setNewGameState(new PreGameState(game, mainWindow));
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

    public void createWindow() {
        LoseWindow loseWindow = new LoseWindow(mainWindow);
        loseWindow.setNewGameListener(e -> onChangingGameState());
        loseWindow.setVisible(true);
    }
}
