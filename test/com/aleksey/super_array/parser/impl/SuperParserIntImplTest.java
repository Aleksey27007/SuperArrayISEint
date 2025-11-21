package com.aleksey.super_array.parser.impl;

import com.aleksey.super_array.entity.SuperArray;
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

import static org.junit.jupiter.api.Assertions.*;

class SuperParserIntImplTest {

    private final SuperParserIntImpl parser = new SuperParserIntImpl();

    @TempDir
    Path tempDirectory;

    @Test
    void parseFileNumbers() throws CustomArrayException, IOException {
        Path file = tempDirectory.resolve("numbers.txt");
        Files.write(file, StringsToTest.STRING_TO_TEST.getStringList());

        StringValidatorImpl validator = new StringValidatorImpl();
        SuperReaderImpl reader = new SuperReaderImpl(tempDirectory, validator);
        List<String> validatedLines = reader.superRead("numbers.txt");

        List<int[]> parsedArrays = parser.parse(validatedLines);
        int[] combinedArray = combineArrays(parsedArrays);

        SuperArray superArray = SuperArray.builder(combinedArray).setId(1L).build();

        assertNotNull(superArray);
        assertEquals(1L, superArray.getId());
        assertNotNull(superArray.getArray());
        assertNotEquals(0, superArray.getArray().length);
        assertArrayEquals(combinedArray, superArray.getArray());
    }

    private int[] combineArrays(List<int[]> arrays) {
        int totalLength = 0;
        for (int i = 0; i < arrays.size(); i++) {
            totalLength += arrays.get(i).length;
        }

        int[] result = new int[totalLength];
        int position = 0;
        for (int i = 0; i < arrays.size(); i++) {
            int[] array = arrays.get(i);
            System.arraycopy(array, 0, result, position, array.length);
            position += array.length;
        }
        return result;
    }
}

