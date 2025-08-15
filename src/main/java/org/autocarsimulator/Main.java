package org.autocarsimulator;

import org.autocarsimulator.controller.CarController;
import org.autocarsimulator.controller.MultiCarControllerImpl;
import org.autocarsimulator.controller.SingleCarControllerImpl;
import org.autocarsimulator.model.Input;
import org.autocarsimulator.model.Result;
import org.autocarsimulator.service.CarService;
import org.autocarsimulator.service.CarServiceImpl;
import org.autocarsimulator.util.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/config.properties"));
            LOGGER.log(Level.INFO, "Properties file loaded successfully.");

            String inputFilePath1 = properties.getProperty("input.filepath1");
            List<String> lines1 = util.readLines(inputFilePath1);
            Input input1 = util.parseInput2(lines1);

            String inputFilePath2 = properties.getProperty("input.filepath2");
            List<String> lines2 = util.readLines(inputFilePath2);
            Input input2 = util.parseInput2(lines2);

            String outputFilePath1 = properties.getProperty("output.filepath1");
            String outputFilePath2 = properties.getProperty("output.filepath2");

            CarService carService = new CarServiceImpl();

            LOGGER.log(Level.INFO, "Executing SingleCarController.");
            CarController singleCarController = new SingleCarControllerImpl(carService);
            Result result1 = singleCarController.executeCarController(input1);
            util.writeOutputFile(outputFilePath1, result1.getResult());
            LOGGER.log(Level.INFO, "SingleCarController execution completed.");

            LOGGER.log(Level.INFO, "Executing MultiCarController.");
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