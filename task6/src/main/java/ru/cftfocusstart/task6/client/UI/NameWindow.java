package ru.cftfocusstart.task6.client.UI;

import javax.swing.*;
import java.awt.*;

public class NameWindow extends JDialog {
    private NameListener nameListener;

    public NameWindow(JFrame frame) {
        super(frame, "Enter nickname", true);

        JTextField nameField = new JTextField();

        GridLayout layout = new GridLayout(3, 1);
        Container contentPane = getContentPane();
        contentPane.setLayout(layout);

        contentPane.add(new JLabel("Enter your name:"));
        contentPane.add(nameField);
        contentPane.add(createOkButton(nameField));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(210, 120));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    public void setNameListener(NameListener nameListener) {
        this.nameListener = nameListener;
    }

    private JButton createOkButton(JTextField nameField) {
        JButton button = new JButton("OK");
        button.addActionListener(e -> {
            dispose();
            if (nameListener != null) {
                nameListener.onNameEntered(nameField.getText());
            }
        });
        return button;
    }
}
