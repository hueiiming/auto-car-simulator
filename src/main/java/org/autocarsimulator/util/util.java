package org.autocarsimulator.util;

import org.autocarsimulator.model.Command;
import org.autocarsimulator.model.Position;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class util {

    public static List<String> readLines(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }

    public static void writeOutputFile(String filePath, Position pos) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(pos.getX() + " " + pos.getY() + " " + pos.getDirection());
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
