package unit;

import org.autocarsimulator.controller.SingleCarControllerImpl;
import org.autocarsimulator.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingleCarControllerTest {
    private final SingleCarControllerImpl controller = new SingleCarControllerImpl();

    @Test
    public void testSingleCarExecution() throws Exception {
        Car car = new Car("A", new Position(0, 0, Direction.N), new ArrayList<>(List.of(Command.F, Command.R, Command.F)));
        Input input = new Input(new Field(5, 5), new ArrayList<>(List.of(car)));

        Result result = controller.executeCarController(input);

        assertEquals(1, car.getPosition().getX());
        assertEquals(1, car.getPosition().getY());
        assertEquals(Direction.E, car.getPosition().getDirection());
        assertEquals("1 1 E", result.getResult());
    }

    @Test
    public void testBoundaryCondition() throws Exception {
        Car car = new Car("A", new Position(0, 0, Direction.S), new ArrayList<>(List.of(Command.F)));
        Input input = new Input(new Field(5, 5), new ArrayList<>(List.of(car)));

        Result result = controller.executeCarController(input);

        assertEquals(0, car.getPosition().getX());
        assertEquals(0, car.getPosition().getY());
        assertEquals("0 0 S", result.getResult());
    }
}
