package org.autocarsimulator.service;

import org.autocarsimulator.model.Direction;
import org.autocarsimulator.model.Position;

public interface CarService {
    public void drive(Position startPos, int maxWidth, int maxHeight, String commands);
}
