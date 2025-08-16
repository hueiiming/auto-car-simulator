package org.autocarsimulator.controller;

import org.autocarsimulator.model.*;
import org.autocarsimulator.service.CarService;

import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MultiCarControllerImpl implements CarController {
    private final CarService carService;
    private final Logger logger;

    public MultiCarControllerImpl(CarService carService, Logger logger) {
        this.carService = carService;
        this.logger = logger;
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
                        logger.log(Level.WARNING, MessageFormat.format("Collision detected between {0} and {1} at position {2} on step {3}.",
                                car.getName(), otherCar.getName(), car.getPosition(), steps + 1));
                        return new CollisionResult(car.getName(), otherCar.getName(), car.getPosition(), steps + 1, true);
                    }
                }

            }
            steps++;

            if (allCommandsExecuted) {
                break;
            }
        }
        logger.log(Level.INFO, "No collision");
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
