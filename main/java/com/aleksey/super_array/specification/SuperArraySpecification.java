package com.aleksey.super_array.specification;

import com.aleksey.super_array.entity.SuperArray;

@FunctionalInterface
public interface SuperArraySpecification {
    boolean test(SuperArray superArray);
}

