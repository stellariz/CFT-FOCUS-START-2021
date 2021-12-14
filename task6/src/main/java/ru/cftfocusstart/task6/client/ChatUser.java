package ru.cftfocusstart.task6.client;

public class ChatUser {
    private String userName;

    public ChatUser() {
    }

    public ChatUser(String userName) {
        this.userName = userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
