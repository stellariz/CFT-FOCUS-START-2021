package ru.cftfocusstart.task6.client;

import lombok.extern.slf4j.Slf4j;
import ru.cftfocusstart.task6.client.Message.Message;
import ru.cftfocusstart.task6.client.UI.View;

@Slf4j
public class ViewListener implements ChatUpdater {
    private final View view;

    public ViewListener(View view) {
        this.view = view;
    }

    @Override
    public void onReceiveMessage(Message message) {
        view.updateChatWindow(message);
    }

    @Override
    public void onReceiveUnsuccessfulConnection() {
        view.showServerNotFoundWindow();
    }

    @Override
    public void onReceiveUnavailableNick() {
        view.showUnavailableNickWindow();
    }

    @Override
    public void onSuccessfulConnection() {
        view.showNameWindow();
    }


    @Override
    public void onReceiveNewUser(Message message) {
        view.addNewUserInList(message.getChatUser());
        view.updateChatWindow(message);
    }

    @Override
    public void onReceiveUserDisconnected(Message message) {
        view.removeUserFromList(message.getChatUser());
        view.updateChatWindow(message);
    }

    @Override
    public void onReceiveGreetingFromServer(Message message) {
        view.loadUsers(message.getChatUserList());
        view.showChatWindow();
    }
}
