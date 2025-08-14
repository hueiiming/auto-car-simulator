package unit;

import org.autocarsimulator.model.Command;
import org.autocarsimulator.model.Direction;
import org.autocarsimulator.model.Position;
import org.autocarsimulator.service.CarService;
import org.autocarsimulator.service.CarServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarServiceImplTest {

    private final CarService carService = new CarServiceImpl();

    @Test
    public void testDriveWithValidCommands() {
        Position startPos = new Position(3, 4, Direction.N);
        List<Command> commands = List.of(Command.F, Command.L, Command.F, Command.R, Command.R);
        int maxWidth = 10;
        int maxHeight = 10;
        carService.drive(startPos, maxWidth, maxHeight, commands);
        assertEquals(2, startPos.getX());
        assertEquals(5, startPos.getY());
        assertEquals(Direction.E, startPos.getDirection());
    }

    @Test
    void testDriveOutOfBounds() {
        Position startPos = new Position(0, 0, Direction.S);
        List<Command> commands = List.of(Command.F);

        carService.drive(startPos, 5, 5, commands);

        assertEquals(0, startPos.getX());
        assertEquals(0, startPos.getY());
        assertEquals(Direction.S, startPos.getDirection());
    }

}
