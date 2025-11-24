package com.aleksey.super_array.comparator;

import com.aleksey.super_array.entity.SuperArray;
import com.aleksey.super_array.excepsion.CustomArrayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuperArrayIdComparatorTest {

    private SuperArrayIdComparator comparator;
    private SuperArray arrayWithId1;
    private SuperArray arrayWithId2;
    private SuperArray arrayWithId3;

    @BeforeEach
    void setUp() throws CustomArrayException {
        comparator = new SuperArrayIdComparator();
        arrayWithId1 = SuperArray.builder(1, 2, 3).setId(1L).build();
        arrayWithId2 = SuperArray.builder(4, 5, 6).setId(2L).build();
        arrayWithId3 = SuperArray.builder(7, 8, 9).setId(3L).build();
    }

    @Test
    void shouldReturnNegativeWhenFirstIdIsLessThanSecond() {
        int result = comparator.compare(arrayWithId1, arrayWithId2);
        assertTrue(result < 0, "First array with id=1 should be less than second with id=2");
    }

    @Test
    void shouldReturnPositiveWhenFirstIdIsGreaterThanSecond() {
        int result = comparator.compare(arrayWithId2, arrayWithId1);
        assertTrue(result > 0, "First array with id=2 should be greater than second with id=1");
    }

    @Test
    void shouldReturnZeroWhenIdsAreEqual() {
        int result = comparator.compare(arrayWithId1, arrayWithId3);
        assertEquals(0, result, "Arrays with equal ids should return 0");
    }

    @Test
    void shouldHandleZeroId() throws CustomArrayException {
        SuperArray zeroId = SuperArray.builder(1).setId(0L).build();
        SuperArray positiveId = SuperArray.builder(2).setId(1L).build();

        int result = comparator.compare(zeroId, positiveId);
        assertTrue(result < 0, "Array with id=0 should be less than array with id=1");
    }
}

