package org.autocarsimulator.service;

import org.autocarsimulator.util.util;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class InputValidatorSingleServiceImpl implements InputValidatorService {
    @Override
    public void validateInput(String filePath, Logger logger) throws IOException {
        List<String> lines = util.readLines(filePath);

        // Validate number of lines
        if (lines.size() != 3) {
            throw new IllegalArgumentException("Input file must only have 3 lines.");
        }

        // Validate field dimensions
        if (!lines.get(0).matches("\\d+ \\d+")) {
            throw new IllegalArgumentException("First line must contain 2 integers, separated by a space.");
        }

        // Validate car position
        if (!lines.get(1).matches("\\d+ \\d+ [NSEW]")) {
            throw new IllegalArgumentException("Second line must contain two integers and a valid direction (N, S, E, W).");
        }

        // Validate commands
        if (!lines.get(2).matches("[LRF]+")) {
            throw new IllegalArgumentException("Third line must only contain a sequence of commands (L, R, F).");
        }

        logger.info("Input1 validation passed.");
    }
}
