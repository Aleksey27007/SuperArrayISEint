package com.aleksey.super_array.specification.impl;

import com.aleksey.super_array.entity.SuperArray;
import com.aleksey.super_array.excepsion.CustomArrayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IdSpecificationTest {

    private SuperArray arrayWithId42;
    private SuperArray differentArray;

    @BeforeEach
    void setUp() throws CustomArrayException {
        arrayWithId42 = SuperArray.builder(1, 2, 3).setId(42L).build();
        differentArray = SuperArray.builder(4, 5).setId(7L).build();
    }

    @Test
    void shouldReturnTrueWhenIdMatches() {
        IdSpecification specification = new IdSpecification(42L);
        assertTrue(specification.test(arrayWithId42));
    }

    @Test
    void shouldReturnFalseWhenIdDoesNotMatch() {
        IdSpecification specification = new IdSpecification(42L);
        assertFalse(specification.test(differentArray));
    }

    @Test
    void shouldReturnFalseWhenArrayIsNull() {
        IdSpecification specification = new IdSpecification(42L);
        assertFalse(specification.test(null));
    }
}

