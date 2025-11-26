package com.aleksey.super_array.specification.impl;

import com.aleksey.super_array.entity.SuperArray;
import com.aleksey.super_array.excepsion.CustomArrayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsElementSpecificationTest {

    private ContainsElementSpecification specification;
    private SuperArray superArray;

    @BeforeEach
    void setUp() throws CustomArrayException {
        specification = new ContainsElementSpecification(5);
        superArray = SuperArray.builder(1, 5, -3, 7).setId(101L).build();
    }

    @Test
    void shouldReturnTrueWhenElementIsPresent() {
        assertTrue(specification.test(superArray));
    }

    @Test
    void shouldReturnFalseWhenElementIsAbsent() {
        ContainsElementSpecification missingElementSpecification = new ContainsElementSpecification(99);
        assertFalse(missingElementSpecification.test(superArray));
    }

    @Test
    void shouldReturnFalseWhenArrayIsNull() {
        assertFalse(specification.test(null));
    }
}

