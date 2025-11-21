package com.aleksey.super_array.service.impl;

import com.aleksey.super_array.parser.impl.SuperParserIntImpl;
import com.aleksey.super_array.resources.string_lists_enum.StringsToTest;
import com.aleksey.super_array.validator.impl.StringValidatorImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortingSuperArrayServiceImplTest {

    private final SortingSuperArrayServiceImpl sortingService = new SortingSuperArrayServiceImpl();
    private final StringValidatorImpl validator = new StringValidatorImpl();

    @Test
    void quickSortShouldSortValidatedParsedNumbers() {
        int[] sourceArray = getValidatedParsedArray();
        assertNotEquals(0, sourceArray.length, "Source array should not be empty after validation");

        int[] expected = sortArray(sourceArray);
        int[] input = Arrays.copyOf(sourceArray, sourceArray.length);

        int[] actual = sortingService.quickSort(input);

        assertArrayEquals(expected, actual);
    }

    @Test
    void mergeSortShouldSortValidatedParsedNumbers() {
        int[] sourceArray = getValidatedParsedArray();
        assertNotEquals(0, sourceArray.length, "Source array should not be empty after validation");

        int[] expected = sortArray(sourceArray);
        int[] input = Arrays.copyOf(sourceArray, sourceArray.length);

        int[] actual = sortingService.mergeSort(input, 0, input.length - 1);

        assertArrayEquals(expected, actual);
    }

    @Test
    void insertionSortShouldSortValidatedParsedNumbers() {
        int[] sourceArray = getValidatedParsedArray();
        assertNotEquals(0, sourceArray.length, "Source array should not be empty after validation");

        int[] expected = sortArray(sourceArray);
        int[] input = Arrays.copyOf(sourceArray, sourceArray.length);

        int[] actual = sortingService.insertionSort(input);

        assertArrayEquals(expected, actual);
    }

    private int[] getValidatedParsedArray() {
        SuperParserIntImpl parser = new SuperParserIntImpl();
        List<String> allLines = StringsToTest.STRING_TO_TEST.getStringList();
        List<String> validLines = new ArrayList<>();
        
        for (int i = 0; i < allLines.size(); i++) {
            String line = allLines.get(i);
            if (line != null && !line.isBlank() && validator.isTheLineSuitable(line)) {
                validLines.add(line);
            }
        }

        List<int[]> parsedArrays = parser.parse(validLines);
        
        int totalLength = 0;
        for (int i = 0; i < parsedArrays.size(); i++) {
            totalLength += parsedArrays.get(i).length;
        }
        
        int[] result = new int[totalLength];
        int position = 0;
        for (int i = 0; i < parsedArrays.size(); i++) {
            int[] array = parsedArrays.get(i);
            System.arraycopy(array, 0, result, position, array.length);
            position += array.length;
        }
        
        return result;
    }

    private int[] sortArray(int[] array) {
        int[] sorted = Arrays.copyOf(array, array.length);
        for (int i = 0; i < sorted.length - 1; i++) {
            for (int j = 0; j < sorted.length - i - 1; j++) {
                if (sorted[j] > sorted[j + 1]) {
                    int temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                }
            }
        }
        return sorted;
    }
}