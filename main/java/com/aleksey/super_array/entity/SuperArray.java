package com.aleksey.super_array.entity;

import com.aleksey.super_array.excepsion.CustomArrayException;

import java.util.Arrays;

public class SuperArray {

    private static final long MAX_LENGTH = 987654321098765432L;
    private final int[] array;

    private SuperArray(SuperArrayBuilder superArrayBuilder) {
        array = superArrayBuilder.array;
    }

    public static SuperArrayBuilder builder(int... array) throws CustomArrayException {
        return new SuperArrayBuilder(array);
    }

    public static class SuperArrayBuilder {
        private final int[] array;

        public SuperArrayBuilder(int[] array) throws CustomArrayException {
            if (array.length < 1) {
                throw new CustomArrayException("The array cannot be empty.");
            }
            this.array = array;
        }

        public SuperArray build() {
            return new SuperArray(this);
        }
    }

    public int[] getArray() {
        return array;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SuperArray)) return false;

        SuperArray that = (SuperArray) o;

        return Arrays.equals(getArray(), that.getArray());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getArray());
    }
}
