package com.aleksey.super_array.parser.impl;

import com.aleksey.super_array.excepsion.CustomArrayException;
import com.aleksey.super_array.reader.impl.SuperReaderImpl;
import com.aleksey.super_array.resources.string_lists_enum.StringsToTest;
import com.aleksey.super_array.validator.impl.StringValidatorImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SuperParserIntImplTest {

    private final SuperParserIntImpl parser = new SuperParserIntImpl();

    @TempDir
    Path tempDirectory;

    @Test
    void parseFileNumbers() throws CustomArrayException {
        Path file = tempDirectory.resolve("numbers.txt");
        try {
            Files.write(file, StringsToTest.STRING_TO_TEST.getStringList());
        } catch (IOException e) {
            throw new CustomArrayException(e.getMessage());
        }

        SuperReaderImpl reader = new SuperReaderImpl(tempDirectory, new StringValidatorImpl());
        List<String> lines = reader.superRead("numbers.txt");
        int[] result = parser.parse(lines);

        assertArrayEquals(new int[]{1, 2, 3, 10, 5, 4, 11, 2,
                3, 4, 1, 2, 3, 12, 5, 7, 10, 1, 3, 3, 4, 1, 6, 13, 5, 6, 1, 14,
                8, 11, 2, 1, 6, 1, 2, 9, 1, 2, 3, 11, 2, 1, 2, 3, 11, 2}, result);
    }
}

