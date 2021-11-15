package ru.cftfocusstart.task3.model.Field;

import ru.cftfocusstart.task3.Game.Game;
import ru.cftfocusstart.task3.Game.GameState;
import ru.cftfocusstart.task3.model.Cell.Cell;
import ru.cftfocusstart.task3.view.Windows.MainWindow;
import ru.cftfocusstart.task3.view.Windows.RecordsWindow;

public class WinningGameState implements GameStateInterface {
    private final Game game;
    private final MainWindow mainWindow;

    public WinningGameState(Game game, MainWindow mainWindow) {
        this.game = game;
        this.mainWindow = mainWindow;
        game.updateGameState(GameState.WINNING);
    }

    public void checkForRecord(){
        if (game.isNewRecord()){
            RecordsWindow recordsWindow = new RecordsWindow(mainWindow);
            recordsWindow.setNameListener(game.getRecordNameListener());
            recordsWindow.setVisible(true);
        }
    }

    @Override
    public void onChangingGameState() {
        mainWindow.closeCells();
        game.getField().setNewGameState(new PreGameState(game, mainWindow));
    }

    @Override
    public void openCellsAroundNumber(Cell cell) {
    }

    @Override
    public void openCell(Cell cell) {
    }

    @Override
    public void markCell(Cell cell) {
    }

    @Override
    public void unmarkCell(Cell cell) {
    }
}
