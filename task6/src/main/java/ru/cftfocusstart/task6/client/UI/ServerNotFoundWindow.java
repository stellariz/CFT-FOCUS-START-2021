package ru.cftfocusstart.task6.client.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ServerNotFoundWindow extends JDialog {
    private ActionListener okListener;

    public ServerNotFoundWindow(JFrame owner) {
        super(owner, "Oops", true);

        GridBagLayout layout = new GridBagLayout();
        Container contentPane = getContentPane();
        contentPane.setLayout(layout);

        contentPane.add(createNotFoundLabel(layout));
        contentPane.add(createExitButton(layout));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(300, 130));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    public void setOkListener(ActionListener okListener) {
        this.okListener = okListener;
    }

    private JLabel createNotFoundLabel(GridBagLayout layout) {
        JLabel label = new JLabel("Server wasn't found!");
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

            if (okListener != null) {
                okListener.actionPerformed(e);
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
