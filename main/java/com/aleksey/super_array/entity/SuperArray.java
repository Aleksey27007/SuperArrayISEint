package com.aleksey.super_array.entity;

import com.aleksey.super_array.excepsion.CustomArrayException;

import java.util.Arrays;

import com.aleksey.super_array.observer.SuperArrayObserver;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SuperArray {
    private static final Logger logger = LogManager.getLogger();
    private final long id;
    private final int[] array;
    private SuperArrayObserver observer;


    private SuperArray(SuperArrayBuilder superArrayBuilder) {
        this.id = superArrayBuilder.id;
        this.array = superArrayBuilder.array;
    }

    public static SuperArrayBuilder builder(int... array) throws CustomArrayException {
        return new SuperArrayBuilder(array);
    }

    public static class SuperArrayBuilder {
        private final int[] array;
        private long id;

        public SuperArrayBuilder(int[] array) throws CustomArrayException {
            if (array.length < 1) {
                throw new CustomArrayException("The array cannot be empty.");
            }
            this.array = array;
        }

        public SuperArrayBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public SuperArray build() {
            logger.log(Level.INFO, "New SuperArray created.");
            return new SuperArray(this);
        }
    }

    public long getId() {
        return id;
    }

    public int[] getArray() {
        return array;
    }

    public int length() {
        return array.length;
    }

    public void addObserver(SuperArrayObserver observer) {
        this.observer = observer;
    }

    public void removeObserver() {
        if (this.observer != null) {
            this.observer = null;
        }
    }

    public void setElement(int index, int value) throws CustomArrayException {
        validateIndex(index);
        array[index] = value;
        notifyObserver();
    }

    private void validateIndex(int index) throws CustomArrayException {
        if (index < 0 || index >= array.length) {
            logger.log(Level.INFO, "Index out of bounds. SuperArray.validateIndex");
            throw new CustomArrayException(String.format("Index %d is out of bounds. Array length: %d", index, array.length));
        }
    }

    private void notifyObserver() {
        if (observer != null) {
            observer.update(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SuperArray that)) return false;

        if (getId() != that.getId()) return false;
        return Arrays.equals(getArray(), that.getArray());
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(getId());
        result = 31 * result + Arrays.hashCode(getArray());
        return result;
    }

    @Override
    public String toString() {
        return String.format(
                "SuperArray { id=%d," +
                        " array=%s," +
                        " size=%d",
                id, Arrays.toString(array), array.length);
    }
}
