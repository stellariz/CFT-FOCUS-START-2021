package ru.cftfocusstart.task3.model;

public class Game {
    private GameState gameState;
    private GameStateChanger gameStateChanger;
    private final Field field;

    public Game(int totalBomb) {
        this.gameState = GameState.PREGAME;
        gameStateChanger = new PreGameState();
        field = new Field(totalBomb);
        //gameStateChanger.goNextState();
    }

    public Cell getFieldCell(int x, int y) {
        return field.getCell(x, y);
    }

    public Field getField() {
        return field;
    }

    public void setGameState(GameStateChanger gameStateChanger) {
        this.gameStateChanger = gameStateChanger;
    }

    void updateGameState(GameState newGameState) {
        this.gameState = newGameState;
    }
}
