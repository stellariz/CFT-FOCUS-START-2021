package ru.cftfocusstart.task3.view.GameMode;

import ru.cftfocusstart.task3.model.Game.Game;
import ru.cftfocusstart.task3.model.Game.GameType;
import ru.cftfocusstart.task3.view.Windows.MainWindow;

public class MediumGameMode extends GameMode {
    public MediumGameMode(Game game, MainWindow mainWindow) {
        super(game, mainWindow);
        gameType = GameType.MEDIUM;
    }
}
