package ru.cftfocusstart.task3.app;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task3.model.ConfigField;
import ru.cftfocusstart.task3.model.Game;
import ru.cftfocusstart.task3.view.*;

@Slf4j
public class Application {
    public static void main(String[] args) {
        log.debug("Running app");
        log.debug("Configuration field");
        ConfigField.setSizeOfField(9, 9);

        log.debug("Generating field");
        Game game = new Game(2);

        log.debug("Creating windows");
        MainWindow mainWindow = new MainWindow(game.getField());
        SettingsWindow settingsWindow = new SettingsWindow(mainWindow);
        HighScoresWindow highScoresWindow = new HighScoresWindow(mainWindow);

        game.getField().setFieldListener(new FieldEventListener(game.getField(), mainWindow));
        game.getField().setGameStateListener(new GameStateListener(game, mainWindow));


        mainWindow.setNewGameMenuAction(e -> { /* TODO */ });
        mainWindow.setSettingsMenuAction(e -> settingsWindow.setVisible(true));
        mainWindow.setHighScoresMenuAction(e -> highScoresWindow.setVisible(true));
        mainWindow.setExitMenuAction(e -> mainWindow.dispose());

        mainWindow.createGameField(ConfigField.getWidth(), ConfigField.getLength());
        mainWindow.setVisible(true);
    }
}
