package ru.cftfocusstart.task6.client.UI;

import javax.swing.*;
import java.awt.*;

public class ServerInfoWindow extends JDialog {
    private final JTextField serverIpText;
    private final JTextField serverPortText;

    private ConnectionListener connectListener;

    public ServerInfoWindow(JFrame owner) {
        super(owner, "Server connection", true);

        GridLayout layout = new GridLayout(0, 1);
        Container contentPane = getContentPane();
        contentPane.setLayout(layout);

        JLabel portServerLabel = new JLabel("Port server");
        JLabel serverIpLabel = new JLabel("IP server");
        serverIpText = new JTextField();
        serverPortText = new JTextField();
        serverIpText.setPreferredSize(new Dimension(40, 5));
        serverPortText.setPreferredSize(new Dimension(40, 5));
        contentPane.add(serverIpLabel);
        contentPane.add(serverIpText);
        contentPane.add(portServerLabel);
        contentPane.add(serverPortText);
        contentPane.add(createConnectButton());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(300, 150));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    public void setConnectionListener(ConnectionListener connectListener) {
        this.connectListener = connectListener;
    }

    private JButton createConnectButton() {
        JButton button = new JButton("Connect");
        button.addActionListener(e -> {
            dispose();
            if (connectListener != null) {
                connectListener.onClickConnect(serverIpText.getText(), serverPortText.getText());
            }
        });
        return button;
    }
}
