package com.aleksey.super_array.reader.impl;

import com.aleksey.super_array.excepsion.CustomArrayException;
import com.aleksey.super_array.validator.StringValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SuperReaderImplTest {

    @TempDir
    Path tempDirectory;

    @Test
    void superReadKeepsOnlyValidatedLines() throws IOException, CustomArrayException {
        Path file = tempDirectory.resolve("numbers.txt");
        Files.write(file, List.of(
                "1; 2; 3",
                "1; 2; x3; 6..5; 77 ",
                "",
                "11; 2"
        ));

        SuperReaderImpl reader = new SuperReaderImpl(tempDirectory, new StringValidator());

        assertEquals(List.of("1; 2; 3", "1; 2; x3; 6..5; 77 "), reader.superRead("numbers.txt"));
    }

    @Test
    void superReadThrowsIfFileMissing() throws CustomArrayException {
        SuperReaderImpl reader = new SuperReaderImpl(tempDirectory, new StringValidator());

        assertThrows(IllegalStateException.class, () -> reader.superRead("missing.txt"));
    }

    @Test
    void superReadRejectsBlankFileName() throws CustomArrayException {
        SuperReaderImpl reader = new SuperReaderImpl(tempDirectory, new StringValidator());

        assertThrows(IllegalArgumentException.class, () -> reader.superRead("  "));
    }
}