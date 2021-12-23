package ru.cftfocusstart.task3.model.Game;

import ru.cftfocusstart.task3.view.View;

public class TableWinnerUpdater implements TableEventListener {
    private final View view;

    public TableWinnerUpdater(View view) {
        this.view = view;
    }

    @Override
    public void onUpdateTable(GameType gameType, Player player) {
        view.updateRecordInTable(gameType, player);
    }
}
