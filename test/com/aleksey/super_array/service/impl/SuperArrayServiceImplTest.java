package com.aleksey.super_array.service.impl;

import com.aleksey.super_array.entity.SuperArray;
import com.aleksey.super_array.excepsion.CustomArrayException;

import com.aleksey.super_array.service.SuperArrayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


class SuperArrayServiceImplTest {

    private SuperArray superArray;
    private SuperArray superArrayNull;

    private SuperArrayService service;
    private int min = -5;
    private int max = 12;
    private int avgNum = 2;
    private int sum = 14;
    private int positive = 4;
    private int negative = 2;
    private int exceptionIndex = 15;
    private int[] initSuperArray = {1, -5, 12, 3, -4, 0, 7};
    private int[] expectedArrayWithReplacedElement = {1, -5, 12, 3, -4, 4, 7};
    @BeforeEach
    public void init() throws CustomArrayException {
        superArray = SuperArray.builder(initSuperArray).setId(1L).build();

        service = new SuperArrayServiceImpl();
    }

    @Test
    void shouldThrowException() {

        Throwable thrown = assertThrows(CustomArrayException.class, () -> superArrayNull = SuperArray.builder().build());

        assertEquals("The array cannot be empty.", thrown.getMessage());
    }

    @Test
    void shouldFindMin() {
        int actual = service.findMin(superArray);
        assertEquals(min, actual);
    }

    @Test
    void shouldFindMax() {
        int actual = service.findMax(superArray);
        assertEquals(max, actual);
    }

    @Test
    void shouldReplaceElementOfArray() throws CustomArrayException {
        int[] initialArray = Arrays.copyOf(superArray.getArray(), superArray.getArray().length);
        int[] expectedArray = expectedArrayWithReplacedElement;
        service.replaceElementOfArray(superArray, 5, 4);
        int[] actualArray = superArray.getArray();

        Throwable thrown = assertThrows(CustomArrayException.class,
                () -> service.replaceElementOfArray(superArray, exceptionIndex, 2000));

        assertEquals(String.format("Index %d is out of bounds. Array length: %d", exceptionIndex, superArray.getArray().length), thrown.getMessage());

        assertFalse(Arrays.equals(initialArray, actualArray));
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    void shouldCountAvgNumberOfArray() {
        int actualAvgNum = service.avgNumberOfArray(superArray);
        assertEquals(avgNum, actualAvgNum);
    }

    @Test
    void shouldCountSumOfArrayElements() {
        int actualSum = service.sumOfArrayElements(superArray);
        assertEquals(sum, actualSum);
    }

    @Test
    void shouldCountNumberOfPositiveElements() {
        int actualCount = service.numberOfPositiveElements(superArray);
        assertEquals(positive, actualCount);
    }

    @Test
    void numberOfNegativeElements() {
        int actualCount = service.numberOfNegativeElements(superArray);
        assertEquals(negative, actualCount);
    }
}