package ru.cftfocusstart.task3.view.GameMode;

import ru.cftfocusstart.task3.Game.Game;
import ru.cftfocusstart.task3.Game.GameType;
import ru.cftfocusstart.task3.view.Windows.MainWindow;

public class HardGameMode extends GameMode {
    public HardGameMode(Game game, MainWindow mainWindow) {
        super(game, mainWindow);
        gameType = GameType.EXPERT;
    }
}
