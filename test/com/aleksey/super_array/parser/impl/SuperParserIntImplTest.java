package com.aleksey.super_array.parser.impl;

import com.aleksey.super_array.excepsion.CustomArrayException;
import com.aleksey.super_array.reader.impl.SuperReaderImpl;
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
        Path file = tempDirectory.resolve("fileNumbers");
        try {
            Files.write(file, List.of(
                    "1; 2; 3;",
                    "1; 2; x3; 6..5; 77",
                    "",
                    "11; 2"
            ));
        } catch (IOException e) {
            throw new CustomArrayException(e.getMessage());
        }

        SuperReaderImpl reader = new SuperReaderImpl(tempDirectory, new StringValidatorImpl());
        List<String> lines = reader.superRead("fileNumbers");
        int[] result = parser.parse(lines);

        assertArrayEquals(new int[]{1, 2, 3, 11, 2}, result);
    }
}

