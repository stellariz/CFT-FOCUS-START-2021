package ru.cftfocusstart.task3.model.Game;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task3.model.Field.ConfigField;
import ru.cftfocusstart.task3.model.Field.Field;
import ru.cftfocusstart.task3.model.Field.FieldEventListener;
import ru.cftfocusstart.task3.view.GameMode.GameMode;

@Slf4j
public class Game {
    private GameMode gameMode;
    private final Field field;
    private GameTimer gameTimer;
    private RecordsTable recordsTable;

    public Game() {
        log.debug("Configure field");
        ConfigField.setSizeOfField(9, 9);
        ConfigField.setTotalBombs(10);
        field = new Field();
    }

    public Field getField() {
        return field;
    }

    public GameType getGameType() {
        return gameMode.getGameType();
    }

    public void createNewField() {
        field.generateNewField();
        field.setNumberOfOpenCells(0);
        field.setExploded(false);
        field.resetTotalFlags();
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public boolean isFieldExploded() {
        return field.isExploded();
    }

    public void setFieldListener(FieldEventListener fieldListener) {
        field.setFieldListener(fieldListener);
    }

    public void setGameTimer(GameTimer gameTimer) {
        this.gameTimer = gameTimer;
    }

    public void setRecordsTable(RecordsTable recordsTable) {
        this.recordsTable = recordsTable;
    }

    public GameTimer getGameTimer() {
        return gameTimer;
    }

    public int getGameDuration() {
        return gameTimer.getSeconds();
    }

    public boolean isNewRecord() {
        return recordsTable.getScoreByType(getGameType()) > getGameDuration();
    }

    public RecordsTable getRecordsTable() {
        return recordsTable;
    }
}
