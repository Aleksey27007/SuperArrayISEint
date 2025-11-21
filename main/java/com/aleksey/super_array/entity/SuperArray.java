package com.aleksey.super_array.entity;

import com.aleksey.super_array.excepsion.CustomArrayException;

import java.util.Arrays;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SuperArray {

    private static final long MAX_LENGTH = 987654321098765432L;
    private final long id;
    private final int[] array;
    private static final Logger logger = LogManager.getLogger();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SuperArray that)) return false;

        return Arrays.equals(getArray(), that.getArray());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getArray());
    }
}
