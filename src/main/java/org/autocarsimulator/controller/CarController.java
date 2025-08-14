package org.autocarsimulator.controller;

import org.autocarsimulator.model.Command;
import org.autocarsimulator.model.Direction;
import org.autocarsimulator.model.Position;
import org.autocarsimulator.service.CarService;
import org.autocarsimulator.service.CarServiceImpl;
import org.autocarsimulator.util.util;

import java.io.IOException;
import java.util.List;

public class CarController {
    public Position run(String filePath) throws IOException {
        List<String> lines = util.readLines(filePath);
        String[] fieldSize = lines.get(0).split(" ");
        int maxWidth = Integer.parseInt(fieldSize[0]);
        int maxHeight = Integer.parseInt(fieldSize[1]);

        String[] positions = lines.get(1).split(" ");
        int x = Integer.parseInt(positions[0]);
        int y = Integer.parseInt(positions[1]);
        Direction direction = Direction.valueOf(positions[2]);

        String commandStr = lines.get(2);
        Position startPos = new Position(x, y, direction);
        List<Command> commands = util.convertToCommands(commandStr);

        CarService carService = new CarServiceImpl();
        carService.drive(startPos, maxWidth, maxHeight, commands);
        return startPos;
    }
}
