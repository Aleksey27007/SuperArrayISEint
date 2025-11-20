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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SuperReaderImplTest {

    @TempDir
    Path tempDirectory;

    private List<String> actualList = Arrays.asList("1; 2; 3", "10; 5;; 4",
            "1;3;3;4;1;6", "13;5; 6; 1; 14. 8", "11; 2; 1 ; 6; 1; 2; 9", "1; 2; 3",
            "11; 2", "1; 2; 3", "11; 2");

    @Test
    void superReadKeepsOnlyValidatedLines() throws IOException, CustomArrayException {
        Path file = tempDirectory.resolve("numbers.txt");

        Files.write(file, StringsToTest.STRING_TO_TEST.getStringList());

        SuperReaderImpl reader = new SuperReaderImpl(tempDirectory, new StringValidatorImpl());

        assertEquals(actualList, reader.superRead("numbers.txt"));
    }

    @Test
    void superReadThrowsIfFileMissing() throws CustomArrayException {
        SuperReaderImpl reader = new SuperReaderImpl(tempDirectory, new StringValidatorImpl());

        assertThrows(IllegalStateException.class, () -> reader.superRead("missing.txt"));
    }

    @Test
    void superReadRejectsBlankFileName() throws CustomArrayException {
        SuperReaderImpl reader = new SuperReaderImpl(tempDirectory, new StringValidatorImpl());

        assertThrows(IllegalArgumentException.class, () -> reader.superRead("  "));
    }
}