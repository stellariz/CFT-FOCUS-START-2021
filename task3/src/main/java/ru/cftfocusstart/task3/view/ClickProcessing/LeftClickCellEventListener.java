package ru.cftfocusstart.task3.view.ClickProcessing;

import ru.cftfocusstart.task3.model.Field.Field;

public class LeftClickCellEventListener implements CellEventListener {
    private final Field field;

    public LeftClickCellEventListener(Field field) {
        this.field = field;
    }

    @Override
    public void onMouseClick(int x, int y, ButtonType buttonType) {
        field.getGameState().openCell(field.getCell(x, y));
    }
}
