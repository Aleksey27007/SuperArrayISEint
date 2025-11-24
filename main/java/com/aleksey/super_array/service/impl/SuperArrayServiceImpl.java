package com.aleksey.super_array.service.impl;

import com.aleksey.super_array.entity.SuperArray;
import com.aleksey.super_array.excepsion.CustomArrayException;
import com.aleksey.super_array.service.SuperArrayService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SuperArrayServiceImpl implements SuperArrayService {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public int findMin(SuperArray superArray) {
        int[] array = superArray.getArray();
        int min = array[0];
        for (int value : array) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    @Override
    public int findMax(SuperArray superArray) {
        int[] array = superArray.getArray();
        int max = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    @Override
    public boolean replaceElementOfArray(SuperArray superArray, int index, int element) throws CustomArrayException {
        validateIndex(superArray, index);
        superArray.setElement(index, element);
        return true;
    }

    @Override
    public int avgNumberOfArray(SuperArray superArray) {
        return sumOfArrayElements(superArray) / superArray.getArray().length;
    }

    @Override
    public int sumOfArrayElements(SuperArray superArray) {
        int[] array = superArray.getArray();
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }

    @Override
    public int numberOfPositiveElements(SuperArray superArray) {
        int[] array = superArray.getArray();
        int count = 0;
        for (int value : array) {
            if (value > 0) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int numberOfNegativeElements(SuperArray superArray) {
        int[] array = superArray.getArray();
        int count = 0;
        for (int value : array) {
            if (value < 0) {
                count++;
            }
        }
        return count;
    }

    private void validateIndex(SuperArray superArray, int index) throws CustomArrayException {
        if (index < 0 || index >= superArray.getArray().length) {
            logger.log(Level.INFO, "Index out of bounds. Method validateIndex, SuperArrayServiceIml.class");
            throw new CustomArrayException(
                    String.format("Index %d is out of bounds. Array length: %d", index, superArray.getArray().length)
            );
        }
    }
}
