package com.aleksey.super_array.comparator;

import com.aleksey.super_array.entity.SuperArray;
import com.aleksey.super_array.excepsion.CustomArrayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SuperArrayFirstElementComparatorTest {

    private SuperArrayFirstElementComparator comparator;
    private SuperArray startsWithOne;
    private SuperArray startsWithThree;
    private SuperArray anotherStartsWithOne;

    @BeforeEach
    void setUp() throws CustomArrayException {
        comparator = new SuperArrayFirstElementComparator();
        startsWithOne = SuperArray.builder(1, 5, 9).setId(11L).build();
        startsWithThree = SuperArray.builder(3, 1, -2).setId(12L).build();
        anotherStartsWithOne = SuperArray.builder(1, 0, 0).setId(13L).build();
    }

    @Test
    void shouldReturnNegativeWhenFirstElementIsLess() {
        int result = comparator.compare(startsWithOne, startsWithThree);
        assertTrue(result < 0, "1 should be less than 3");
    }

    @Test
    void shouldReturnPositiveWhenFirstElementIsGreater() {
        int result = comparator.compare(startsWithThree, startsWithOne);
        assertTrue(result > 0, "3 should be greater than 1");
    }

    @Test
    void shouldReturnZeroWhenFirstElementsAreEqual() {
        int result = comparator.compare(startsWithOne, anotherStartsWithOne);
        assertEquals(0, result, "Arrays starting with equal values should be considered equal");
    }
}

