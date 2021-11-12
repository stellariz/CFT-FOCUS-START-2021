package ru.cftfocusstart.task3.view;

public class NewGameTypeListener implements GameTypeListener {
    private final NewGameCreator newGameCreator;

    public NewGameTypeListener(NewGameCreator newGameCreator) {
        this.newGameCreator = newGameCreator;
    }

    @Override
    public void onGameTypeChanged(GameType gameType) {
        switch (gameType) {
            case NOVICE:
                newGameCreator.startNoviceGame();
                break;
            case MEDIUM:
                newGameCreator.startMediumGame();
                break;
            case EXPERT:
                newGameCreator.startHardGame();
                break;
        }
    }
}
