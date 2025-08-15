package org.autocarsimulator.service;

import org.autocarsimulator.model.Car;
import org.autocarsimulator.model.Command;
import org.autocarsimulator.model.Direction;
import org.autocarsimulator.model.Position;

import java.util.List;

import static org.autocarsimulator.model.Command.F;

public class CarServiceImpl implements CarService {

    @Override
    public void drive(Car car, Command command, int width, int height) {
        Position pos = car.getPosition();
        switch (command) {
            case L:
                pos.setDirection(pos.getDirection().turnLeft());
                break;
            case R:
                pos.setDirection(pos.getDirection().turnRight());
                break;
            case F:
                moveForward(pos, width, height);
                break;
        }

    }

    private void moveForward(Position pos, int maxWidth, int maxHeight) {
        int x = pos.getX();
        int y = pos.getY();

        switch (pos.getDirection()) {
            case N: if (y < maxHeight) pos.setY(y + 1); break;
            case S: if (y > 0) pos.setY(y - 1); break;
            case E: if (x < maxWidth) pos.setX(x + 1); break;
            case W: if (x > 0) pos.setX(x - 1); break;
        }
    }
}
