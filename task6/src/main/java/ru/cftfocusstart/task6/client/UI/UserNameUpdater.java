package ru.cftfocusstart.task6.client.UI;

import ru.cftfocusstart.task6.client.NetworkLogic;

public class UserNameUpdater implements NameListener {
    private final NetworkLogic networkLogic;

    public UserNameUpdater(NetworkLogic networkLogic) {
        this.networkLogic = networkLogic;
    }

    @Override
    public void onNameEntered(String nickname) {
        networkLogic.checkNickNameOnServer(nickname);
    }
}
