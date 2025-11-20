package com.aleksey.super_array.service.impl;

import com.aleksey.super_array.parser.impl.SuperParserIntImpl;
import com.aleksey.super_array.resources.string_lists_enum.StringsToTest;
import com.aleksey.super_array.validator.impl.StringValidatorImpl;
import org.junit.jupiter.api.Test;

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

        int[] expected = Arrays.stream(sourceArray).sorted().toArray();
        int[] input = Arrays.copyOf(sourceArray, sourceArray.length);

        int[] actual = sortingService.quickSort(input);

        assertArrayEquals(expected, actual);
    }

    @Test
    void mergeSortShouldSortValidatedParsedNumbers() {
        int[] sourceArray = getValidatedParsedArray();
        assertNotEquals(0, sourceArray.length, "Source array should not be empty after validation");

        int[] expected = Arrays.stream(sourceArray).sorted().toArray();
        int[] input = Arrays.copyOf(sourceArray, sourceArray.length);

        int[] actual = sortingService.mergeSort(input, 0, input.length - 1);

        assertArrayEquals(expected, actual);
    }

    @Test
    void insertionSortShouldSortValidatedParsedNumbers() {
        int[] sourceArray = getValidatedParsedArray();
        assertNotEquals(0, sourceArray.length, "Source array should not be empty after validation");

        int[] expected = Arrays.stream(sourceArray).sorted().toArray();
        int[] input = Arrays.copyOf(sourceArray, sourceArray.length);

        int[] actual = sortingService.insertionSort(input);

        assertArrayEquals(expected, actual);
    }

    private int[] getValidatedParsedArray() {
        SuperParserIntImpl parser = new SuperParserIntImpl();
        List<String> validLines = StringsToTest.STRING_TO_TEST.getStringList().stream()
                .filter(line -> line != null && !line.isBlank())
                .filter(validator::isTheLineSuitable)
                .toList();

        return parser.parse(validLines);
    }
}