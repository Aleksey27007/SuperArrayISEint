package com.aleksey.super_array.comparator;

import com.aleksey.super_array.entity.SuperArray;
import com.aleksey.super_array.excepsion.CustomArrayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SuperArrayLengthComparatorTest {

    private SuperArrayLengthComparator comparator;
    private SuperArray shortArray;
    private SuperArray longArray;
    private SuperArray anotherShortArray;

    @BeforeEach
    void setUp() throws CustomArrayException {
        comparator = new SuperArrayLengthComparator();
        shortArray = SuperArray.builder(1, 2).setId(21L).build();
        longArray = SuperArray.builder(3, 4, 5, 6).setId(22L).build();
        anotherShortArray = SuperArray.builder(7, 8).setId(23L).build();
    }

    @Test
    void shouldReturnNegativeWhenFirstArrayIsShorter() {
        int result = comparator.compare(shortArray, longArray);
        assertTrue(result < 0, "Shorter array should come before longer array");
    }

    @Test
    void shouldReturnPositiveWhenFirstArrayIsLonger() {
        int result = comparator.compare(longArray, shortArray);
        assertTrue(result > 0, "Longer array should come after shorter array");
    }

    @Test
    void shouldReturnZeroWhenLengthsAreEqual() {
        int result = comparator.compare(shortArray, anotherShortArray);
        assertEquals(0, result, "Arrays with the same length should be equal");
    }
}

