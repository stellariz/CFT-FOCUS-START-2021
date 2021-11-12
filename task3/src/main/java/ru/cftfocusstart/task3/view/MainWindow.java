package ru.cftfocusstart.task3.view;

import ru.cftfocusstart.task3.model.ConfigField;
import ru.cftfocusstart.task3.model.Field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {
    private final Container contentPane;
    private final GridBagLayout mainLayout;

    private JMenuItem newGameMenu;
    private JMenuItem highScoresMenu;
    private JMenuItem settingsMenu;
    private JMenuItem exitMenu;

    private CellEventListener listener;
    private final Field field;

    private JButton[][] cellButtons;
    private JLabel timerLabel;
    private JLabel bombsCounterLabel;

    public MainWindow(Field field) {
        super("Miner");
        this.field = field;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        createMenu();

        contentPane = getContentPane();
        mainLayout = new GridBagLayout();
        contentPane.setLayout(mainLayout);

        contentPane.setBackground(new Color(144, 158, 184));
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");

        gameMenu.add(newGameMenu = new JMenuItem("New Game"));
        gameMenu.addSeparator();
        gameMenu.add(highScoresMenu = new JMenuItem("High Scores"));
        gameMenu.add(settingsMenu = new JMenuItem("Settings"));
        gameMenu.addSeparator();
        gameMenu.add(exitMenu = new JMenuItem("Exit"));

        menuBar.add(gameMenu);
        setJMenuBar(menuBar);
    }

    public CellEventListener getListener() {
        return listener;
    }

    public void setNewGameMenuAction(ActionListener listener) {
        newGameMenu.addActionListener(listener);
    }

    public void setHighScoresMenuAction(ActionListener listener) {
        highScoresMenu.addActionListener(listener);
    }

    public void setSettingsMenuAction(ActionListener listener) {
        settingsMenu.addActionListener(listener);
    }

    public void setExitMenuAction(ActionListener listener) {
        exitMenu.addActionListener(listener);
    }

    public void setCellListener(CellEventListener listener) {
        this.listener = listener;
    }

    public void setCellImage(int x, int y, GameImage gameImage) {
        cellButtons[y][x].setIcon(gameImage.getImageIcon());
    }

    public void setBombsCount(int bombsCount) {
        bombsCounterLabel.setText(String.valueOf(bombsCount));
    }

    public void setTimerValue(int value) {
        timerLabel.setText(String.valueOf(value));
    }

    public void createGameField() {
        contentPane.removeAll();
        setPreferredSize(new Dimension(20 * ConfigField.getLength() + 70, 20 * ConfigField.getWidth() + 110));

        addButtonsPanel(createButtonsPanel());
        addTimerImage();
        addTimerLabel(timerLabel = new JLabel("0"));
        addBombCounter(bombsCounterLabel = new JLabel("0"));
        addBombCounterImage();
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createButtonsPanel() {
        cellButtons = new JButton[ConfigField.getWidth()][ConfigField.getLength()];
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension(20 * ConfigField.getWidth(), 20 * ConfigField.getLength()));
        buttonsPanel.setLayout(new GridLayout(ConfigField.getWidth(), ConfigField.getLength(), 0, 0));

        for (int row = 0; row < ConfigField.getWidth(); row++) {
            for (int col = 0; col < ConfigField.getLength(); col++) {
                final int x = col;
                final int y = row;

                cellButtons[y][x] = new JButton(GameImage.CLOSED.getImageIcon());
                cellButtons[y][x].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        switch (e.getButton()) {
                            case MouseEvent.BUTTON1:
                                setCellListener(new LeftClickCellEventListener(field));
                                listener.onMouseClick(x, y, ButtonType.LEFT_BUTTON);
                                break;
                            case MouseEvent.BUTTON2:
                                setCellListener(new MiddleClickCellEventListener(field));
                                listener.onMouseClick(x, y, ButtonType.RIGHT_BUTTON);
                                break;
                            case MouseEvent.BUTTON3:
                                setCellListener(new RightClickCellEventListener(field));
                                listener.onMouseClick(x, y, ButtonType.MIDDLE_BUTTON);
                                break;
                            default:
                                break;
                        }
                    }
                });
                buttonsPanel.add(cellButtons[y][x]);
            }
        }

        return buttonsPanel;
    }

    private void addButtonsPanel(JPanel buttonsPanel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbc.insets = new Insets(20, 20, 5, 20);
        mainLayout.setConstraints(buttonsPanel, gbc);
        contentPane.add(buttonsPanel);
    }

    private void addTimerImage() {
        JLabel label = new JLabel(GameImage.TIMER.getImageIcon());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0, 20, 0, 0);
        gbc.weightx = 0.1;
        mainLayout.setConstraints(label, gbc);
        contentPane.add(label);
    }

    private void addTimerLabel(JLabel timerLabel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 5, 0, 0);
        mainLayout.setConstraints(timerLabel, gbc);
        contentPane.add(timerLabel);
    }

    private void addBombCounter(JLabel bombsCounterLabel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weightx = 0.7;
        mainLayout.setConstraints(bombsCounterLabel, gbc);
        bombsCounterLabel.setText(String.valueOf(ConfigField.getTotalBombs()));
        contentPane.add(bombsCounterLabel);
    }

    private void addBombCounterImage() {
        JLabel label = new JLabel(GameImage.BOMB_ICON.getImageIcon());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 3;
        gbc.insets = new Insets(0, 5, 0, 20);
        gbc.weightx = 0.1;
        mainLayout.setConstraints(label, gbc);
        contentPane.add(label);
    }

    public void closeCells() {
        for (int i = 0; i < ConfigField.getWidth(); ++i) {
            for (int j = 0; j < ConfigField.getLength(); ++j) {
                if (cellButtons[i][j].getIcon() != GameImage.CLOSED.getImageIcon()) {
                    cellButtons[i][j].setIcon(GameImage.CLOSED.getImageIcon());
                }
            }
        }
    }
}
