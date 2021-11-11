package ru.cftfocusstart.task3.app;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task3.model.ConfigField;
import ru.cftfocusstart.task3.model.Game;
import ru.cftfocusstart.task3.view.*;

@Slf4j
public class Application {
    public static void main(String[] args) {
        log.debug("Running app");
        log.debug("Configuration field");
        ConfigField.setSizeOfField(9, 9);

        log.debug("Generating field");
        Game game = new Game(5);

        log.debug("Creating windows");
        MainWindow mainWindow = new MainWindow(game.getField());
        SettingsWindow settingsWindow = new SettingsWindow(mainWindow);
        HighScoresWindow highScoresWindow = new HighScoresWindow(mainWindow);

        game.getField().setFieldListener(new FieldListener(mainWindow));

        for (int j = 0; j < ConfigField.getWidth(); ++j){
            for (int i = 0; i < ConfigField.getLength(); ++i){
                System.out.print(game.getFieldCell(i, j).getCellState()+ " ");
            }
            System.out.println();
        }

        mainWindow.setNewGameMenuAction(e -> { /* TODO */ });
        mainWindow.setSettingsMenuAction(e -> settingsWindow.setVisible(true));
        mainWindow.setHighScoresMenuAction(e -> highScoresWindow.setVisible(true));
        mainWindow.setExitMenuAction(e -> mainWindow.dispose());

        mainWindow.createGameField(10, 10);
        mainWindow.setVisible(true);

        // TODO: There is a sample code below, remove it after try

        /*mainWindow.setTimerValue(145);
        mainWindow.setBombsCount(45);
        mainWindow.setCellImage(0, 0, GameImage.EMPTY);
        mainWindow.setCellImage(0, 1, GameImage.CLOSED);
        mainWindow.setCellImage(0, 2, GameImage.MARKED);
        mainWindow.setCellImage(0, 3, GameImage.BOMB);
        mainWindow.setCellImage(1, 0, GameImage.NUM_1);
        mainWindow.setCellImage(1, 1, GameImage.NUM_2);
        mainWindow.setCellImage(1, 2, GameImage.NUM_3);
        mainWindow.setCellImage(1, 3, GameImage.NUM_4);
        mainWindow.setCellImage(1, 4, GameImage.NUM_5);
        mainWindow.setCellImage(1, 5, GameImage.NUM_6);
        mainWindow.setCellImage(1, 6, GameImage.NUM_7);
        mainWindow.setCellImage(1, 7, GameImage.NUM_8);*/
    }
}
