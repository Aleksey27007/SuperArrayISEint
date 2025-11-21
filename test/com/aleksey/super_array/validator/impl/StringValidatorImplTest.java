package com.aleksey.super_array.validator.impl;

import com.aleksey.super_array.excepsion.CustomArrayException;
import com.aleksey.super_array.reader.impl.SuperReaderImpl;
import com.aleksey.super_array.resources.string_lists_enum.StringsToTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringValidatorImplTest {

    private final StringValidatorImpl validator = new StringValidatorImpl();

    @TempDir
    Path tempDirectory;

    @Test
    void testValidateStringsFromFile() throws IOException, CustomArrayException {
        Path file = tempDirectory.resolve("test_numbers.txt");
        List<String> testStrings = StringsToTest.STRING_TO_TEST.getStringList();
        
        Files.write(file, testStrings);

        List<String> allLinesFromFile = Files.readAllLines(file);
        
        SuperReaderImpl reader = new SuperReaderImpl(tempDirectory, validator);
        List<String> validatedLines = reader.superRead("test_numbers.txt");

        for (int i = 0; i < allLinesFromFile.size(); i++) {
            String line = allLinesFromFile.get(i);
            boolean isValid = validator.isTheLineSuitable(line);
            
            boolean expectedValid = line != null 
                    && !line.isBlank() 
                    && line.matches("\\s*\\d+(\\s*[; ,.]\\s*\\d+)*\\s*");
            
            assertEquals(expectedValid, isValid, 
                    "Validation failed for line at index " + i + ": '" + line + "'");
            
            if (expectedValid) {
                assertTrue(validatedLines.contains(line), 
                        "Valid line '" + line + "' should be in validated lines list");
            } else {
                assertFalse(validatedLines.contains(line), 
                        "Invalid line '" + line + "' should not be in validated lines list");
            }
        }
    }
}