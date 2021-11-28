package ru.cftfocusstart.task3.model.GameMode;

import ru.cftfocusstart.task3.model.Field.ConfigField;
import ru.cftfocusstart.task3.model.Game.Game;
import ru.cftfocusstart.task3.model.Game.GameType;

public class NewGameTypeListener implements GameTypeListener {
    private final Game game;

    public NewGameTypeListener(Game game) {
        this.game = game;
    }

    @Override
    public void onGameTypeChanged(GameType gameType) {
        switch (gameType) {
            case NOVICE:
                ConfigField.setSizeOfField(9, 9);
                ConfigField.setTotalBombs(10);
                game.setGameMode(new NoviceGameMode(game));
                break;
            case MEDIUM:
                ConfigField.setSizeOfField(16, 16);
                ConfigField.setTotalBombs(40);
                game.setGameMode(new MediumGameMode(game));
                break;
            case EXPERT:
                ConfigField.setSizeOfField(16, 30);
                ConfigField.setTotalBombs(99);
                game.setGameMode(new HardGameMode(game));
                break;
        }
    }

    @Override
    public void restartGame() {
        onGameTypeChanged(game.getGameType());
    }
}
