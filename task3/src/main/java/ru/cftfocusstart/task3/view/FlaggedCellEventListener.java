package ru.cftfocusstart.task3.view;

import ru.cftfocusstart.task3.model.Cell;
import ru.cftfocusstart.task3.model.Field;

public class FlaggedCellEventListener {


    public void onMouseClick(Field field, Cell cell, ButtonType buttonType){
        switch (buttonType){
            case LEFT_BUTTON:
                if (cell.getFlag()){
                    field.unmarkCell(cell);
                } else {
                    field.markCell(cell);
                }
                break;
            case RIGHT_BUTTON:
            case MIDDLE_BUTTON:
                break;
        }
    }
}
