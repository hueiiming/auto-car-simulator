package org.autocarsimulator.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class util {

    public static List<String> readLines(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }
}
