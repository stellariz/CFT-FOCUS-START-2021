package ru.cftfocusstart.task3.app;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task3.model.Field.FieldEventListener;
import ru.cftfocusstart.task3.model.Game.*;
import ru.cftfocusstart.task3.model.GameMode.NewGameCreator;
import ru.cftfocusstart.task3.model.GameMode.NoviceGameMode;
import ru.cftfocusstart.task3.view.GameEventListeners.*;
import ru.cftfocusstart.task3.view.View;
import ru.cftfocusstart.task3.view.Windows.MainWindow;

@Slf4j
public class Application {
    public static void main(String[] args) {
        try {
            log.debug("Starting game");
            Game game = new Game();
            RecordsListener recordsViewListener = new RecordsListener(game);

            View view = new View(new MainWindow(game.getField()));
            view.setNewGameListener(new NewGameCreator(game));

            GameEventManager gameStateManager = new GameEventManager("PreGameState",
                    "PlayingGameState",
                    "LosingGameState",
                    "WinningGameState",
                    "NewWinner");
            PreGameStateListener preGameStateListener = new PreGameStateListener(view);
            PlayingGameStateListener playingGameStateListener = new PlayingGameStateListener();
            LosingGameStateListener losingGameStateListener = new LosingGameStateListener(view);
            WinningGameStateListener winningGameStateListener = new WinningGameStateListener(view);
            NewRecordListener winnerListener = new NewRecordListener(view, recordsViewListener);
            gameStateManager.subscribe("PreGameState", preGameStateListener);
            gameStateManager.subscribe("PlayingGameState", playingGameStateListener);
            gameStateManager.subscribe("LosingGameState", losingGameStateListener);
            gameStateManager.subscribe("WinningGameState", winningGameStateListener);
            gameStateManager.subscribe("NewWinner", winnerListener);

            RecordsTable recordsTable = new RecordsTable("task3/src/main/resources/records.txt");
            recordsTable.setTableEventListener(new TableWinnerUpdater(view));

            game.setFieldListener(new FieldEventListener(game.getField(), view.getMainWindow()));
            game.setRecordsTable(recordsTable);
            game.setEventManager(gameStateManager);
            game.setGameMode(new NoviceGameMode(game));

            GameTimer gameTimer = new GameTimer();
            gameTimer.setTimeListener(new TimerListener(gameTimer, view.getMainWindow()));
            game.setTimerController(new GameTimerController(gameTimer));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
