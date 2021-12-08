package ru.cftfocusstart.task6.client.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UnavailableNickWindow extends JDialog {

    private ActionListener exitListener;

    public UnavailableNickWindow(JFrame owner) {
        super(owner, "Oops", true);

        GridBagLayout layout = new GridBagLayout();
        Container contentPane = getContentPane();
        contentPane.setLayout(layout);

        contentPane.add(createDisconnectLabel(layout));
        contentPane.add(createExitButton(layout));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(500, 130));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    public void setExitListener(ActionListener exitListener) {
        this.exitListener = exitListener;
    }

    private JLabel createDisconnectLabel(GridBagLayout layout) {
        JLabel label = new JLabel("User with this nick already exists. Please, try another nick.");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        layout.setConstraints(label, gbc);
        return label;
    }

    private JButton createExitButton(GridBagLayout layout) {
        JButton exitButton = new JButton("OK");
        exitButton.setPreferredSize(new Dimension(100, 25));

        exitButton.addActionListener(e -> {
            dispose();

            if (exitListener != null) {
                exitListener.actionPerformed(e);
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(15, 5, 0, 0);
        layout.setConstraints(exitButton, gbc);

        return exitButton;
    }
}
