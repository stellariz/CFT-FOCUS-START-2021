package ru.cftfocusstart.task3.app;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task3.Game.Game;
import ru.cftfocusstart.task3.Game.GameType;
import ru.cftfocusstart.task3.view.ClickProcessing.FieldEventListener;
import ru.cftfocusstart.task3.view.GameRestart.NewGameTypeListener;
import ru.cftfocusstart.task3.view.GameRestart.NoviceGameMode;
import ru.cftfocusstart.task3.view.Windows.HighScoresWindow;
import ru.cftfocusstart.task3.view.Windows.MainWindow;
import ru.cftfocusstart.task3.view.Windows.SettingsWindow;

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
        NewGameTypeListener newGameTypeListener = new NewGameTypeListener(game, mainWindow);

        game.setGameMode(new NoviceGameMode(game, mainWindow));
        game.setFieldListener(new FieldEventListener(game.getField(), mainWindow));

        mainWindow.setNewGameMenuAction(e -> newGameTypeListener.onGameTypeChanged(GameType.NOVICE));
        mainWindow.setSettingsMenuAction(e -> settingsWindow.setVisible(true));
        mainWindow.setHighScoresMenuAction(e -> highScoresWindow.setVisible(true));
        mainWindow.setExitMenuAction(e -> mainWindow.dispose());

        settingsWindow.setGameTypeListener(newGameTypeListener);

        mainWindow.setVisible(true);
    }
}
