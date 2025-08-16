package org.autocarsimulator.controller;

import org.autocarsimulator.model.Input;
import org.autocarsimulator.model.Result;
import org.autocarsimulator.util.util;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarControllerExecutor {
    private final Logger logger;

    public CarControllerExecutor(Logger logger) {
        this.logger = logger;
    }

    public void executeSingleCarController(Properties properties, CarController singleCarController) {
        try {
            Input input = util.parseInput1(properties.getProperty("input.filepath1"));
            String outputFilePath = properties.getProperty("output.filepath1");

            Result result = singleCarController.executeCarController(input);
            util.writeOutputFile(outputFilePath, result.getResult(), logger);
            logger.log(Level.INFO, "SingleCarController execution completed.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error during SingleCarController execution: {0}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void executeMultiCarController(Properties properties, CarController multiCarController) {
        try {
            Input input = util.parseInput2(properties.getProperty("input.filepath2"));
            String outputFilePath = properties.getProperty("output.filepath2");

            Result result = multiCarController.executeCarController(input);
            util.writeOutputFile(outputFilePath, result.getResult(), logger);
            logger.log(Level.INFO, "MultiCarController execution completed.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error during MultiCarController execution: {0}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
