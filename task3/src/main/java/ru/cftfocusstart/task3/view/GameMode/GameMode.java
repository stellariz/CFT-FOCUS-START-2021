package ru.cftfocusstart.task3.view.GameMode;

import ru.cftfocusstart.task3.model.Game.Game;
import ru.cftfocusstart.task3.model.Game.GameType;
import ru.cftfocusstart.task3.model.Field.PreGameState;
import ru.cftfocusstart.task3.view.Windows.MainWindow;

public abstract class GameMode {
    protected GameType gameType;

    public GameMode(Game game, MainWindow mainWindow) {
        game.getField().setNewGameState(new PreGameState(game, mainWindow));
        mainWindow.repaint();
        mainWindow.pack();
    }

    public GameType getGameType() {
        return gameType;
    }
}
