package com.aleksey.super_array.comparator;

import com.aleksey.super_array.entity.SuperArray;

import java.util.Comparator;

public class SuperArrayLengthComparator implements Comparator<SuperArray> {
    @Override
    public int compare(SuperArray first, SuperArray second) {
        return Integer.compare(first.length(), second.length());
    }
}

