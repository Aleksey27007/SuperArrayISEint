package com.aleksey.super_array.service;

import com.aleksey.super_array.excepsion.CustomArrayException;

public interface SuperArrayService {
    int findMin(int[] array);
    int findMax(int[] array);
    boolean replaceElementOfArray(int[] array, int index, int element) throws CustomArrayException;
    int avgNumberOfArray(int[] array);
    int sumOfArrayElements(int[] array);
    int numberOfPositiveElements(int[] array);
    int numberOfNegativeElements(int[] array);
}
