package com.aleksey.super_array.repository.impl;

import com.aleksey.super_array.entity.SuperArray;
import com.aleksey.super_array.observer.impl.WarehouseObserverImpl;
import com.aleksey.super_array.repository.SuperArrayRepository;
import com.aleksey.super_array.specification.SuperArraySpecification;
import com.aleksey.super_array.warehouse.Warehouse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SuperArrayRepositoryImpl implements SuperArrayRepository {
    private static final Logger logger = LogManager.getLogger();
    private static SuperArrayRepositoryImpl instance;
    private final Warehouse warehouse = Warehouse.getInstance();
    private List<SuperArray> storage = new ArrayList<>();

    private SuperArrayRepositoryImpl() {
    }

    public static SuperArrayRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new SuperArrayRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void add(SuperArray superArray) {
        if (superArray == null) {
            logger.log(Level.WARN, "Attempt to add null SuperArray");
            return;
        }
        superArray.addObserver(new WarehouseObserverImpl());
        warehouse.put(superArray);
        storage.add(superArray);
    }

    @Override
    public void addAll(List<SuperArray> superArrays) {
        if (superArrays == null) {
            return;
        }
        superArrays.forEach(this::add);
    }

    @Override
    public boolean removeById(long id) {
        Optional<SuperArray> target = findById(id);
        target.ifPresent(superArray -> {
            storage.remove(superArray);
            warehouse.remove(id);
            superArray.removeObserver();
        });
        return target.isPresent();
    }

    @Override
    public boolean remove(SuperArray superArray) {
        if (superArray == null) {
            return false;
        }
        boolean removed = storage.remove(superArray);
        if (removed) {
            warehouse.remove(superArray.getId());
            superArray.removeObserver();
        }
        return removed;
    }

    @Override
    public Optional<SuperArray> findById(long id) {
        return storage.stream()
                .filter(superArray -> superArray.getId() == id)
                .findFirst();
    }

    @Override
    public List<SuperArray> query(SuperArraySpecification specification) {
        if (specification == null) {
            return List.copyOf(storage);
        }
        return storage.stream()
                .filter(specification::test)
                .toList();
    }

    @Override
    public List<SuperArray> sort(Comparator<SuperArray> comparator) {
        return storage.stream()
                .sorted(comparator)
                .toList();
    }

    @Override
    public List<SuperArray> findAll() {
        return List.copyOf(storage);
    }
}
