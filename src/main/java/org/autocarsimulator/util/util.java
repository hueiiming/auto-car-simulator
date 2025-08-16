package org.autocarsimulator.util;

import org.autocarsimulator.model.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class util {

    public static Input parseInput1(String filePath) throws IOException {
        List<String> lines = util.readLines(filePath);
        Field field = parseField(lines);

        String[] positions = lines.get(1).split(" ");
        int x = Integer.parseInt(positions[0]);
        int y = Integer.parseInt(positions[1]);
        Direction direction = Direction.valueOf(positions[2]);
        Position startPos = new Position(x, y, direction);

        String commandStr = lines.get(2);
        List<Command> commands = util.convertToCommands(commandStr);

        Car car = new Car("", startPos, commands);
        return new Input(field, new ArrayList<>(List.of(car)));
    }
    public static Input parseInput2(String filePath) throws IOException {
        List<String> lines = util.readLines(filePath);
        Field field = parseField(lines);

      List<Car> cars = new ArrayList<>();
      int i = 2;

      while (i < lines.size() - 2) {
          String carName = lines.get(i);
          String[] positions = lines.get(i + 1).split(" ");
          int x = Integer.parseInt(positions[0]);
          int y = Integer.parseInt(positions[1]);
          Direction direction = Direction.valueOf(positions[2]);
          Position startPos = new Position(x, y, direction);

          String commandStr = lines.get(i + 2);
          List<Command> commands = util.convertToCommands(commandStr);
          Car car = new Car(carName, startPos, commands);
          cars.add(car);

          i += 4; // Parse next car
      }

      return new Input(field, cars);
    }

    public static Field parseField(List<String> lines) {
        String[] fieldSize = lines.get(0).split(" ");
        int width = Integer.parseInt(fieldSize[0]);
        int height = Integer.parseInt(fieldSize[1]);
        return new Field(width, height);
    }
    public static List<String> readLines(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }

    public static void writeOutputFile(String filePath, String output, Logger logger) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(output);
            logger.info(MessageFormat.format("Successfully written output to {0}", filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Command> convertToCommands(String input) {
        return input.chars()
                .mapToObj(c -> Command.valueOf(String.valueOf((char) c)))
                .collect(Collectors.toList());
    }
}
