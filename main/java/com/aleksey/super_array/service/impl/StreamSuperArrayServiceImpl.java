package com.aleksey.super_array.service.impl;

import com.aleksey.super_array.excepsion.CustomArrayException;
import com.aleksey.super_array.service.SuperArrayService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class StreamSuperArrayServiceImpl implements SuperArrayService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public int findMin(int[] array) {
        return Arrays.stream(array)
                .min()
                .orElseThrow();
    }

    @Override
    public int findMax(int[] array) {
        return Arrays.stream(array)
                .max()
                .orElseThrow();
    }

    @Override
    public boolean replaceElementOfArray(int[] array, int index, int element) throws CustomArrayException {
        validateIndex(array, index);
        array[index] = element;
        return true;
    }

    @Override
    public int avgNumberOfArray(int[] array) {
        return (int) Arrays.stream(array)
                .average()
                .orElseThrow();
    }

    @Override
    public int sumOfArrayElements(int[] array) {
        return Arrays.stream(array)
                .sum();
    }

    @Override
    public int numberOfPositiveElements(int[] array) {
        return (int) Arrays.stream(array)
                .filter(x -> x > 0)
                .count();
    }

    @Override
    public int numberOfNegativeElements(int[] array) {
        return (int) Arrays.stream(array)
                .filter(x -> x < 0)
                .count();
    }

    private void validateIndex(int[] array, int index) throws CustomArrayException {
        if (index < 0 || index >= array.length) {
            logger.log(Level.INFO, "Index out of bounds. Method validateIndex, StreamSuperArrayServiceImpl.class");
            throw new CustomArrayException(
                    String.format("Index %d is out of bounds. Array length: %d", index, array.length)
            );
        }
    }
}

