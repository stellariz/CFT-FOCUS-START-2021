package ru.cftfocusstart.task2.utils;

import java.util.Arrays;
import java.util.Optional;

public enum PrintMode {
    OFF("-d"),
    CONSOLE_MODE("-c"),
    FILE_MODE("-f");

    private final String mode;

    PrintMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

    public static Optional<PrintMode> get(String printMode) {
        return Arrays.stream(PrintMode.values())
                .filter(curPrintMode -> curPrintMode.getMode().equals(printMode))
                .findFirst();
    }
}
