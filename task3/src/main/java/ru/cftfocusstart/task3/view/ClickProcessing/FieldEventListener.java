package ru.cftfocusstart.task3.view.ClickProcessing;

import ru.cftfocusstart.task3.model.Cell.Cell;
import ru.cftfocusstart.task3.model.Field.Field;
import ru.cftfocusstart.task3.view.GameImages.GameImage;
import ru.cftfocusstart.task3.view.Windows.MainWindow;

public class FieldEventListener {
    private final MainWindow mainWindow;
    private final Field field;

    public FieldEventListener(Field field, MainWindow mainWindow) {
        this.field = field;
        this.mainWindow = mainWindow;
    }

    public void updateMarkCellView(Cell markedCell) {
        mainWindow.setBombsCount(field.getBombsWithFlags());
        mainWindow.setCellImage(markedCell.getX(), markedCell.getY(), GameImage.MARKED);
    }

    public void updateUnmarkCellView(Cell unmarkedCell) {
        mainWindow.setBombsCount(field.getBombsWithFlags());
        mainWindow.setCellImage(unmarkedCell.getX(), unmarkedCell.getY(), GameImage.CLOSED);
    }

    public void viewOpenCell(Cell openedCell) {
        GameImage imageIcon;
        switch (openedCell.getCellState()) {
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
                imageIcon = GameImage.BOMB;
                break;
            default:
                assert false;
                imageIcon = GameImage.EMPTY;
        }
        mainWindow.setCellImage(openedCell.getX(), openedCell.getY(), imageIcon);
    }
}
