package com.aleksey.super_array.specification.specification_enum;

public enum StatisticComparisonType {
    GREATER_THAN {
        @Override
        public boolean compare(double actual, double expected) {
            return actual > expected;
        }
    },
    LESS_THAN {
        @Override
        public boolean compare(double actual, double expected) {
            return actual < expected;
        }
    },
    EQUALS {
        @Override
        public boolean compare(double actual, double expected) {
            return Double.compare(actual, expected) == 0;
        }
    };

    public abstract boolean compare(double actual, double expected);
}

