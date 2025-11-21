package com.aleksey.super_array.reader.impl;

import com.aleksey.super_array.excepsion.CustomArrayException;
import com.aleksey.super_array.resources.string_lists_enum.StringsToTest;
import com.aleksey.super_array.validator.impl.StringValidatorImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SuperReaderImplTest {

    @TempDir
    Path tempDirectory;

    @Test
    void superReadKeepsOnlyValidatedLines() throws IOException, CustomArrayException {
        Path file = tempDirectory.resolve("numbers.txt");
        List<String> testStrings = StringsToTest.STRING_TO_TEST.getStringList();
        
        Files.write(file, testStrings);

        StringValidatorImpl validator = new StringValidatorImpl();
        SuperReaderImpl reader = new SuperReaderImpl(tempDirectory, validator);
        List<String> result = reader.superRead("numbers.txt");

        List<String> expectedList = new ArrayList<>();
        for (int i = 0; i < testStrings.size(); i++) {
            String line = testStrings.get(i);
            if (validator.isTheLineSuitable(line)) {
                expectedList.add(line);
            }
        }

        assertEquals(expectedList, result);
    }

    @Test
    void superReadThrowsIfFileMissing() throws CustomArrayException {
        SuperReaderImpl reader = new SuperReaderImpl(tempDirectory, new StringValidatorImpl());

        assertThrows(CustomArrayException.class, () -> reader.superRead("missing.txt"));
    }

    @Test
    void superReadRejectsBlankFileName() throws CustomArrayException {
        SuperReaderImpl reader = new SuperReaderImpl(tempDirectory, new StringValidatorImpl());

        assertThrows(CustomArrayException.class, () -> reader.superRead("  "));
    }
}