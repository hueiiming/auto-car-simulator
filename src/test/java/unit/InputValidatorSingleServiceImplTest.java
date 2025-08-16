package unit;

import org.autocarsimulator.service.InputValidatorSingleServiceImpl;
import org.autocarsimulator.util.util;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InputValidatorSingleServiceImplTest {

    @Test
    void testValidateInput_InvalidLineCount() {
        // Mock dependencies
        Logger logger = Mockito.mock(Logger.class);
        List<String> mockLines = List.of("5 5", "1 2 N");
        try (MockedStatic<util> mockedUtil = Mockito.mockStatic(util.class)) {
            mockedUtil.when(() -> util.readLines("invalidInput.txt")).thenReturn(mockLines);

            // Test
            InputValidatorSingleServiceImpl validator = new InputValidatorSingleServiceImpl();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> validator.validateInput("invalidInput.txt", logger));
            assertEquals("Input file must only have 3 lines.", exception.getMessage());
        }
    }

    @Test
    void testValidateInput_InvalidFieldDimensions() {
        // Mock dependencies
        Logger logger = Mockito.mock(Logger.class);
        List<String> mockLines = List.of("5 5}", "1 2 N", "LFRF");
        try (MockedStatic<util> mockedUtil = Mockito.mockStatic(util.class)) {
            mockedUtil.when(() -> util.readLines("invalidInput.txt")).thenReturn(mockLines);

            // Test
            InputValidatorSingleServiceImpl validator = new InputValidatorSingleServiceImpl();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> validator.validateInput("invalidInput.txt", logger));
            assertEquals("First line must contain 2 integers, separated by a space.", exception.getMessage());
        }
    }

    @Test
    void testValidateInput_InvalidCarPosition() {
        // Mock dependencies
        Logger logger = Mockito.mock(Logger.class);
        List<String> mockLines = List.of("5 5", "1 2 X", "LFRF");
        try (MockedStatic<util> mockedUtil = Mockito.mockStatic(util.class)) {
            mockedUtil.when(() -> util.readLines("invalidInput.txt")).thenReturn(mockLines);

            // Test
            InputValidatorSingleServiceImpl validator = new InputValidatorSingleServiceImpl();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> validator.validateInput("invalidInput.txt", logger));
            assertEquals("Second line must contain two integers and a valid direction (N, S, E, W).", exception.getMessage());
        }
    }

    @Test
    void testValidateInput_InvalidCommands() {
        // Mock dependencies
        Logger logger = Mockito.mock(Logger.class);
        List<String> mockLines = List.of("5 5", "1 2 N", "LFX");
        try (MockedStatic<util> mockedUtil = Mockito.mockStatic(util.class)) {
            mockedUtil.when(() -> util.readLines("invalidInput.txt")).thenReturn(mockLines);

            // Test
            InputValidatorSingleServiceImpl validator = new InputValidatorSingleServiceImpl();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> validator.validateInput("invalidInput.txt", logger));
            assertEquals("Third line must only contain a sequence of commands (L, R, F).", exception.getMessage());
        }
    }
}
