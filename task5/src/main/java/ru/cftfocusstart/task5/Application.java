package ru.cftfocusstart.task5;

public class Application {
    //TODO: вынести константы в properties
    private final static int CONSUMERS_NUM = 1000;
    private final static int PRODUCES_NUM = 20;
    private final static int STORAGE_SIZE = 10;

    public static void main(String[] args) {
        Storage storage = new Storage(STORAGE_SIZE);
        for (int i = 0; i < PRODUCES_NUM; ++i) {
            new Thread(new Producer(i, storage)).start();
        }
        for (int i = 0; i < CONSUMERS_NUM; ++i) {
            new Thread(new Consumer(i, storage)).start();
        }
    }
}
