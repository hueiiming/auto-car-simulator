package org.autocarsimulator.service;

import org.autocarsimulator.model.Command;
import org.autocarsimulator.model.Direction;
import org.autocarsimulator.model.Position;

import java.util.List;

import static org.autocarsimulator.model.Command.F;

public class CarServiceImpl implements CarService {

    @Override
    public void drive(Position pos, int maxWidth, int maxHeight, List<Command> commands) {
        for (Command cmd : commands) {
            switch (cmd) {
                case L:
                    pos.setDirection(pos.getDirection().turnLeft());
                    break;
                case R:
                    pos.setDirection(pos.getDirection().turnRight());
                    break;
                case F:
                    moveForward(pos, maxWidth, maxHeight);
                    break;
            }
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
