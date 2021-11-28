package ru.cftfocusstart.task3.view;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task3.model.Game.GameType;
import ru.cftfocusstart.task3.model.Game.Player;
import ru.cftfocusstart.task3.model.Game.RecordsListener;
import ru.cftfocusstart.task3.model.GameMode.NewGameTypeListener;
import ru.cftfocusstart.task3.view.Windows.*;

@Slf4j
public class View {
    private final SettingsWindow settingsWindow;
    private final MainWindow mainWindow;
    private final HighScoresWindow highScoresWindow;
    private final LoseWindow loseWindow;
    private final WinWindow winWindow;
    private final RecordsWindow recordsWindow;

    private NewGameTypeListener newGameListener;

    public View(MainWindow mainWindow) {
        log.debug("Creating windows");
        this.mainWindow = mainWindow;
        this.settingsWindow = new SettingsWindow(mainWindow);
        this.highScoresWindow = new HighScoresWindow(mainWindow);
        this.loseWindow = new LoseWindow(mainWindow);
        this.winWindow = new WinWindow(mainWindow);
        this.recordsWindow = new RecordsWindow(mainWindow);
        initMainWindow();
    }

    private void initMainWindow() {
        mainWindow.setSettingsMenuAction(e -> settingsWindow.setVisible(true));
        mainWindow.setHighScoresMenuAction(e -> highScoresWindow.setVisible(true));
        mainWindow.setExitMenuAction(e -> mainWindow.dispose());
    }

    public void drawLoseWindow() {
        LoseWindow loseWindow = new LoseWindow(mainWindow);
        loseWindow.setNewGameListener(e -> {
            redrawField();
            newGameListener.restartGame();
        });
        loseWindow.setVisible(true);
    }

    public void drawWinWindow() {
        WinWindow winWindow = new WinWindow(mainWindow);
        winWindow.setNewGameListener(e -> {
            redrawField();
            newGameListener.restartGame();
        });
        winWindow.setVisible(true);
    }

    public void drawMainWindow() {
        mainWindow.setVisible(true);
    }

    public void updateView() {
        mainWindow.createGameField();
        mainWindow.repaint();
        mainWindow.pack();
    }

    private void redrawField() {
        mainWindow.closeCells();
    }

    public void setNewGameListener(NewGameTypeListener newGameListener) {
        this.newGameListener = newGameListener;
        mainWindow.setNewGameMenuAction(e -> newGameListener.restartGame());
        settingsWindow.setGameTypeListener(newGameListener);
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public void drawRecordsWindow(RecordsListener recordsListener) {
        recordsWindow.setNameListener(recordsListener);
        recordsWindow.setVisible(true);
    }

    public void updateRecordInTable(GameType gameType, Player player) {
        switch (gameType) {
            case NOVICE:
                highScoresWindow.setNoviceRecord(player.getName(), player.getTimeRecord());
                break;
            case MEDIUM:
                highScoresWindow.setMediumRecord(player.getName(), player.getTimeRecord());
                break;
            case EXPERT:
                highScoresWindow.setExpertRecord(player.getName(), player.getTimeRecord());
                break;
            default:
                throw new IllegalArgumentException("Unknown game type");
        }
    }
}
