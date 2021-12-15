package ru.cftfocusstart.task6.client.UI;

import ru.cftfocusstart.task6.client.ChatUser;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UsersOnlineWindow extends JDialog {
    private final JPanel midPanel;
    private DefaultListModel<String> defaultListModel;
    private JList<String> jlist;

    public UsersOnlineWindow(JFrame owner) {
        super(owner, "Users online", true);
        midPanel = new JPanel();
        initListOfUsers();
        add(midPanel);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(200, 200));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private void initListOfUsers() {
        defaultListModel = new DefaultListModel<>();
        jlist = new JList<>(defaultListModel);
        JScrollPane jScrollPane = new JScrollPane(jlist);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setPreferredSize(new Dimension(150, 180));
        midPanel.add(jScrollPane);
    }

    public void addUserInList(String userName) {
        defaultListModel.add(defaultListModel.size(), userName);
    }

    public void removeUserFromList(String userName) {
        defaultListModel.removeElement(userName);
    }

    public void loadUserList(List<ChatUser> chatUserList) {
        chatUserList.forEach(e -> defaultListModel.add(defaultListModel.size(), e.getUserName()));
    }
}
