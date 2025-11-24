package com.aleksey.super_array.specification.impl;

import com.aleksey.super_array.entity.SuperArray;
import com.aleksey.super_array.specification.specification_enum.StatisticComparisonType;
import com.aleksey.super_array.specification.specification_enum.StatisticType;
import com.aleksey.super_array.specification.SuperArraySpecification;

public class StatisticSpecification implements SuperArraySpecification {

    private final StatisticType statisticType;
    private final StatisticComparisonType comparisonType;
    private final double expectedValue;

    public StatisticSpecification(StatisticType statisticType,
                                  StatisticComparisonType comparisonType,
                                  double expectedValue) {
        this.statisticType = statisticType;
        this.comparisonType = comparisonType;
        this.expectedValue = expectedValue;
    }

    @Override
    public boolean test(SuperArray superArray) {
        if (superArray == null) {
            return false;
        }
        return statisticType.matches(superArray, comparisonType, expectedValue);
    }
}

