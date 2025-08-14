package org.autocarsimulator.service;

import org.autocarsimulator.model.Direction;
import org.autocarsimulator.model.Position;

import static org.autocarsimulator.model.Gear.F;

public class CarServiceImpl implements CarService {

    @Override
    public void drive(Position pos, int maxWidth, int maxHeight, String commands) {
        Direction currDirection = pos.getDirection();
        for (char cmd : commands.toCharArray()) {
            switch (cmd) {
                case 'L':
                    pos.setDirection(currDirection.turnLeft());
                case 'R':
                    pos.setDirection(currDirection.turnRight());
                case F:

            }
        }
    }

    private void moveForward(Position pos, int maxWidth, int maxHeight) {
        int x = pos.getX();
        int y = pos.getY();
    }
}
