package ru.cftfocusstart.task3.view;

import ru.cftfocusstart.task3.model.Cell;
import ru.cftfocusstart.task3.model.Field;
import ru.cftfocusstart.task3.model.ViewCellState;

public class RightClickCellEventListener implements CellEventListener {

    private final Field field;

    public RightClickCellEventListener(Field field) {
        this.field = field;
    }

    @Override
    public void onMouseClick(int x, int y, ButtonType buttonType) {
        Cell cell = field.getCell(x, y);
        switch (buttonType) {
            case MIDDLE_BUTTON:
                if (cell.getViewCellState() == ViewCellState.FLAGGED) {
                    field.unmarkCell(cell);
                } else {
                    field.markCell(cell);
                }
                break;
            case RIGHT_BUTTON:
            case LEFT_BUTTON:
                break;
        }
    }
}
