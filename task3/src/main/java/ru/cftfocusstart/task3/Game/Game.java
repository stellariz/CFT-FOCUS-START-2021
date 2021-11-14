package ru.cftfocusstart.task3.Game;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task3.model.Field.ConfigField;
import ru.cftfocusstart.task3.model.Field.Field;
import ru.cftfocusstart.task3.view.ClickProcessing.FieldEventListener;
import ru.cftfocusstart.task3.view.GameMode.GameMode;
import ru.cftfocusstart.task3.model.Field.GameStateInterface;

@Slf4j
public class Game {
    private GameState gameState;
    private GameMode gameMode;

    private final Field field;

    public Game() {
        log.debug("Configuration field");
        ConfigField.setSizeOfField(9, 9);
        ConfigField.setTotalBombs(10);
        field = new Field();
    }

    public Field getField() {
        return field;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
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
}
