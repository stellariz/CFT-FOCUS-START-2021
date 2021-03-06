package ru.cftfocusstart.task6.client.UI;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task6.client.Message.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@Slf4j
public class ChatWindow extends JFrame {
    private SendMessageListener sendMessageListener;
    private JTextField messageBox;
    private final JTextArea chatBox;
    private JMenuItem usersOnline;

    public ChatWindow() {
        super("Chat");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.BLUE);
        southPanel.setLayout(new GridBagLayout());


        chatBox = new JTextArea();
        chatBox.setBackground(Color.PINK);
        chatBox.setEditable(false);
        chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
        chatBox.setLineWrap(true);

        mainPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);

        addTextArea(southPanel);
        addSendMessageButton(southPanel);

        mainPanel.add(BorderLayout.SOUTH, southPanel);

        add(mainPanel);
        createMenu();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        setLocationRelativeTo(null);
    }

    public void setSendMessageListener(SendMessageListener sendMessageListener) {
        this.sendMessageListener = sendMessageListener;
    }

    public void setUsersOnlineAction(ActionListener actionListener) {
        usersOnline.addActionListener(actionListener);
    }

    public void updateChat(Message message) {
        chatBox.append("<" + message.getNickName() + ">(" + message.getDate() + ") : " + message.getText()
                + System.lineSeparator());
    }

    private void addSendMessageButton(JPanel southPanel) {
        JButton sendMessage = new JButton("Send Message");
        sendMessage.addActionListener(e -> {
            if (messageBox.getText().length() >= 1) {
                sendMessageListener.onClickSend(messageBox.getText());
                messageBox.setText("");
            }
            log.info("Message was send");
            messageBox.requestFocusInWindow();
        });

        GridBagConstraints right = new GridBagConstraints();
        right.insets = new Insets(0, 10, 0, 0);
        right.anchor = GridBagConstraints.LINE_END;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0;
        right.weighty = 1.0;

        southPanel.add(sendMessage, right);
    }

    private void createMenu() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu users = new JMenu("Chat info");
        users.add(usersOnline = new JMenuItem("Online users"));
        jMenuBar.add(users);
        setJMenuBar(jMenuBar);
    }

    private void addTextArea(JPanel southPanel) {
        messageBox = new JTextField(70);
        messageBox.requestFocusInWindow();

        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.LINE_START;
        left.fill = GridBagConstraints.HORIZONTAL;
        left.weightx = 512.0;
        left.weighty = 1.0;

        southPanel.add(messageBox, left);
    }
}
