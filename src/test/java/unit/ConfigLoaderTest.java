package unit;

import org.autocarsimulator.config.ConfigLoader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigLoaderTest {
    @Test
    public void testLoadProperties() throws IOException {
        String configPath = "src/test/resources/testConfig.properties";
        Properties properties = ConfigLoader.loadProperties(configPath);
        assertEquals("src/test/resources/input/input1.txt", properties.getProperty("input.filepath1"));
        assertEquals("src/test/resources/input/input2.txt", properties.getProperty("input.filepath2"));
        assertEquals("src/test/resources/output/output1.txt", properties.getProperty("output.filepath1"));
        assertEquals("src/test/resources/output/output2.txt", properties.getProperty("output.filepath2"));
    }

    @Test
    public void testLoadPropertiesFileNotFound() {
        String invalidConfigPath = "nonexistent.properties";

        Exception exception = assertThrows(IOException.class, () -> {
            ConfigLoader.loadProperties(invalidConfigPath);
        });
        assertTrue(exception instanceof java.io.FileNotFoundException);
    }
}
