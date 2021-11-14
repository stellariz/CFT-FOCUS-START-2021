package ru.cftfocusstart.task3.view.GameMode;

import ru.cftfocusstart.task3.Game.Game;
import ru.cftfocusstart.task3.Game.GameType;
import ru.cftfocusstart.task3.model.Field.ConfigField;
import ru.cftfocusstart.task3.view.Windows.MainWindow;

public class MediumGameMode extends GameMode {
    public MediumGameMode(Game game, MainWindow mainWindow) {
        super(game, mainWindow);
        ConfigField.setSizeOfField(16, 16);
        ConfigField.setTotalBombs(40);
        gameType = GameType.MEDIUM;
        updateView();
    }
}
