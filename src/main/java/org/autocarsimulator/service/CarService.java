package org.autocarsimulator.service;

import org.autocarsimulator.model.Car;
import org.autocarsimulator.model.Command;
import org.autocarsimulator.model.Position;

import java.util.List;

public interface CarService {
    void drive(Car car, Command command, int width, int height);
}
