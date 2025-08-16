package org.autocarsimulator.service;

import java.io.IOException;
import java.util.logging.Logger;

public interface InputValidatorService {
    void validateInput(String filePath, Logger logger) throws IOException;
}
