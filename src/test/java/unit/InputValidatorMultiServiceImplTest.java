package unit;

import org.autocarsimulator.service.InputValidatorMultiServiceImpl;
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
import static org.mockito.Mockito.when;

public class InputValidatorMultiServiceImplTest {

    @Test
    void testValidateInput_InvalidLineCount() throws IOException {
        // Mock dependencies
        Logger logger = Mockito.mock(Logger.class);
        List<String> mockLines = List.of("5 5");
        try (MockedStatic<util> mockedUtil = Mockito.mockStatic(util.class)) {
            mockedUtil.when(() -> util.readLines("invalidInput.txt")).thenReturn(mockLines);

            InputValidatorMultiServiceImpl validator = new InputValidatorMultiServiceImpl();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> validator.validateInput("invalidInput.txt", logger));
            assertEquals("Input file must have at least 3 lines.", exception.getMessage());
        }
    }

    @Test
    void testValidateInput_InvalidFieldDimensions() throws IOException {
        // Mock dependencies
        Logger logger = Mockito.mock(Logger.class);
        List<String> mockLines = List.of("5 5X", "", "Car1", "1 2 N", "LFRF");
        try (MockedStatic<util> mockedUtil = Mockito.mockStatic(util.class)) {
            mockedUtil.when(() -> util.readLines("invalidInput.txt")).thenReturn(mockLines);

            InputValidatorMultiServiceImpl validator = new InputValidatorMultiServiceImpl();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> validator.validateInput("invalidInput.txt", logger));
            assertEquals("First line must contain two integers separated by a space.", exception.getMessage());
        }
    }

    @Test
    void testValidateInput_EmptyCarName() throws IOException {
        // Mock dependencies
        Logger logger = Mockito.mock(Logger.class);
        List<String> mockLines = List.of("5 5", "", "", "1 2 N", "LFRF");
        try (MockedStatic<util> mockedUtil = Mockito.mockStatic(util.class)) {
            mockedUtil.when(() -> util.readLines("invalidInput.txt")).thenReturn(mockLines);

            InputValidatorMultiServiceImpl validator = new InputValidatorMultiServiceImpl();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> validator.validateInput("invalidInput.txt", logger));
            assertEquals("Car name cannot be empty.", exception.getMessage());
        }
    }

    @Test
    void testValidateInput_InvalidCarName() throws IOException {
        // Mock dependencies
        Logger logger = Mockito.mock(Logger.class);
        List<String> mockLines = List.of("5 5", "$", "1234", "1 2 N", "LFRF");
        try (MockedStatic<util> mockedUtil = Mockito.mockStatic(util.class)) {
            mockedUtil.when(() -> util.readLines("invalidInput.txt")).thenReturn(mockLines);

            InputValidatorMultiServiceImpl validator = new InputValidatorMultiServiceImpl();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> validator.validateInput("invalidInput.txt", logger));
            assertEquals("Car name must contain at least one non-digit character.", exception.getMessage());
        }
    }

    @Test
    void testValidateInput_InvalidCarPosition() throws IOException {
        // Mock dependencies
        Logger logger = Mockito.mock(Logger.class);
        List<String> mockLines = List.of("5 5", "", "Car1", "1 2 X", "LFRF");
        try (MockedStatic<util> mockedUtil = Mockito.mockStatic(util.class)) {
            mockedUtil.when(() -> util.readLines("invalidInput.txt")).thenReturn(mockLines);

            InputValidatorMultiServiceImpl validator = new InputValidatorMultiServiceImpl();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> validator.validateInput("invalidInput.txt", logger));
            assertEquals("Car position must contain two integers and a valid direction (N, S, E, W).", exception.getMessage());
        }
    }

    @Test
    void testValidateInput_InvalidCarCommands() throws IOException {
        // Mock dependencies
        Logger logger = Mockito.mock(Logger.class);
        List<String> mockLines = List.of("5 5", "", "Car1", "1 2 N", "LFX");
        try (MockedStatic<util> mockedUtil = Mockito.mockStatic(util.class)) {
            mockedUtil.when(() -> util.readLines("invalidInput.txt")).thenReturn(mockLines);

            InputValidatorMultiServiceImpl validator = new InputValidatorMultiServiceImpl();
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> validator.validateInput("invalidInput.txt", logger));
            assertEquals("Car commands must contain a sequence of commands (L, R, F).", exception.getMessage());
        }
    }
}
