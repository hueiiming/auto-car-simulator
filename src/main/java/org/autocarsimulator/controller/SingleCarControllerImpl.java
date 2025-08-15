package org.autocarsimulator.controller;

import org.autocarsimulator.model.*;
import org.autocarsimulator.service.CarService;

import java.util.List;

public class SingleCarControllerImpl implements CarController {
    private final CarService carService;

    public SingleCarControllerImpl(CarService carService) {
        this.carService = carService;
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
