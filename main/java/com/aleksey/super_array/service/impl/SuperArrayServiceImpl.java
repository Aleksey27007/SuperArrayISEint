package com.aleksey.super_array.service.impl;

import com.aleksey.super_array.entity.SuperArray;
import com.aleksey.super_array.excepsion.SuperArrayIndexOutOfBoundsException;
import com.aleksey.super_array.excepsion.SuperArrayNullPointerException;
import com.aleksey.super_array.service.SuperArrayService;

public class SuperArrayServiceImpl implements SuperArrayService {

    private final int length;
    private final int[] array;

    public SuperArrayServiceImpl(SuperArray superArray) {
        if (superArray.getArray().length == 0) {
            throw new SuperArrayNullPointerException("The array cannot be empty.");
        }
        this.length = superArray.getArray().length;
        this.array = superArray.getArray();
    }


    @Override
    public int findMin() {
        int min = array[0];
        for (int i = 0; i < length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    @Override
    public int findMax() {
        int max = array[0];
        for (int i = 0; i < length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    @Override
    public boolean replaceElementOfArray(int index, int element) {
        validateIndex(index);
        array[index] = element;
        return true;
    }

    @Override
    public int avgNumberOfArray() {
        return sumOfArrayElements() / length;
    }

    @Override
    public int sumOfArrayElements() {
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += array[i];
        }
        return sum;
    }

    @Override
    public int numberOfPositiveElements() {
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (array[i] > 0) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int numberOfNegativeElements() {
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (array[i] < 0) {
                count++;
            }
        }
        return count;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= length) {
            throw new SuperArrayIndexOutOfBoundsException(
                    String.format("Index %d is out of bounds. Array length: %d", index, length)
            );
        }
    }
}
