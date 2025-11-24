package com.aleksey.super_array.service.impl;

import com.aleksey.super_array.entity.SuperArray;
import com.aleksey.super_array.excepsion.CustomArrayException;
import com.aleksey.super_array.service.SuperArrayService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class StreamSuperArrayServiceImpl implements SuperArrayService {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public int findMin(SuperArray superArray) {
        return Arrays.stream(superArray.getArray())
                .min()
                .orElseThrow();
    }

    @Override
    public int findMax(SuperArray superArray) {
        return Arrays.stream(superArray.getArray())
                .max()
                .orElseThrow();
    }

    @Override
    public boolean replaceElementOfArray(SuperArray superArray, int index, int element) throws CustomArrayException {
        validateIndex(superArray, index);
        superArray.setElement(index, element);
        return true;
    }

    @Override
    public int avgNumberOfArray(SuperArray superArray) {
        return (int) Arrays.stream(superArray.getArray())
                .average()
                .orElseThrow();
    }

    @Override
    public int sumOfArrayElements(SuperArray superArray) {
        return Arrays.stream(superArray.getArray())
                .sum();
    }

    @Override
    public int numberOfPositiveElements(SuperArray superArray) {
        return (int) Arrays.stream(superArray.getArray())
                .filter(x -> x > 0)
                .count();
    }

    @Override
    public int numberOfNegativeElements(SuperArray superArray) {
        return (int) Arrays.stream(superArray.getArray())
                .filter(x -> x < 0)
                .count();
    }

    private void validateIndex(SuperArray superArray, int index) throws CustomArrayException {
        if (index < 0 || index >= superArray.getArray().length) {
            logger.log(Level.INFO, "Index out of bounds. Method validateIndex, StreamSuperArrayServiceImpl.class");
            throw new CustomArrayException(
                    String.format("Index %d is out of bounds. Array length: %d", index, superArray.getArray().length)
            );
        }
    }
}

