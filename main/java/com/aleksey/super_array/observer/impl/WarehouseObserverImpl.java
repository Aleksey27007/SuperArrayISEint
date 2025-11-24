package com.aleksey.super_array.observer.impl;

import com.aleksey.super_array.entity.SuperArray;
import com.aleksey.super_array.observer.SuperArrayObserver;
import com.aleksey.super_array.warehouse.Warehouse;

public class WarehouseObserverImpl implements SuperArrayObserver {

    private final Warehouse warehouse = Warehouse.getInstance();

    @Override
    public void update(SuperArray superArray) {
        warehouse.update(superArray);
    }
}

