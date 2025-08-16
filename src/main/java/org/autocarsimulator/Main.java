package org.autocarsimulator;

import org.autocarsimulator.config.ConfigLoader;
import org.autocarsimulator.controller.CarController;
import org.autocarsimulator.controller.MultiCarControllerImpl;
import org.autocarsimulator.controller.SingleCarControllerImpl;
import org.autocarsimulator.model.Input;
import org.autocarsimulator.model.Result;
import org.autocarsimulator.service.CarService;
import org.autocarsimulator.service.CarServiceImpl;
import org.autocarsimulator.util.util;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            Properties properties = ConfigLoader.loadProperties("src/main/resources/config.properties");
            LOGGER.log(Level.INFO, "Properties file loaded successfully.");

            Input input1 = util.parseInput1(properties.getProperty("input.filepath1"));
            Input input2 = util.parseInput2(properties.getProperty("input.filepath2"));

            String outputFilePath1 = properties.getProperty("output.filepath1");
            String outputFilePath2 = properties.getProperty("output.filepath2");

            CarService carService = new CarServiceImpl();

            CarController singleCarController = new SingleCarControllerImpl(carService);
            Result result1 = singleCarController.executeCarController(input1);
            util.writeOutputFile(outputFilePath1, result1.getResult());
            LOGGER.log(Level.INFO, "SingleController execution completed.");

            CarController multiCarController = new MultiCarControllerImpl(carService);
            Result result2 = multiCarController.executeCarController(input2);
            util.writeOutputFile(outputFilePath2, result2.getResult());
            LOGGER.log(Level.INFO, "MultiCarController execution completed.");

            LOGGER.log(Level.INFO, "Run ended successfully");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An error occurred while accessing files: {0}", e.getMessage());
            LOGGER.log(Level.SEVERE, "Exception details: ", e);
        }
    }
}