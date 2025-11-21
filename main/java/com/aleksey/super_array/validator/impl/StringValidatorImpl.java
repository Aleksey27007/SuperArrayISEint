package com.aleksey.super_array.validator.impl;


import com.aleksey.super_array.validator.StringValidator;

public class StringValidatorImpl implements StringValidator {
    private static final String VALID_NUMERIC_LINE_REGEX = "\\s*\\d+(\\s*[; ,.]\\s*\\d+)*\\s*";
    
    @Override
    public boolean isTheLineSuitable(String line) {
        if (line == null || line.isBlank()) {
            return false;
        }
        return line.matches(VALID_NUMERIC_LINE_REGEX);
    }
}
