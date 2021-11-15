package ru.cftfocusstart.task3.view.GameMode;

import ru.cftfocusstart.task3.Game.Game;
import ru.cftfocusstart.task3.Game.GameType;
import ru.cftfocusstart.task3.model.Field.PreGameState;
import ru.cftfocusstart.task3.view.Windows.MainWindow;

public abstract class GameMode {
    protected final Game game;
    protected final MainWindow mainWindow;
    protected GameType gameType;

    public GameMode(Game game, MainWindow mainWindow) {
        this.game = game;
        this.mainWindow = mainWindow;
        game.getField().setNewGameState(new PreGameState(game, mainWindow));
        updateView();
    }

    private void updateView() {
        mainWindow.repaint();
        mainWindow.pack();
    }

    public GameType getGameType() {
        return gameType;
    }
}
