package ru.cftfocusstart.task3.Game;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task3.model.Field.ConfigField;
import ru.cftfocusstart.task3.model.Field.Field;
import ru.cftfocusstart.task3.model.Timer.GameTimer;
import ru.cftfocusstart.task3.view.ClickProcessing.FieldEventListener;
import ru.cftfocusstart.task3.view.GameMode.GameMode;
import ru.cftfocusstart.task3.view.RecordNameListener;
import ru.cftfocusstart.task3.view.RecordsHolder;

@Slf4j
public class Game {
    private GameMode gameMode;

    private final Field field;
    private GameTimer gameTimer;
    private RecordsHolder recordsHolder;

    public Game() {
        log.debug("Configuration field");
        ConfigField.setSizeOfField(9, 9);
        ConfigField.setTotalBombs(4);
        field = new Field();
    }

    public Field getField() {
        return field;
    }

    public void updateGameState(GameState gameState) {
        log.debug(String.valueOf(gameState));
        switch (gameState) {
            case PREGAME:
                gameTimer.resetTimer();
                break;
            case PLAYING:
                gameTimer.startTimer();
                break;
            case LOSING:
            case WINNING:
                gameTimer.stopTimer();
        }
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

    public void setRecordsHolder(RecordsHolder recordsHolder) {
        this.recordsHolder = recordsHolder;
    }

    public int getTime() {
        return gameTimer.getSeconds();
    }

    public boolean isNewRecord() {
        return recordsHolder.getScoreByType(getGameType()) > getTime();
    }

    public RecordNameListener getRecordNameListener() {
        return recordsHolder.getRecordsViewListener();
    }

    public void updateFileWithRecords() {
        recordsHolder.uploadFile();
    }

}
