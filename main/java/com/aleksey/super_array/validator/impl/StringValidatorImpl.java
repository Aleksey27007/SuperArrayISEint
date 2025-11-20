package com.aleksey.super_array.validator.impl;


import com.aleksey.super_array.validator.StringValidator;

public class StringValidatorImpl implements StringValidator {
    private static final String GOOD_LINE_REGEX = "(\\d+[; ,.-]*)+";
    @Override
    public boolean isTheLineSuitable(String line) {
        return line.matches(GOOD_LINE_REGEX);
    }
}
