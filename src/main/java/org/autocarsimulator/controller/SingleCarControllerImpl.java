package org.autocarsimulator.controller;

import org.autocarsimulator.model.*;
import org.autocarsimulator.service.CarService;
import org.autocarsimulator.service.CarServiceImpl;
import org.autocarsimulator.util.util;

import java.io.IOException;
import java.util.List;

public class SingleCarControllerImpl implements CarController {
    @Override
    public Result executeCarController(Input input) throws IOException {
        CarService carService = new CarServiceImpl();
        Car car = input.getCars().get(0);
        Field field = input.getField();
        List<Command> commands = car.getCommands();
        for (Command command : commands) {
            carService.drive(car, command, field.getWidth(), field.getHeight());
        }
        return new SingleCarResult(car.getPosition());
    }
}
