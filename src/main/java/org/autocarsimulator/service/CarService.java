package org.autocarsimulator.service;

import org.autocarsimulator.model.Command;
import org.autocarsimulator.model.Position;

import java.util.List;

public interface CarService {
    void drive(Position startPos, int maxWidth, int maxHeight, List<Command> commands);
}
