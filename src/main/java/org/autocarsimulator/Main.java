package org.autocarsimulator;

import org.autocarsimulator.controller.CarController;
import org.autocarsimulator.controller.MultiCarControllerImpl;
import org.autocarsimulator.controller.SingleCarControllerImpl;
import org.autocarsimulator.model.Input;
import org.autocarsimulator.model.Result;
import org.autocarsimulator.util.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/config.properties"));

            String inputFilePath1 = properties.getProperty("input.filepath1");
            List<String> lines1 = util.readLines(inputFilePath1);
            Input input1 = util.parseInput2(lines1);

            String inputFilePath2 = properties.getProperty("input.filepath2");
            List<String> lines2 = util.readLines(inputFilePath2);
            Input input2 = util.parseInput2(lines2);

            String outputFilePath1 = properties.getProperty("output.filepath1");
            String outputFilePath2 = properties.getProperty("output.filepath2");

            CarController singleCarController = new SingleCarControllerImpl();
            Result result1 = singleCarController.executeCarController(input1);
            util.writeOutputFile(outputFilePath1, result1.getResult());

            CarController multiCarController = new MultiCarControllerImpl();
            Result result2 = multiCarController.executeCarController(input2);
            util.writeOutputFile(outputFilePath2, result2.getResult());

            System.out.println("Run ended successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}