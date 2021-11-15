package ru.cftfocusstart.task3.view;

public class Player {
    private String Name;
    private int record;

    public Player(String name, int record) {
        Name = name;
        this.record = record;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }
}
