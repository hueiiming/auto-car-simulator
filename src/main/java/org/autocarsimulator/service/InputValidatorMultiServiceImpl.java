package org.autocarsimulator.service;

import org.autocarsimulator.util.util;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class InputValidatorMultiServiceImpl implements InputValidatorService {
    @Override
    public void validateInput(String filePath, Logger logger) throws IOException {
        List<String> lines = util.readLines(filePath);

        // Validate number of lines
        if (lines.size() < 3) {
            throw new IllegalArgumentException("Input file must have at least 3 lines.");
        }

        // Validate field dimensions
        if (!lines.get(0).matches("\\d+ \\d+")) {
            throw new IllegalArgumentException("First line must contain two integers separated by a space.");
        }

        // Validate multiple cars
        int i = 2;
        while (i < lines.size() - 2) {
            if (lines.get(i).isEmpty()) {
                throw new IllegalArgumentException("Car name cannot be empty.");
            }

            if (!lines.get(i).matches(".*\\D.*")) {
                throw new IllegalArgumentException("Car name must contain at least one non-digit character.");
            }

            if (!lines.get(i + 1).matches("\\d+ \\d+ [NSEW]")) {
                throw new IllegalArgumentException("Car position must contain two integers and a valid direction (N, S, E, W).");
            }

            if (!lines.get(i + 2).matches("[LRF]+")) {
                throw new IllegalArgumentException("Car commands must contain a sequence of commands (L, R, F).");
            }

            i += 4; // Move to the next car
        }

        logger.info("Input2 validation passed.");
    }
}
