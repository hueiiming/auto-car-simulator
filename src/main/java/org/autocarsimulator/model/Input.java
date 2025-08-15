package org.autocarsimulator.model;

import java.util.List;

public class Input {
    private final Field field;
    private final List<Car> cars;

    public Input(Field field, List<Car> cars) {
        this.field = field;
        this.cars = cars;
    }

    public Field getField() { return this.field; }
    public List<Car> getCars() { return this.cars; }
}
