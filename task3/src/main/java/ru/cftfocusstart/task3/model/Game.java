package ru.cftfocusstart.task3.model;

public class Game {
    private GameState gameState;
    private final Field field;

    public Game(int totalBomb) {
        field = new Field(totalBomb);
        this.gameState = GameState.PLAYING;
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
}
