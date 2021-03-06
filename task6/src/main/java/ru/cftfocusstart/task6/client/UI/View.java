package ru.cftfocusstart.task6.client.UI;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task6.client.ChatUser;
import ru.cftfocusstart.task6.client.Message.Message;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

@Slf4j
public class View {
    private final BigMessageWindow bigMessageWindow;
    private final ChatWindow chatWindow;
    private final UnavailableNickWindow unavailableNickWindow;
    private final ServerNotFoundWindow serverNotFoundWindow;
    private final ServerInfoWindow serverInfoWindow;
    private final NameWindow nameWindow;
    private final UsersOnlineWindow usersOnlineWindow;

    public View() {
        this.chatWindow = new ChatWindow();
        this.unavailableNickWindow = new UnavailableNickWindow(null);
        this.serverNotFoundWindow = new ServerNotFoundWindow(null);
        this.serverInfoWindow = new ServerInfoWindow(null);
        this.nameWindow = new NameWindow(null);
        this.usersOnlineWindow = new UsersOnlineWindow(null);
        this.bigMessageWindow = new BigMessageWindow(null);
        setScenarioWindows();
    }

    private void setScenarioWindows() {
        chatWindow.setUsersOnlineAction(e -> showUsersOnlineWindow());
        unavailableNickWindow.setOkListener(e -> showNameWindow());
        serverNotFoundWindow.setOkListener(e -> showServerInfoWindow());
    }

    public void showUsersOnlineWindow() {
        usersOnlineWindow.setVisible(true);
    }

    public void showChatWindow() {
        chatWindow.setVisible(true);
    }

    public void showUnavailableNickWindow() {
        unavailableNickWindow.setVisible(true);
    }

    public void showServerNotFoundWindow() {
        serverNotFoundWindow.setVisible(true);
    }

    public void showServerInfoWindow() {
        serverInfoWindow.setVisible(true);
    }

    public void showNameWindow() {
        nameWindow.setVisible(true);
    }

    public void setConnectionListener(ConnectionListener connectionListener) {
        serverInfoWindow.setConnectionListener(connectionListener);
    }

    public void setSendMessageListener(SendMessageListener sendMessageListener) {
        chatWindow.setSendMessageListener(sendMessageListener);
        chatWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sendMessageListener.onClickClose();
            }
        });
    }

    public void setNameListener(NameListener nameListener) {
        nameWindow.setNameListener(nameListener);
    }

    public void updateChatWindow(Message message) {
        chatWindow.updateChat(message);
    }

    public void showBigMessageWindow() {
        bigMessageWindow.setVisible(true);
    }

    public void addNewUserInList(ChatUser chatUser) {
        usersOnlineWindow.addUserInList(chatUser.getUserName());
    }

    public void removeUserFromList(ChatUser chatUser) {
        usersOnlineWindow.removeUserFromList(chatUser.getUserName());
    }

    public void loadUsers(List<ChatUser> chatUserList) {
        usersOnlineWindow.loadUserList(chatUserList);
    }
}
