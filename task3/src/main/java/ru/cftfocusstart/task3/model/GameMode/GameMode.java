package ru.cftfocusstart.task3.model.GameMode;

import ru.cftfocusstart.task3.model.Field.PreGameState;
import ru.cftfocusstart.task3.model.Game.Game;
import ru.cftfocusstart.task3.model.Game.GameType;

public abstract class GameMode {
    protected GameType gameType;

    public GameMode(Game game) {
        game.setGameState(new PreGameState(game));
    }

    public GameType getGameType() {
        return gameType;
    }
}
