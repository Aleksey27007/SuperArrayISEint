package com.aleksey.super_array.repository;

import com.aleksey.super_array.entity.SuperArray;
import com.aleksey.super_array.specification.SuperArraySpecification;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface SuperArrayRepository {
    void add(SuperArray superArray);
    void addAll(List<SuperArray> superArrays);
    boolean removeById(long id);
    boolean remove(SuperArray superArray);
    Optional<SuperArray> findById(long id);
    List<SuperArray> query(SuperArraySpecification specification);
    List<SuperArray> sort(Comparator<SuperArray> comparator);
    List<SuperArray> findAll();
}
