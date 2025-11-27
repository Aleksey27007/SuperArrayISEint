package com.aleksey.super_array.service.impl;

import com.aleksey.super_array.comparator.SuperArrayIdComparator;
import com.aleksey.super_array.comparator.SuperArrayLengthComparator;
import com.aleksey.super_array.entity.SuperArray;
import com.aleksey.super_array.excepsion.CustomArrayException;
import com.aleksey.super_array.service.SuperArrayRepositoryService;
import com.aleksey.super_array.specification.impl.ContainsElementSpecification;
import com.aleksey.super_array.specification.impl.IdSpecification;
import com.aleksey.super_array.warehouse.Warehouse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SuperArrayRepositoryServiceImplTest {

    private SuperArrayRepositoryService service;
    private Warehouse warehouse;
    private SuperArray array1;
    private SuperArray array2;
    private SuperArray array3;

    @BeforeEach
    void setUp() throws CustomArrayException {
        service = new SuperArrayRepositoryServiceImpl();
        warehouse = Warehouse.getInstance();
        
        array1 = SuperArray.builder(1, 2, 3).setId(2001L).build();
        array2 = SuperArray.builder(4, 5, 6, 7).setId(2002L).build();
        array3 = SuperArray.builder(8, 9).setId(2003L).build();
    }

    @AfterEach
    void tearDown() {
        List<SuperArray> allArrays = service.findAll();
        for (SuperArray array : allArrays) {
            service.removeById(array.getId());
        }
    }

    @Test
    void shouldAddSuperArray() {
        service.add(array1);
        
        Optional<SuperArray> found = service.findById(array1.getId());
        assertTrue(found.isPresent());
        assertEquals(array1.getId(), found.get().getId());
    }

    @Test
    void shouldNotAddNullSuperArray() {
        service.add(null);
        
        List<SuperArray> all = service.findAll();
        assertTrue(all.isEmpty() || !all.contains(null));
    }

    @Test
    void shouldAddAllSuperArrays() {
        List<SuperArray> arrays = new ArrayList<>();
        arrays.add(array1);
        arrays.add(array2);
        arrays.add(array3);
        
        service.addAll(arrays);
        
        List<SuperArray> all = service.findAll();
        assertEquals(3, all.size());
        assertTrue(all.contains(array1));
        assertTrue(all.contains(array2));
        assertTrue(all.contains(array3));
    }

    @Test
    void shouldNotAddAllWhenListIsNull() {
        service.addAll(null);
        
        assertDoesNotThrow(() -> service.findAll());
    }

    @Test
    void shouldRemoveById() {
        service.add(array1);
        service.add(array2);
        
        boolean removed = service.removeById(array1.getId());
        
        assertTrue(removed);
        assertFalse(service.findById(array1.getId()).isPresent());
        assertTrue(service.findById(array2.getId()).isPresent());
    }

    @Test
    void shouldReturnFalseWhenRemoveByIdWithNonExistentId() {
        service.add(array1);
        
        boolean removed = service.removeById(9999L);
        
        assertFalse(removed);
        assertTrue(service.findById(array1.getId()).isPresent());
    }

    @Test
    void shouldRemoveSuperArray() {
        service.add(array1);
        service.add(array2);
        
        boolean removed = service.remove(array1);
        
        assertTrue(removed);
        assertFalse(service.findById(array1.getId()).isPresent());
        assertTrue(service.findById(array2.getId()).isPresent());
    }

    @Test
    void shouldReturnFalseWhenRemoveNonExistentSuperArray() {
        service.add(array1);
        
        boolean removed = service.remove(array2);
        
        assertFalse(removed);
        assertTrue(service.findById(array1.getId()).isPresent());
    }

    @Test
    void shouldReturnFalseWhenRemoveNull() {
        service.add(array1);
        
        boolean removed = service.remove(null);
        
        assertFalse(removed);
        assertTrue(service.findById(array1.getId()).isPresent());
    }

    @Test
    void shouldFindById() {
        service.add(array1);
        service.add(array2);
        
        Optional<SuperArray> found = service.findById(array1.getId());
        
        assertTrue(found.isPresent());
        assertEquals(array1.getId(), found.get().getId());
    }

    @Test
    void shouldReturnEmptyWhenFindByIdWithNonExistentId() {
        service.add(array1);
        
        Optional<SuperArray> found = service.findById(9999L);
        
        assertFalse(found.isPresent());
    }

    @Test
    void shouldQueryWithIdSpecification() {
        service.add(array1);
        service.add(array2);
        service.add(array3);
        
        IdSpecification specification = new IdSpecification(array2.getId());
        List<SuperArray> result = service.query(specification);
        
        assertEquals(1, result.size());
        assertEquals(array2.getId(), result.get(0).getId());
    }

    @Test
    void shouldQueryWithContainsElementSpecification() {
        service.add(array1);
        service.add(array2);
        service.add(array3);
        
        ContainsElementSpecification specification = new ContainsElementSpecification(5);
        List<SuperArray> result = service.query(specification);
        
        assertEquals(1, result.size());
        assertEquals(array2.getId(), result.get(0).getId());
    }

    @Test
    void shouldQueryWithNullSpecification() {
        service.add(array1);
        service.add(array2);
        
        List<SuperArray> result = service.query(null);
        
        assertEquals(2, result.size());
    }

    @Test
    void shouldQueryWithSpecificationThatMatchesNothing() {
        service.add(array1);
        service.add(array2);
        
        ContainsElementSpecification specification = new ContainsElementSpecification(999);
        List<SuperArray> result = service.query(specification);
        
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldSortById() {
        service.add(array3);
        service.add(array1);
        service.add(array2);
        
        SuperArrayIdComparator comparator = new SuperArrayIdComparator();
        List<SuperArray> sorted = service.sort(comparator);
        
        assertEquals(3, sorted.size());
        assertEquals(array1.getId(), sorted.get(0).getId());
        assertEquals(array2.getId(), sorted.get(1).getId());
        assertEquals(array3.getId(), sorted.get(2).getId());
    }

    @Test
    void shouldSortByLength() {
        service.add(array3);
        service.add(array1);
        service.add(array2);
        
        SuperArrayLengthComparator comparator = new SuperArrayLengthComparator();
        List<SuperArray> sorted = service.sort(comparator);
        
        assertEquals(3, sorted.size());
        assertEquals(2, sorted.get(0).length());
        assertEquals(3, sorted.get(1).length());
        assertEquals(4, sorted.get(2).length());
    }

    @Test
    void shouldFindAll() {
        service.add(array1);
        service.add(array2);
        service.add(array3);
        
        List<SuperArray> all = service.findAll();
        
        assertEquals(3, all.size());
        assertTrue(all.contains(array1));
        assertTrue(all.contains(array2));
        assertTrue(all.contains(array3));
    }

    @Test
    void shouldReturnEmptyListWhenFindAllOnEmptyRepository() {
        List<SuperArray> all = service.findAll();
        
        assertTrue(all.isEmpty());
    }

    @Test
    void shouldReturnCopyOfListWhenFindAll() {
        service.add(array1);
        
        List<SuperArray> all1 = service.findAll();
        List<SuperArray> all2 = service.findAll();
        
        assertNotSame(all1, all2);
        assertEquals(all1, all2);
    }
}

