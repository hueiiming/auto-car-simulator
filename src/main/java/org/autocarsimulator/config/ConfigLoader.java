package org.autocarsimulator.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    public static Properties loadProperties(String filePath) throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(filePath);
        properties.load(fis);
        return properties;
    }
}
