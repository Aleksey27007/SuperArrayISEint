package com.aleksey.super_array.entity;

import java.util.Arrays;

public class SuperArrayStatistic {
    private final long arrayId;
    private final int sum;
    private final double average;
    private final int min;
    private final int max;
    private final int length;
    private final long positiveCount;
    private final long negativeCount;

    private SuperArrayStatistic(long arrayId,
                               int sum,
                               double average,
                               int min,
                               int max,
                               int length,
                               long positiveCount,
                               long negativeCount) {
        this.arrayId = arrayId;
        this.sum = sum;
        this.average = average;
        this.min = min;
        this.max = max;
        this.length = length;
        this.positiveCount = positiveCount;
        this.negativeCount = negativeCount;
    }

    public static SuperArrayStatistic from(SuperArray superArray) {
        int[] array = Arrays.copyOf(superArray.getArray(), superArray.getArray().length);
        int sum = Arrays.stream(array).sum();
        double avg = array.length == 0 ? 0 : (double) sum / array.length;
        int min = Arrays.stream(array).min().orElse(0);
        int max = Arrays.stream(array).max().orElse(0);
        long positives = Arrays.stream(array).filter(value -> value > 0).count();
        long negatives = Arrays.stream(array).filter(value -> value < 0).count();
        return new SuperArrayStatistic(
                superArray.getId(),
                sum,
                avg,
                min,
                max,
                array.length,
                positives,
                negatives
        );
    }

    public long getArrayId() {
        return arrayId;
    }

    public int getSum() {
        return sum;
    }

    public double getAverage() {
        return average;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getLength() {
        return length;
    }

    public long getPositiveCount() {
        return positiveCount;
    }

    public long getNegativeCount() {
        return negativeCount;
    }
}
