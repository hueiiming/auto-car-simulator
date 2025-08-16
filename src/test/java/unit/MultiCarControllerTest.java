package unit;

import org.autocarsimulator.controller.MultiCarControllerImpl;
import org.autocarsimulator.model.*;
import org.autocarsimulator.service.CarService;
import org.autocarsimulator.service.CarServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class MultiCarControllerTest {
    private static final Logger LOGGER = Logger.getLogger(MultiCarControllerTest.class.getName());

    private final CarService carService = new CarServiceImpl(LOGGER);
    private final MultiCarControllerImpl controller = new MultiCarControllerImpl(carService, LOGGER);

    @Test
    public void testNoCollision() {
        Car carA = new Car("A", new Position(0, 0, Direction.N), new ArrayList<>(List.of(Command.F, Command.R)));
        Car carB = new Car("B", new Position(1, 1, Direction.E), new ArrayList<>(List.of(Command.F, Command.L)));
        Input input = new Input(new Field(5, 5), new ArrayList<>(List.of(carA, carB)));

        Result result = controller.executeCarController(input);

        assertFalse(result.isCollisionOccured());
        assertNull(result.getCollisionPosition());
        assertEquals(0, result.getStepsToCollision());
        assertEquals("no collision", result.getResult());
    }

    @Test
    public void testCollisionDetection() {
        Car carA = new Car("A", new Position(0, 0, Direction.N), new ArrayList<>(List.of(Command.F)));
        Car carB = new Car("B", new Position(0, 1, Direction.S), new ArrayList<>(List.of(Command.F)));
        Input input = new Input(new Field(5, 5), new ArrayList<>(List.of(carA, carB)));


        Result result = controller.executeCarController(input);

        assertTrue(result.isCollisionOccured());
        assertEquals("A", result.getCarName1());
        assertEquals("B", result.getCarName2());
        assertEquals(0, result.getCollisionPosition().getX());
        assertEquals(1, result.getCollisionPosition().getY());
        assertEquals(1, result.getStepsToCollision());
        assertEquals("A B\n0 1\n1", result.getResult());
    }
}
