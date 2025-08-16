package org.autocarsimulator;

import org.autocarsimulator.config.ConfigLoader;
import org.autocarsimulator.controller.CarController;
import org.autocarsimulator.controller.CarControllerExecutor;
import org.autocarsimulator.controller.MultiCarControllerImpl;
import org.autocarsimulator.controller.SingleCarControllerImpl;
import org.autocarsimulator.service.*;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final String CONFIG_FILE = "src/main/resources/config.properties";

    public static void main(String[] args) {
        try {
            Properties properties = ConfigLoader.loadProperties(CONFIG_FILE);
            LOGGER.info("Properties file loaded successfully.");

            InputValidatorService inputValidatorSingleService = new InputValidatorSingleServiceImpl();
            InputValidatorService inputValidatorMultiServiceService = new InputValidatorMultiServiceImpl();
            CarControllerExecutor carControllerExecutor = new CarControllerExecutor(inputValidatorSingleService, inputValidatorMultiServiceService, LOGGER);
            CarService carService = new CarServiceImpl(LOGGER);

            CarController singleCarController = new SingleCarControllerImpl(carService, LOGGER);
            carControllerExecutor.executeSingleCarController(properties, singleCarController);

            CarController multiCarController = new MultiCarControllerImpl(carService, LOGGER);
            carControllerExecutor.executeMultiCarController(properties, multiCarController);

            LOGGER.info("Run ended successfully");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An error occurred while accessing files: {0}", e.getMessage());
            LOGGER.log(Level.SEVERE, "Exception details: ", e);
        }
    }
}