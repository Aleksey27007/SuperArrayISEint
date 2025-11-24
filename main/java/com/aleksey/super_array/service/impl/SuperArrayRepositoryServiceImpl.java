package com.aleksey.super_array.service.impl;

import com.aleksey.super_array.entity.SuperArray;
import com.aleksey.super_array.repository.SuperArrayRepository;
import com.aleksey.super_array.repository.impl.SuperArrayRepositoryImpl;
import com.aleksey.super_array.service.SuperArrayRepositoryService;
import com.aleksey.super_array.specification.SuperArraySpecification;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SuperArrayRepositoryServiceImpl implements SuperArrayRepositoryService {

    private final SuperArrayRepository repository = SuperArrayRepositoryImpl.getInstance();

    @Override
    public void add(SuperArray superArray) {
        repository.add(superArray);
    }

    @Override
    public void addAll(List<SuperArray> superArrays) {
        repository.addAll(superArrays);
    }

    @Override
    public boolean removeById(long id) {
        return repository.removeById(id);
    }

    @Override
    public boolean remove(SuperArray superArray) {
        return repository.remove(superArray);
    }

    @Override
    public Optional<SuperArray> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<SuperArray> query(SuperArraySpecification specification) {
        return repository.query(specification);
    }

    @Override
    public List<SuperArray> sort(Comparator<SuperArray> comparator) {
        return repository.sort(comparator);
    }

    @Override
    public List<SuperArray> findAll() {
        return repository.findAll();
    }
}
