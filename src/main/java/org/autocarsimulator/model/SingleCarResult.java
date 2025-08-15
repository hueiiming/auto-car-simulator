package org.autocarsimulator.model;

import org.autocarsimulator.model.Position;
import org.autocarsimulator.model.Result;

public class SingleCarResult implements Result {
    private final Position finalPosition;
    public SingleCarResult (Position finalPosition) {
        this.finalPosition = finalPosition;
    }

    public Position getFinalPosition() { return finalPosition; }

    @Override
    public String getResult() {
        return finalPosition.getX() + " " + finalPosition.getY() + " " + finalPosition.getDirection();
    }
}
