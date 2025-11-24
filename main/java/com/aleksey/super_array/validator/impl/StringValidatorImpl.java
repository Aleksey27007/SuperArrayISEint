package com.aleksey.super_array.validator.impl;


import com.aleksey.super_array.validator.StringValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StringValidatorImpl implements StringValidator {
    private static final String VALID_NUMERIC_LINE_REGEX = "\\s*\\d+(\\s*[; ,.]\\s*\\d+)*\\s*";
    private static final Logger logger = LogManager.getLogger();
    
    @Override
    public boolean isTheLineSuitable(String line) {
        if (line == null || line.isBlank()) {
            logger.warn("This line is empty.");
            return false;
        }
        return line.matches(VALID_NUMERIC_LINE_REGEX);
    }
}
