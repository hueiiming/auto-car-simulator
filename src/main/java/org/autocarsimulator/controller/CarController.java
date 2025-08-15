package org.autocarsimulator.controller;

import org.autocarsimulator.model.Input;
import org.autocarsimulator.model.Result;

public interface CarController {
    Result executeCarController(Input input);
}
