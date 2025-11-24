package com.aleksey.super_array.specification.specification_enum;

import com.aleksey.super_array.entity.SuperArray;
import com.aleksey.super_array.entity.SuperArrayStatistic;
import com.aleksey.super_array.warehouse.Warehouse;

public enum StatisticType {
    SUM {
        @Override
        double extract(SuperArrayStatistic statistic) {
            return statistic.getSum();
        }
    },
    AVERAGE {
        @Override
        double extract(SuperArrayStatistic statistic) {
            return statistic.getAverage();
        }
    },
    MAX {
        @Override
        double extract(SuperArrayStatistic statistic) {
            return statistic.getMax();
        }
    },
    MIN {
        @Override
        double extract(SuperArrayStatistic statistic) {
            return statistic.getMin();
        }
    },
    LENGTH {
        @Override
        double extract(SuperArrayStatistic statistic) {
            return statistic.getLength();
        }
    },
    POSITIVE_COUNT {
        @Override
        double extract(SuperArrayStatistic statistic) {
            return statistic.getPositiveCount();
        }
    },
    NEGATIVE_COUNT {
        @Override
        double extract(SuperArrayStatistic statistic) {
            return statistic.getNegativeCount();
        }
    };

    private final Warehouse warehouse = Warehouse.getInstance();

    public boolean matches(SuperArray superArray, StatisticComparisonType comparisonType, double expected) {
        SuperArrayStatistic statistic = warehouse.get(superArray.getId())
                .orElseGet(() -> {
                    warehouse.update(superArray);
                    return warehouse.get(superArray.getId()).orElseThrow();
                });
        double actual = extract(statistic);
        return comparisonType.compare(actual, expected);
    }

    abstract double extract(SuperArrayStatistic statistic);
}

