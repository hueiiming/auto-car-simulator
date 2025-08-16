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

                // Skip cars with no commands left
                if (commands.isEmpty()) {
                    continue;
                }

                allCommandsExecuted = false; // At least one car still has commands
                processCarCommand(car, field);

                Car collidingCar = detectCollision(car, cars);
                if (collidingCar != null) {
                    logger.log(Level.WARNING, MessageFormat.format(
                            "Collision detected between {0} and {1} at position {2} on step {3}.",
                            car.getName(), collidingCar.getName(), car.getPosition(), steps + 1));
                    return new CollisionResult(car.getName(), collidingCar.getName(), car.getPosition(), steps + 1);
                }
            }

            steps++;

            // Exit loop once all commands have been executed
            if (allCommandsExecuted) {
                break;
            }
        }

        logger.log(Level.INFO, "No collision");
        return new CollisionResult(); // Return no collision result
    }

    private void processCarCommand(Car car, Field field) {
        // Remove the command to process commands sequentially
        Command command = car.getCommands().remove(0);

        this.carService.drive(car, command, field.getWidth(), field.getHeight());
    }

    private Car detectCollision(Car car, List<Car> cars) {
        for (Car otherCar : cars) {
            if (car != otherCar
                    && car.getPosition().getX() == otherCar.getPosition().getX()
                    && car.getPosition().getY() == otherCar.getPosition().getY()) {
                return otherCar; // Return the colliding car
            }
        }
        return null; // No collision detected
    }
}
