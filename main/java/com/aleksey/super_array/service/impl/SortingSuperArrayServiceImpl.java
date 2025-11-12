package com.aleksey.super_array.service.impl;

import com.aleksey.super_array.service.SortingSuperArrayService;

public class SortingSuperArrayServiceImpl implements SortingSuperArrayService {
    @Override
    public int[] quickSort(int[] array) { // O(n log n) - в среднем, O(n^2) - в худшем
        quickSort(array, 0, array.length - 1);
        return array;
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    @Override
    public int[] mergeSort() { // O(n log n)
        return new int[0];
    }

    @Override
    public int[] insertionSort() { // O(n^2)
        return new int[0];
    }
}
