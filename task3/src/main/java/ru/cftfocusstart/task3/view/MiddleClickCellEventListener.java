package ru.cftfocusstart.task3.view;

import ru.cftfocusstart.task3.model.Cell;
import ru.cftfocusstart.task3.model.Field;

public class MiddleClickCellEventListener implements CellEventListener {
    private final Field field;

    public MiddleClickCellEventListener(Field field) {
        this.field = field;
    }

    @Override
    public void onMouseClick(int x, int y, ButtonType buttonType) {
        Cell cell = field.getCell(x, y);
        switch (buttonType) {
            case RIGHT_BUTTON:
                field.openCellsAroundNumber(cell);
            case MIDDLE_BUTTON:
            case LEFT_BUTTON:
                break;
        }
    }
}
