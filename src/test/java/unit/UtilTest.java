package unit;

import org.autocarsimulator.model.Command;
import org.autocarsimulator.model.Direction;
import org.autocarsimulator.model.Field;
import org.autocarsimulator.model.Input;
import org.autocarsimulator.util.util;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class UtilTest {
    private static final Logger LOGGER = Logger.getLogger(UtilTest.class.getName());

    @Test
    public void testParseInput1() throws IOException {
        Input input = util.parseInput1("src/test/resources/input/input1.txt");
        assertEquals(10, input.getField().getWidth());
        assertEquals(10, input.getField().getHeight());
        assertEquals(1, input.getCars().size());
        assertEquals(1, input.getCars().get(0).getPosition().getX());
        assertEquals(2, input.getCars().get(0).getPosition().getY());
        assertEquals(Direction.N, input.getCars().get(0).getPosition().getDirection());
        assertEquals(List.of(
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
            ),
              input.getCars().get(0).getCommands()
        );
    }

    @Test
    public void testParseInput1InvalidInput() {
        Exception exception = assertThrows(IOException.class, () -> {
            util.parseInput1("src/test/resources/input/invalidInput.txt");
        });
        assertTrue(exception instanceof java.nio.file.NoSuchFileException);
    }

    @Test
    public void testParseInput2() throws IOException {
        Input input = util.parseInput2("src/test/resources/input/input2.txt");
        assertEquals(10, input.getField().getWidth());
        assertEquals(10, input.getField().getHeight());
        assertEquals(2, input.getCars().size());
        assertEquals("A", input.getCars().get(0).getName());
        assertEquals(1, input.getCars().get(0).getPosition().getX());
        assertEquals(2, input.getCars().get(0).getPosition().getY());
        assertEquals(Direction.N, input.getCars().get(0).getPosition().getDirection());
        assertEquals(List.of(
                        Command.F,
                        Command.F,
                        Command.R,
                        Command.F,
                        Command.F,
                        Command.F,
                        Command.F,
                        Command.R,
                        Command.R,
                        Command.L
                ),
                input.getCars().get(0).getCommands()
        );

        assertEquals("B", input.getCars().get(1).getName());
        assertEquals(7, input.getCars().get(1).getPosition().getX());
        assertEquals(8, input.getCars().get(1).getPosition().getY());
        assertEquals(Direction.W, input.getCars().get(1).getPosition().getDirection());
        assertEquals(List.of(
                        Command.F,
                        Command.F,
                        Command.L,
                        Command.F,
                        Command.F,
                        Command.F,
                        Command.F,
                        Command.F,
                        Command.F,
                        Command.F
                ),
                input.getCars().get(1).getCommands()
        );
    }

    @Test
    public void testParseField() {
        List<String> lines = List.of("10 10", "1 2 N", "FFRFF");
        Field field = util.parseField(lines);
        assertEquals(10, field.getWidth());
        assertEquals(10, field.getHeight());
    }

    @Test
    public void testReadLines() throws IOException {
        List<String> lines = util.readLines("src/test/resources/input/input1.txt");
        assertEquals(3, lines.size());
        assertEquals("10 10", lines.get(0));
        assertEquals("1 2 N", lines.get(1));
        assertEquals("FFRFFFRRLF", lines.get(2));
    }

    @Test
    public void testWriteOutputFile() throws IOException {
        String filePath = "src/test/resources/output/testOutput.txt";
        String content = "Test Output";
        util.writeOutputFile(filePath, content, LOGGER);

        List<String> lines = Files.readAllLines(Paths.get(filePath));
        assertEquals(1, lines.size());
        assertEquals("Test Output", lines.get(0));
    }

    @Test
    public void testConvertToCommands() {
        String commandStr = "FFRFFL";
        List<Command> commands = util.convertToCommands(commandStr);
        assertEquals(List.of(Command.F, Command.F, Command.R, Command.F, Command.F, Command.L), commands);
    }
}
