package e2e;

import org.autocarsimulator.controller.CarController;
import org.autocarsimulator.controller.MultiCarControllerImpl;
import org.autocarsimulator.controller.SingleCarControllerImpl;
import org.autocarsimulator.model.Input;
import org.autocarsimulator.model.Result;
import org.autocarsimulator.util.util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoCarIntegrationTest {

    @Test
    public void testSingleCarWithMockedInputFile() throws Exception {

        String inputFilePath = "src/test/resources/input/input1.txt";
        String outputFilePath = "src/test/resources/output/output1.txt";
        List<String> inputLines = Files.readAllLines(new File(inputFilePath).toPath());
        Input input = util.parseInput1(inputLines);

        CarController singleCarController = new SingleCarControllerImpl();
        Result result = singleCarController.executeCarController(input);
        util.writeOutputFile(outputFilePath, result.getResult());

        List<String> output = Files.readAllLines(new File(outputFilePath).toPath());

        assertEquals(List.of("4 3 S"), output);
    }

    @Test
    public void testMultiCarWithMockedInputFile() throws Exception {

        String inputFilePath = "src/test/resources/input/input2.txt";
        String outputFilePath = "src/test/resources/output/output2.txt";
        List<String> inputLines = Files.readAllLines(new File(inputFilePath).toPath());
        Input input = util.parseInput2(inputLines);

        CarController multiCarController = new MultiCarControllerImpl();
        Result result = multiCarController.executeCarController(input);
        util.writeOutputFile(outputFilePath, result.getResult());

        List<String> output = Files.readAllLines(new File(outputFilePath).toPath());

        assertEquals(List.of("B A", "5 4", "7"), output);
    }
}
