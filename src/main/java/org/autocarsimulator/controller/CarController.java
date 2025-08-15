package org.autocarsimulator.controller;

import org.autocarsimulator.model.Input;
import org.autocarsimulator.model.Result;

import java.io.IOException;

public interface CarController {
    Result executeCarController(Input input) throws IOException;
}
