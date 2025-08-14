package unit;

import org.autocarsimulator.controller.CarController;
import org.autocarsimulator.model.Command;
import org.autocarsimulator.model.Direction;
import org.autocarsimulator.model.Position;
import org.autocarsimulator.util.util;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

public class CarControllerTest {

    @Test
    void testRun() throws IOException {
        String dummyFilePath = "dummy.txt";
        List<String> mockLines = Arrays.asList(
            "10 10",
            "1 2 N",
            "FFRFFFRRLF"
        );

        String dummyCommands = "FFRFFFRRLF";
        List<Command> mockCommands = Arrays.asList(
            Command.F,
            Command.F,
            Command.R,
            Command.F,
            Command.F,
            Command.F,
            Command.R,
            Command.R,
            Command.L,
            Command.F
        );

        try (MockedStatic<util> mockUtil = mockStatic(util.class)) {
            mockUtil.when(() -> util.readLines(dummyFilePath)).thenReturn(mockLines);
            mockUtil.when(() -> util.convertToCommands(dummyCommands)).thenReturn(mockCommands);
            CarController carController = new CarController();
            Position position = carController.run(dummyFilePath);

            assertEquals(4, position.getX());
            assertEquals(3, position.getY());
            assertEquals(Direction.S, position.getDirection());
        }
    }
}
