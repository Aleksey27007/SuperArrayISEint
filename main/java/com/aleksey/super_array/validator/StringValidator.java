package com.aleksey.super_array.validator;


public class StringValidator {
    private static final String GOOD_LINE_REGEX = "(\\d+[; .-]*)+";

    public boolean isTheLineSuitable(String line) {
        return line.matches(GOOD_LINE_REGEX);
    }
}
