package org.autocarsimulator.controller;

import org.autocarsimulator.model.*;
import org.autocarsimulator.service.CarService;

import java.util.List;
import java.util.logging.Logger;

public class SingleCarControllerImpl implements CarController {
    private final CarService carService;
    private final Logger logger;

    public SingleCarControllerImpl(CarService carService, Logger logger) {
        this.carService = carService;
        this.logger = logger;
    }
    @Override
    public Result executeCarController(Input input) {
        Car car = input.getCars().get(0);
        Field field = input.getField();
        List<Command> commands = car.getCommands();
        for (Command command : commands) {
            this.carService.drive(car, command, field.getWidth(), field.getHeight());
        }

        return new SingleCarResult(car.getPosition());
    }
}
