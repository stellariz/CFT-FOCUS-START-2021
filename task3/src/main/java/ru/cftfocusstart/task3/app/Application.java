package ru.cftfocusstart.task3.app;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task3.model.Game;
import ru.cftfocusstart.task3.view.*;

@Slf4j
public class Application {
    public static void main(String[] args) {
        log.debug("Running app");

        log.debug("Starting game");
        Game game = new Game();

        log.debug("Creating windows");
        MainWindow mainWindow = new MainWindow(game.getField());
        SettingsWindow settingsWindow = new SettingsWindow(mainWindow);
        HighScoresWindow highScoresWindow = new HighScoresWindow(mainWindow);

        NewGameCreator newGameCreator = new NewGameCreator(game, mainWindow);
        NewGameTypeListener newGameTypeListener = new NewGameTypeListener(newGameCreator);

        game.getField().setFieldListener(new FieldEventListener(game.getField(), mainWindow));
        game.getField().setGameStateListener(new GameStateListener(game, newGameCreator));

        mainWindow.setNewGameMenuAction(e -> newGameCreator.restartGame());
        mainWindow.setSettingsMenuAction(e -> settingsWindow.setVisible(true));
        mainWindow.setHighScoresMenuAction(e -> highScoresWindow.setVisible(true));
        mainWindow.setExitMenuAction(e -> mainWindow.dispose());

        settingsWindow.setGameTypeListener(newGameTypeListener);


        mainWindow.createGameField();
        mainWindow.setVisible(true);
    }
}
