package org.autocarsimulator.controller;

import org.autocarsimulator.model.*;
import org.autocarsimulator.service.CarService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MultiCarControllerImpl implements CarController {
    private static final Logger LOGGER = Logger.getLogger(MultiCarControllerImpl.class.getName());

    private final CarService carService;

    public MultiCarControllerImpl(CarService carService) {
        this.carService = carService;
    }

    @Override
    public Result executeCarController(Input input) {
        Field field = input.getField();
        List<Car> cars = input.getCars();
        int steps = 0;

        while (true) {
            boolean allCommandsExecuted = true;

            for (Car car : cars) {
                List<Command> commands = car.getCommands();
                if (commands.isEmpty()) {
                    continue;
                }

                allCommandsExecuted = false; // At least one car still has commands
                processCarCommand(car, field);

                for (Car otherCar : cars) {
                    if (isCollision(car, otherCar)) {
                        LOGGER.log(Level.WARNING, "Collision detected between {0} and {1} at position {2} on step {3}.",
                                new Object[]{car.getName(), otherCar.getName(), car.getPosition(), steps + 1});
                        return new CollisionResult(car.getName(), otherCar.getName(), car.getPosition(), steps + 1, true);
                    }
                }

            }
            steps++;

            if (allCommandsExecuted) {
                break;
            }
        }
        return new CollisionResult();
    }

    private void processCarCommand(Car car, Field field) {
        Command command = car.getCommands().remove(0);
        this.carService.drive(car, command, field.getWidth(), field.getHeight());
    }
    private boolean isCollision(Car car, Car otherCar) {
        return car != otherCar
                && car.getPosition().getX() == otherCar.getPosition().getX()
                && car.getPosition().getY() == otherCar.getPosition().getY();
    }
}
