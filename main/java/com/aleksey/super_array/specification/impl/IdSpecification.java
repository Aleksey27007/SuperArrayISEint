package com.aleksey.super_array.specification.impl;

import com.aleksey.super_array.entity.SuperArray;
import com.aleksey.super_array.specification.SuperArraySpecification;

public class IdSpecification implements SuperArraySpecification {

    private final long id;

    public IdSpecification(long id) {
        this.id = id;
    }

    @Override
    public boolean test(SuperArray superArray) {
        return superArray != null && superArray.getId() == id;
    }
}

