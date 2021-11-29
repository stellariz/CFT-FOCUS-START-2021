package ru.cftfocusstart.task3.model.Game;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task3.model.Field.ConfigField;
import ru.cftfocusstart.task3.model.Field.Field;
import ru.cftfocusstart.task3.model.Field.FieldEventListener;
import ru.cftfocusstart.task3.model.Field.GameState;
import ru.cftfocusstart.task3.model.GameMode.GameMode;

@Slf4j
public class Game {
    private GameMode gameMode;
    private final Field field;
    private TimerController timerController;
    private RecordsTable recordsTable;
    private GameEventManager eventManager;

    public Game() {
        log.debug("Configure field");
        ConfigField.setSizeOfField(9, 9);
        ConfigField.setTotalBombs(3);
        field = new Field();
    }

    public void createNewField() {
        field.generateNewField();
        field.setNumberOfOpenCells(0);
        field.setExploded(false);
        field.resetTotalFlags();
    }

    public void setGameState(GameState gameState) {
        field.setNewGameState(gameState);
        eventManager.notify(gameState.toString());
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public void setFieldListener(FieldEventListener fieldListener) {
        field.setFieldListener(fieldListener);
    }

    public void setEventManager(GameEventManager eventManager) {
        this.eventManager = eventManager;
    }

    public void setTimerController(TimerController timerController) {
        this.timerController = timerController;
    }

    public void setRecordsTable(RecordsTable recordsTable) {
        this.recordsTable = recordsTable;
    }

    public Field getField() {
        return field;
    }

    public GameType getGameType() {
        return gameMode.getGameType();
    }

    public int getGameDuration() {
        return timerController.getTime();
    }

    public RecordsTable getRecordsTable() {
        return recordsTable;
    }

    public boolean isFieldExploded() {
        return field.isExploded();
    }

    public void startTimer() {
        timerController.startTimer();
    }

    public void stopTimer() {
        timerController.stopTimer();
    }

    public void resetTimer() {
        if (timerController != null) {
            timerController.resetTimer();
        }
    }

    public void isNewRecord() {
        if (recordsTable.getScoreByType(getGameType()) > getGameDuration()) {
            eventManager.notify("NewWinner");
        }
    }

    public void updateRecord(Player player) {
        recordsTable.updateRecord(getGameType(), player.getName(), player.getTimeRecord());
        recordsTable.uploadFile();
    }
}
