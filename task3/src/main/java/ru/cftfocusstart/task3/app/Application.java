package ru.cftfocusstart.task3.app;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task3.model.Game.Game;
import ru.cftfocusstart.task3.model.Game.GameType;
import ru.cftfocusstart.task3.model.Game.GameTimer;
import ru.cftfocusstart.task3.model.Game.TimerListener;
import ru.cftfocusstart.task3.model.Field.FieldEventListener;
import ru.cftfocusstart.task3.view.GameMode.NewGameTypeListener;
import ru.cftfocusstart.task3.view.GameMode.NoviceGameMode;
import ru.cftfocusstart.task3.model.Game.RecordsTable;
import ru.cftfocusstart.task3.model.Game.RecordsListener;
import ru.cftfocusstart.task3.view.Windows.HighScoresWindow;
import ru.cftfocusstart.task3.view.Windows.MainWindow;
import ru.cftfocusstart.task3.view.Windows.SettingsWindow;

@Slf4j
public class Application {
    public static void main(String[] args) {
        log.debug("Starting game");
        Game game = new Game();

        log.debug("Creating windows");
        MainWindow mainWindow = new MainWindow(game.getField());
        SettingsWindow settingsWindow = new SettingsWindow(mainWindow);
        HighScoresWindow highScoresWindow = new HighScoresWindow(mainWindow);

        NewGameTypeListener newGameTypeListener = new NewGameTypeListener(game, mainWindow);

        RecordsTable recordsTable = new RecordsTable("task3/src/main/resources/records.txt");
        RecordsListener recordsViewListener = new RecordsListener(game, highScoresWindow, recordsTable);
        recordsTable.setRecordsListener(recordsViewListener);

        GameTimer gameTimer = new GameTimer();
        TimerListener timeListener = new TimerListener(gameTimer, mainWindow);
        gameTimer.setTimeListener(timeListener);

        game.setRecordsTable(recordsTable);
        game.setGameTimer(gameTimer);
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
