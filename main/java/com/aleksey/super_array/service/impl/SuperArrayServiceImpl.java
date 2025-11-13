package com.aleksey.super_array.service.impl;

import com.aleksey.super_array.excepsion.CustomArrayException;
import com.aleksey.super_array.service.SuperArrayService;

public class SuperArrayServiceImpl implements SuperArrayService {
    @Override
    public int findMin(int[] array) {
        int min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    @Override
    public int findMax(int[] array) {
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    @Override
    public boolean replaceElementOfArray(int[] array, int index, int element) throws CustomArrayException {
        validateIndex(array, index);
        array[index] = element;
        return true;
    }

    @Override
    public int avgNumberOfArray(int[] array) {
        return sumOfArrayElements(array) / array.length;
    }

    @Override
    public int sumOfArrayElements(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

    @Override
    public int numberOfPositiveElements(int[] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int numberOfNegativeElements(int[] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                count++;
            }
        }
        return count;
    }

    private void validateIndex(int[] array, int index) throws CustomArrayException {
        if (index < 0 || index >= array.length) {
            throw new CustomArrayException(
                    String.format("Index %d is out of bounds. Array length: %d", index, array.length)
            );
        }
    }
}
