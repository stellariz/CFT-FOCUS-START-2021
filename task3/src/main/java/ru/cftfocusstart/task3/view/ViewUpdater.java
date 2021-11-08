package ru.cftfocusstart.task3.view;

import ru.cftfocusstart.task3.model.Cell;
import ru.cftfocusstart.task3.model.Field;

public class ViewUpdater implements CellEventListener{

    private final Field field;
    private final MainWindow mainWindow;
    private final FlaggedCellEventListener flaggedCellEventListener = new FlaggedCellEventListener();

    public ViewUpdater(Field field, MainWindow mainWindow) {
        this.field = field;
        this.mainWindow = mainWindow;
    }

    @Override
    public void onMouseClick(int x, int y, ButtonType buttonType) {
        Cell clickedCell = field.getCell(x,y);
        if (buttonType == ButtonType.LEFT_BUTTON){
            flaggedCellEventListener.onMouseClick(field, clickedCell, buttonType);
        }
    }
}
