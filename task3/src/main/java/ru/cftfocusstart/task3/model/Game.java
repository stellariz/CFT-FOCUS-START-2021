package ru.cftfocusstart.task3.model;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task3.view.GameType;

@Slf4j
public class Game {
    private GameState gameState;
    private GameType gameType;
    private final Field field;

    public Game() {
        log.debug("Configuration field");
        ConfigField.setSizeOfField(9, 9);
        ConfigField.setTotalBombs(16);
        field = new Field();
        this.gameState = GameState.PLAYING;
        this.gameType = GameType.NOVICE;
        field.setGameState(gameState);
    }

    public Cell getFieldCell(int x, int y) {
        return field.getCell(x, y);
    }

    public Field getField() {
        return field;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        field.setGameState(gameState);
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public void restartGame() {
        field.generateNewField();
    }

    public void startNewGame(int totalBombs) {
        ConfigField.setTotalBombs(totalBombs);
    }
}
