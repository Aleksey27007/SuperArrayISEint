package com.aleksey.super_array.comparator;

import com.aleksey.super_array.entity.SuperArray;

import java.util.Comparator;

public class SuperArrayIdComparator implements Comparator<SuperArray> {
    @Override
    public int compare(SuperArray first, SuperArray second) {
        return Long.compare(first.getId(), second.getId());
    }
}

