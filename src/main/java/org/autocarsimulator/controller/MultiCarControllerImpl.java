package org.autocarsimulator.controller;

import org.autocarsimulator.model.*;
import org.autocarsimulator.service.CarService;
import org.autocarsimulator.service.CarServiceImpl;

import java.io.IOException;
import java.util.List;

public class MultiCarControllerImpl implements CarController {
    @Override
    public Result executeCarController(Input input) throws IOException {
        Field field = input.getField();
        List<Car> cars = input.getCars();
        CarService carService = new CarServiceImpl();
        int steps = 0;

        while (true) {
            boolean allCommandsExecuted = true;

            for (Car car : cars) {
                List<Command> commands = car.getCommands();
                if (!commands.isEmpty()) {
                    allCommandsExecuted = false; // At least one car still has commands
                    Command command = commands.remove(0);
                    carService.drive(car, command, field.getWidth(), field.getHeight());

                    for (Car otherCar : cars) {
                        if (car != otherCar
                                && car.getPosition().getX() == otherCar.getPosition().getX()
                                && car.getPosition().getY() == otherCar.getPosition().getY()
                            ) {
                                return new CollisionResult(car.getName(), otherCar.getName(), car.getPosition(), steps + 1, true);
                        }
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
}
