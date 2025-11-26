package com.aleksey.super_array.specification.impl;

import com.aleksey.super_array.entity.SuperArray;
import com.aleksey.super_array.excepsion.CustomArrayException;
import com.aleksey.super_array.specification.specification_enum.StatisticComparisonType;
import com.aleksey.super_array.specification.specification_enum.StatisticType;
import com.aleksey.super_array.warehouse.Warehouse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StatisticSpecificationTest {

    private final Warehouse warehouse = Warehouse.getInstance();
    private final Set<Long> idsToCleanup = new HashSet<>();

    @AfterEach
    void tearDown() {
        idsToCleanup.forEach(warehouse::remove);
        idsToCleanup.clear();
    }

    @Test
    void shouldReturnTrueWhenStatisticMatchesWithoutPreExistingStats() throws CustomArrayException {
        SuperArray array = createArray(1001L, 2, 2, 2);
        StatisticSpecification specification = new StatisticSpecification(
                StatisticType.SUM,
                StatisticComparisonType.GREATER_THAN,
                5
        );

        assertTrue(specification.test(array));
    }

    @Test
    void shouldUseExistingWarehouseStatistics() throws CustomArrayException {
        SuperArray array = createArray(1002L, 3, 3, 3);
        warehouse.put(array);
        StatisticSpecification specification = new StatisticSpecification(
                StatisticType.AVERAGE,
                StatisticComparisonType.EQUALS,
                3
        );

        assertTrue(specification.test(array));
    }

    @Test
    void shouldReturnFalseWhenComparisonFails() throws CustomArrayException {
        SuperArray array = createArray(1003L, -1, -2, -3);
        StatisticSpecification specification = new StatisticSpecification(
                StatisticType.MIN,
                StatisticComparisonType.GREATER_THAN,
                -1
        );

        assertFalse(specification.test(array));
    }

    @Test
    void shouldReturnFalseWhenArrayIsNull() {
        StatisticSpecification specification = new StatisticSpecification(
                StatisticType.LENGTH,
                StatisticComparisonType.EQUALS,
                0
        );

        assertFalse(specification.test(null));
    }

    private SuperArray createArray(long id, int... values) throws CustomArrayException {
        SuperArray array = SuperArray.builder(values).setId(id).build();
        idsToCleanup.add(id);
        return array;
    }
}

