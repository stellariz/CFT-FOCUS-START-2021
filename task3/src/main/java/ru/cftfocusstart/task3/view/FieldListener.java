package ru.cftfocusstart.task3.view;

import ru.cftfocusstart.task3.model.Cell;
import ru.cftfocusstart.task3.model.Field;
import ru.cftfocusstart.task3.model.Game;

public class FieldListener {
    private final MainWindow mainWindow;
    // пусть game подпишется на изменения в field - при нажатии на мину будем дёргать listener
    // который будет завершать игру
    //private Game game;

    public FieldListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void updateMarkCellView(Cell markedCell) {
        mainWindow.updateBombCounter();
        mainWindow.setCellImage(markedCell.getX(), markedCell.getY(), GameImage.MARKED);
    }

    public void updateUnmarkCellView(Cell unmarkedCell) {
        mainWindow.updateBombCounter();
        mainWindow.setCellImage(unmarkedCell.getX(), unmarkedCell.getY(), GameImage.CLOSED);
    }

    public void openCell(Cell openedCell) {
        GameImage imageIcon;
        switch (openedCell.getCellState()){
            case ZERO:
                imageIcon = GameImage.EMPTY;
                break;
            case ONE:
                imageIcon = GameImage.NUM_1;
                break;
            case TWO:
                imageIcon = GameImage.NUM_2;
                break;
            case THREE:
                imageIcon = GameImage.NUM_3;
                break;
            case FOUR:
                imageIcon = GameImage.NUM_4;
                break;
            case FIVE:
                imageIcon = GameImage.NUM_5;
                break;
            case SIX:
                imageIcon = GameImage.NUM_6;
                break;
            case SEVEN:
                imageIcon = GameImage.NUM_7;
                break;
            case EIGHT:
                imageIcon = GameImage.NUM_8;
                break;
            case BOMB:
                imageIcon = GameImage.BOMB_ICON;
                break;
            default:
                assert false;
                imageIcon = GameImage.EMPTY;
        }
        mainWindow.setCellImage(openedCell.getX(),openedCell.getY(), imageIcon);
    }
}
