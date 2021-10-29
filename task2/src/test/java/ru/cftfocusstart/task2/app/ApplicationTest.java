package ru.cftfocusstart.task2.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

public class ApplicationTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"asd", "-f", "-l", "13"})
    void doesPrintInConsole_throwsIllegalArgumentException_ifIncorrectKey(String key) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Application.doesPrintInConsole(key));
    }

    @Test
    void doesPrintInConsole_returnValue() {
        Assertions.assertTrue(Application.doesPrintInConsole("-c"));
        Assertions.assertFalse(Application.doesPrintInConsole("-d"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @MethodSource("methodIncorrectArgumentsNumberProvider")
    void checkNumberUserArguments_throwsIllegalArgumentException_ifIncorrectArguments(String[] args) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Application.checkNumberUserArguments(args));
    }

    static Stream<Arguments> methodIncorrectArgumentsNumberProvider() {
        return Stream.of(
                Arguments.of((Object) new String[]{"-c", "file", "anotherFile"}),
                Arguments.of((Object) new String[]{"-c"})
        );
    }
}
