package org.autocarsimulator;

import org.autocarsimulator.controller.CarController;
import org.autocarsimulator.model.Position;
import org.autocarsimulator.util.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/config.properties"));

            String inputFilePath = properties.getProperty("input.filepath");
            String outputFilePath = properties.getProperty("output.filepath");

            CarController carController = new CarController();
            Position finalPos = carController.run(inputFilePath);
            util.writeOutputFile(outputFilePath, finalPos);

            System.out.println("Run ended successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}