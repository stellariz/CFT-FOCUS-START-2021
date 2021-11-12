package ru.cftfocusstart.task3.Game;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task3.model.Field.ConfigField;
import ru.cftfocusstart.task3.model.Field.Field;
import ru.cftfocusstart.task3.view.GameRestart.GameRestarter;
import ru.cftfocusstart.task3.view.GameState.GameStateListener;

@Slf4j
public class Game {
    private GameState gameState;
    private GameType gameType;

    private final Field field;
    private GameRestarter gameRestarter;
    private GameStateListener gameStateListener;

    public Game() {
        log.debug("Configuration field");
        ConfigField.setSizeOfField(9, 9);
        ConfigField.setTotalBombs(10);
        field = new Field();
        this.gameState = GameState.PLAYING;
        this.gameType = GameType.NOVICE;
    }

    public Field getField() {
        return field;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameRestarter(GameRestarter gameRestarter) {
        this.gameRestarter = gameRestarter;
    }

    public void setGameStateListener(GameStateListener gameStateListener) {
        this.gameStateListener = gameStateListener;
    }

    public void restartNewGame() {
        gameRestarter.restartGame();
        generateField();
    }

    public void updateState() {
        gameStateListener.onChangingGameState();
    }

    public void generateField() {
        gameState = GameState.PLAYING;
        field.generateNewField();
    }
}
