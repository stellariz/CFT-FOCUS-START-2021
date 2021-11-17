package ru.cftfocusstart.task3.view.GameMode;

import ru.cftfocusstart.task3.model.Game.Game;
import ru.cftfocusstart.task3.model.Game.GameType;
import ru.cftfocusstart.task3.view.Windows.MainWindow;

public class NoviceGameMode extends GameMode {
    public NoviceGameMode(Game game, MainWindow mainWindow) {
        super(game, mainWindow);
        gameType = GameType.NOVICE;
    }
}
