package org.autocarsimulator.controller;

import org.autocarsimulator.model.Input;
import org.autocarsimulator.model.Result;
import org.autocarsimulator.service.InputValidatorService;
import org.autocarsimulator.util.util;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarControllerExecutor {
    private final Logger logger;
    private final InputValidatorService singleCarValidator;
    private final InputValidatorService multiCarValidator;

    public CarControllerExecutor(InputValidatorService singleCarValidator, InputValidatorService multiCarValidator, Logger logger) {
        this.singleCarValidator = singleCarValidator;
        this.multiCarValidator = multiCarValidator;
        this.logger = logger;
    }

    public void executeSingleCarController(Properties properties, CarController singleCarController) {
        try {
            String inputFilePath = properties.getProperty("input.filepath1");
            singleCarValidator.validateInput(inputFilePath, logger);

            // Process after input is validated
            Input input = util.parseInput1(inputFilePath);
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
            String inputFilePath = properties.getProperty("input.filepath2");
            multiCarValidator.validateInput(inputFilePath, logger);

            // Process after input is validated
            Input input = util.parseInput2(inputFilePath);
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
