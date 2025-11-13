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
    public int[] mergeSort(int[] array, int left, int right) { // O(n log n)
        if (left < right) {
            // Находим середину массива
            int middle = left + (right - left) / 2;

            // Рекурсивно сортируем левую и правую половины
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);

            // Сливаем отсортированные половины
            merge(array, left, middle, right);
        }
        return array;
    }

    private static void merge(int[] array, int left, int middle, int right) {
        // Создаем временные массивы для хранения данных
        int size1 = middle - left + 1;
        int size2 = right - middle;

        int[] leftArray = new int[size1];
        int[] rightArray = new int[size2];

        // Копируем данные во временные массивы
        System.arraycopy(array, left, leftArray, 0, size1);
        for (int j = 0; j < size2; ++j) {
            rightArray[j] = array[middle + 1 + j];
        }
        // Сливаем временные массивы обратно в основной массив array
        int i = 0, j = 0, k = left;
        while (i < size1 && j < size2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }
        // Копируем оставшиеся элементы leftArray[], если они есть
        while (i < size1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }
        // Копируем оставшиеся элементы rightArray[], если они есть
        while (j < size2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    @Override
    public int[] insertionSort(int[] array) { // O(n^2)
        return new int[0];
    }
}
