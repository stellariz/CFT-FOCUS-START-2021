package ru.cftfocusstart.task3.view.GameRestart;

import ru.cftfocusstart.task3.Game.Game;
import ru.cftfocusstart.task3.Game.GameType;
import ru.cftfocusstart.task3.model.Field.ConfigField;
import ru.cftfocusstart.task3.view.Windows.MainWindow;

public class HardGameMode extends GameMode {
    public HardGameMode(Game game, MainWindow mainWindow) {
        super(game, mainWindow);
        ConfigField.setSizeOfField(16, 30);
        ConfigField.setTotalBombs(99);
        gameType = GameType.EXPERT;
        updateView();
    }
}
