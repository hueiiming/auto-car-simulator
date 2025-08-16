package unit;

import org.autocarsimulator.model.Car;
import org.autocarsimulator.model.Command;
import org.autocarsimulator.model.Direction;
import org.autocarsimulator.model.Position;
import org.autocarsimulator.service.CarService;
import org.autocarsimulator.service.CarServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarServiceImplTest {

    private static final Logger LOGGER = Logger.getLogger(CarServiceImplTest.class.getName());

    private final CarService carService = new CarServiceImpl(LOGGER);

    @Test
    public void testTurnLeft() {
        Car car = new Car("CarA", new Position(0, 0, Direction.N), null);
        carService.drive(car, Command.L, 10, 10);
        assertEquals(Direction.W, car.getPosition().getDirection());
    }

    @Test
    public void testTurnRight() {
        Car car = new Car("CarA", new Position(0, 0, Direction.N), null);
        carService.drive(car, Command.R, 10, 10);
        assertEquals(Direction.E, car.getPosition().getDirection());
    }

    @Test
    public void testMoveForward() {
        Car car = new Car("CarA", new Position(0, 0, Direction.N), null);
        carService.drive(car, Command.F, 10, 10);
        assertEquals(0, car.getPosition().getX());
        assertEquals(1, car.getPosition().getY());
    }

    @Test
    public void testMoveOutOfBounds() {
        Car car = new Car("CarA", new Position(0, 0, Direction.S), null);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            carService.drive(car, Command.F, 10, 10);
        });

        assertEquals("Boundary violation at position x=0, y=0, direction=S", exception.getMessage());
    }
}
