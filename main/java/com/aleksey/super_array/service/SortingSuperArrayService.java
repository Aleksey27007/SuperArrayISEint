package com.aleksey.super_array.service;

public interface SortingSuperArrayService {
    int[] quickSort(int[] array);
    int[] mergeSort(int[] array, int left, int right);
    int[] insertionSort(int[] array);
}
