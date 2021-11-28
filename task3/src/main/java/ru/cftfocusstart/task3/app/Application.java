package ru.cftfocusstart.task3.app;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task3.model.Field.FieldEventListener;
import ru.cftfocusstart.task3.model.Game.*;
import ru.cftfocusstart.task3.model.GameMode.NewGameTypeListener;
import ru.cftfocusstart.task3.model.GameMode.NoviceGameMode;
import ru.cftfocusstart.task3.view.View;
import ru.cftfocusstart.task3.view.Windows.MainWindow;

@Slf4j
public class Application {
    public static void main(String[] args) {
        log.debug("Starting game");
        Game game = new Game();
        RecordsListener recordsViewListener = new RecordsListener(game);

        View view = new View(new MainWindow(game.getField()));

        GameEventManager gameStateManager = new GameEventManager("PreGameState",
                "PlayingGameState",
                "LosingGameState",
                "WinningGameState",
                "NewWinner");
        PreGameListener preGameListener = new PreGameListener(view);
        PlayingGameStateListener playingGameStateListener = new PlayingGameStateListener();
        LosingGameStateListener losingGameStateListener = new LosingGameStateListener(view);
        WinningGameStateListener winningGameStateListener = new WinningGameStateListener(view);
        WinnerListener winnerListener = new WinnerListener(view, recordsViewListener);
        gameStateManager.subscribe("PreGameState", preGameListener);
        gameStateManager.subscribe("PlayingGameState", playingGameStateListener);
        gameStateManager.subscribe("LosingGameState", losingGameStateListener);
        gameStateManager.subscribe("WinningGameState", winningGameStateListener);
        gameStateManager.subscribe("NewWinner", winnerListener);

        NewGameTypeListener newGameTypeListener = new NewGameTypeListener(game);
        view.setNewGameListener(newGameTypeListener);

        TableWinnerUpdater tableWinnerUpdater = new TableWinnerUpdater(view);
        RecordsTable recordsTable = new RecordsTable("task3/src/main/resources/records.txt");
        recordsTable.setTableEventListener(tableWinnerUpdater);

        GameTimer gameTimer = new GameTimer();
        TimerListener timeListener = new TimerListener(gameTimer, view.getMainWindow());
        gameTimer.setTimeListener(timeListener);

        game.setFieldListener(new FieldEventListener(game.getField(), view.getMainWindow()));
        game.setRecordsTable(recordsTable);
        game.setEventManager(gameStateManager);
        game.setGameMode(new NoviceGameMode(game));
        game.setGameTimer(gameTimer);
    }
}
