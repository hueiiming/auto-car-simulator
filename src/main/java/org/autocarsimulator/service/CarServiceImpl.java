package org.autocarsimulator.service;

import org.autocarsimulator.model.Car;
import org.autocarsimulator.model.Command;
import org.autocarsimulator.model.Position;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarServiceImpl implements CarService {
    private final Logger logger;

    public CarServiceImpl(Logger logger) {
        this.logger = logger;
    }
    @Override
    public void drive(Car car, Command command, int width, int height) {
        Position pos = car.getPosition();
        logger.info(MessageFormat.format(
                "Driving car: {0}, Command: {1}, Current Position: {2}, Grid: {3}x{4}",
                car.getName(), command, pos, width, height));
        switch (command) {
            case L:
                pos.setDirection(pos.getDirection().turnLeft());
                logger.info(MessageFormat.format("Car turned left. New direction: {0}", pos.getDirection()));
                break;
            case R:
                pos.setDirection(pos.getDirection().turnRight());
                logger.info(MessageFormat.format("Car turned right. New direction: {0}", pos.getDirection()));
                break;
            case F:
                moveForward(pos, width, height);
                break;
        }

    }

    private void moveForward(Position pos, int maxWidth, int maxHeight) {
        logger.info(MessageFormat.format("Attempting to move forward. Current Position: {0}", pos));

        int x = pos.getX();
        int y = pos.getY();

        switch (pos.getDirection()) {
            case N: if (y < maxHeight) pos.setY(y + 1); else handleBoundaryViolation(pos); break;
            case S: if (y > 0) pos.setY(y - 1); else handleBoundaryViolation(pos); break;
            case E: if (x < maxWidth) pos.setX(x + 1); else handleBoundaryViolation(pos); break;
            case W: if (x > 0) pos.setX(x - 1); else handleBoundaryViolation(pos); break;
        }
        logger.info(MessageFormat.format("Moved forward. New Position: {0}", pos));
    }

    private void handleBoundaryViolation(Position pos) {
        throw new RuntimeException("Boundary violation at position " + pos);
    }
}
