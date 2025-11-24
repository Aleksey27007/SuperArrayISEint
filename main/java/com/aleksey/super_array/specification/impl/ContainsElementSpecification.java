package com.aleksey.super_array.specification.impl;

import com.aleksey.super_array.entity.SuperArray;
import com.aleksey.super_array.specification.SuperArraySpecification;

import java.util.Arrays;

public class ContainsElementSpecification implements SuperArraySpecification {

    private final int element;

    public ContainsElementSpecification(int element) {
        this.element = element;
    }

    @Override
    public boolean test(SuperArray superArray) {
        return superArray != null && Arrays.stream(superArray.getArray()).anyMatch(value -> value == element);
    }
}

