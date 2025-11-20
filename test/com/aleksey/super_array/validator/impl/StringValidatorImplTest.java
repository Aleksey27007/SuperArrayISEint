package com.aleksey.super_array.validator.impl;

import com.aleksey.super_array.resources.string_lists_enum.StringsToTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringValidatorImplTest {

    private final StringValidatorImpl validator = new StringValidatorImpl();

    @Test
    void testValidStrings() {
        assertTrue(validator.isTheLineSuitable("1; 2; 3"));
        assertTrue(validator.isTheLineSuitable("1;3;3;4;1;6"));
        assertTrue(validator.isTheLineSuitable("13;5; 6; 1; 14. 8"));
        assertTrue(validator.isTheLineSuitable("11; 2; 1 ; 6; 1; 2; 9"));
        assertTrue(validator.isTheLineSuitable("11; 2"));
        assertTrue(validator.isTheLineSuitable("10; 5;; 4"));
        assertTrue(validator.isTheLineSuitable("11; 2; 3  4,"));
        assertTrue(validator.isTheLineSuitable("1, 2, 3 , 12, 5, 7; 10"));
    }

    @Test
    void testInvalidStrings() {
        assertFalse(validator.isTheLineSuitable(""));
        assertFalse(validator.isTheLineSuitable("1; 2; x3; 6..5; 77 "));
        assertFalse(validator.isTheLineSuitable("13; 12, 11a"));
        assertFalse(validator.isTheLineSuitable("1q; 2; 3a"));
        assertFalse(validator.isTheLineSuitable("qass"));
    }

    @Test
    void testAllStringsFromEnum() {
        StringsToTest.STRING_TO_TEST.getStringList().forEach(line -> {
            boolean isValid = validator.isTheLineSuitable(line);
            System.out.println("Line: '" + line + "' -> Valid: " + isValid);
        });
    }

    @Test
    void testEmptyString() {
        assertFalse(validator.isTheLineSuitable(""));
        assertFalse(validator.isTheLineSuitable("   "));
    }

    @Test
    void testStringWithOnlyDigits() {
        assertTrue(validator.isTheLineSuitable("123"));
        assertTrue(validator.isTheLineSuitable("1"));
    }

    @Test
    void testStringWithLetters() {
        assertFalse(validator.isTheLineSuitable("abc"));
        assertFalse(validator.isTheLineSuitable("1a"));
        assertFalse(validator.isTheLineSuitable("a1"));
    }

    @Test
    void testStringWithSeparators() {
        assertTrue(validator.isTheLineSuitable("1;2;3"));
        assertTrue(validator.isTheLineSuitable("1.2.3"));
        assertTrue(validator.isTheLineSuitable("1-2-3"));
        assertTrue(validator.isTheLineSuitable("1 2 3"));
        assertTrue(validator.isTheLineSuitable("1,2,3"));
    }
}