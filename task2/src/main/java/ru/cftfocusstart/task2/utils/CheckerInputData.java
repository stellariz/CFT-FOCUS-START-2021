package ru.cftfocusstart.task2.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
public final class CheckerInputData {

    private static final int PARAMETERS_NUMBER_DEFAULT = 2;
    private static final int PARAMETERS_NUMBER_FOR_FILEOUTPUT = 3;

    private CheckerInputData() {
    }

    public static PrintMode getPrintMode(String[] args) {
        log.info("Checking print key");
        if (args == null) {
            throw new IllegalArgumentException("Null cannot be passed in program's arguments");
        }
        Optional<PrintMode> printMode = PrintMode.get(args[0]);
        if (printMode.isPresent()) {
            return printMode.get();
        } else {
            throw new IllegalArgumentException("Unknown argument for print: \"" + args[0] + "\"");
        }
    }

    private static void checkDoesExistConfigFile(String fileName) {
        if (!new File(fileName).exists()) {
            throw new IllegalArgumentException("Config file with name: " + fileName + " doesn't exist!");
        }
    }

    public static String[] getFileNames(PrintMode printMode, String[] args) {
        log.info("Checking number of arguments");
        if (printMode == null) {
            throw new IllegalArgumentException("Print mode cannot be null");
        }
        if (args == null) {
            throw new IllegalArgumentException("Program arguments cannot be null");
        }
        switch (printMode) {
            case OFF:
            case CONSOLE_MODE:
                if (args.length != PARAMETERS_NUMBER_DEFAULT) {
                    throw new IllegalArgumentException("Incorrect number of program's arguments:  " + args.length
                            + ", but should be " + PARAMETERS_NUMBER_DEFAULT);
                }
                break;
            case FILE_MODE:
                if (args.length != PARAMETERS_NUMBER_FOR_FILEOUTPUT) {
                    throw new IllegalArgumentException("Incorrect number of program's arguments:  " + args.length
                            + ", but should be " + PARAMETERS_NUMBER_FOR_FILEOUTPUT);
                }
        }
        String[] fileNames = Arrays.copyOfRange(args, 1, args.length);
        checkDoesExistConfigFile(fileNames[0]);
        return fileNames;
    }
}
