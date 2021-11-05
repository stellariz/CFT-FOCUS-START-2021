package ru.cftfocusstart.task2.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

public class CheckerInputDataTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"asd", "-k", "-l", "13"})
    void getPrintMode_throwsIllegalArgumentException_ifIncorrectPrintKey(String printKey) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> CheckerInputData.getPrintMode(new String[]{printKey}));
    }

    @Test
    void getPrintMode_returnValue() {
        Assertions.assertEquals(PrintMode.OFF, CheckerInputData.getPrintMode(new String[]{"-d"}));
        Assertions.assertEquals(PrintMode.CONSOLE_MODE, CheckerInputData.getPrintMode(new String[]{"-c"}));
        Assertions.assertEquals(PrintMode.FILE_MODE, CheckerInputData.getPrintMode(new String[]{"-f"}));
    }

    @ParameterizedTest
    @MethodSource("methodIncorrectArgumentsNumberProvider")
    void getFileNames_throwsIllegalArgumentException_ifIncorrectPrintModeAndArgumentsNumber(PrintMode printMode, String[] args) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> CheckerInputData.getFileNames(printMode, args));
    }

    static Stream<Arguments> methodIncorrectArgumentsNumberProvider() {
        return Stream.of(
                Arguments.of(PrintMode.CONSOLE_MODE, new String[]{"-c", "file", "anotherFile"}),
                Arguments.of(PrintMode.CONSOLE_MODE, new String[]{"-c"}),
                Arguments.of(PrintMode.FILE_MODE, new String[]{"-f", "file"}),
                Arguments.of(PrintMode.FILE_MODE, new String[]{"-f", "file", "anotherFile1", "anotherFile2"}),
                Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("methodIncorrectFileNamesProvider")
    void getFileNames_throwsIllegalArgumentException_ifIncorrectFileName(PrintMode printMode, String[] args) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> CheckerInputData.getFileNames(printMode, args));
    }

    static Stream<Arguments> methodIncorrectFileNamesProvider() {
        return Stream.of(
                Arguments.of(PrintMode.CONSOLE_MODE, new String[]{"-c", "abcdefghijk"}),
                Arguments.of(PrintMode.CONSOLE_MODE, new String[]{"-c", "1234"}),
                Arguments.of(PrintMode.FILE_MODE, new String[]{"-f", "abcdefghikj", "output.txt"})
        );
    }
}
