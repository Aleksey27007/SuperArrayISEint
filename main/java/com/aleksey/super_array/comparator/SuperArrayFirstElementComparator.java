package com.aleksey.super_array.comparator;

import com.aleksey.super_array.entity.SuperArray;

import java.util.Comparator;

public class SuperArrayFirstElementComparator implements Comparator<SuperArray> {

    @Override
    public int compare(SuperArray first, SuperArray second) {
        int firstValue = first.getArray()[0];
        int secondValue = second.getArray()[0];
        return Integer.compare(firstValue, secondValue);
    }
}

