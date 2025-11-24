package com.aleksey.super_array.service;

import com.aleksey.super_array.entity.SuperArray;
import com.aleksey.super_array.excepsion.CustomArrayException;

public interface SuperArrayService {
    int findMin(SuperArray superArray);
    int findMax(SuperArray superArray);
    boolean replaceElementOfArray(SuperArray superArray, int index, int element) throws CustomArrayException;
    int avgNumberOfArray(SuperArray superArray);
    int sumOfArrayElements(SuperArray superArray);
    int numberOfPositiveElements(SuperArray superArray);
    int numberOfNegativeElements(SuperArray superArray);
}
