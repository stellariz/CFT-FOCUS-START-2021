package ru.cftfocusstart.task3.view;

import ru.cftfocusstart.task3.model.Field;

public class LeftClickCellEventListener implements CellEventListener {

    private final Field field;

    public LeftClickCellEventListener(Field field) {
        this.field = field;
    }

    @Override
    public void onMouseClick(int x, int y, ButtonType buttonType) {
        field.openCell(field.getCell(x, y));
    }
}
