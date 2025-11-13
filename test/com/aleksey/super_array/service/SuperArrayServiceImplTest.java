package com.aleksey.super_array.service;

import com.aleksey.super_array.entity.SuperArray;
import com.aleksey.super_array.excepsion.CustomArrayException;
import com.aleksey.super_array.service.SuperArrayService;
import com.aleksey.super_array.service.impl.SuperArrayServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class SuperArrayServiceImplTest {

    static {
        System.setProperty("log4j.configurationFile", "D:\\IntJava\\innowise\\super_array\\main\\resources\\log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger();

    @RegisterExtension
    static TestWatcher loggingWatcher = new TestWatcher() {
        @Override
        public void testSuccessful(ExtensionContext context) {
            String displayName = context.getDisplayName();
            LOGGER.info("TEST PASSED: {}", displayName);
        }

        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            String displayName = context.getDisplayName();
            LOGGER.error("TEST FAILED: {} - {}", displayName, Optional.ofNullable(cause.getMessage()).orElse("no message"), cause);
        }
    };

    private SuperArray superArray;
    private SuperArray superArrayNull;

    private SuperArrayService service;
    private SuperArrayService serviceForNull;
    private int min = -5;
    private int max = 12;
    private int avgNum = 2;
    private int sum = 14;
    private int positive = 4;
    private int negative = 2;
    private int exceptionIndex = 15;
    private int[] array;



    @BeforeEach
    public void init(TestInfo testInfo) throws CustomArrayException {
        LOGGER.info("TEST START: {}", testInfo.getDisplayName());

        superArray = SuperArray.builder(1, -5, 12, 3, -4, 0, 7).build();

        service = new SuperArrayServiceImpl();
        array = superArray.getArray();
    }

    @Test
    void shouldThrowException() throws CustomArrayException {

        Throwable thrown = assertThrows(
                CustomArrayException.class,
                () -> superArrayNull = SuperArray.builder().build()
        );

        assertEquals("The array cannot be empty.", thrown.getMessage());
    }

    @Test
    void shouldFindMin() {
        int actual = service.findMin(array);
        assertEquals(min, actual);
    }

    @Test
    void shouldFindMax() {
        int actual = service.findMax(array);
        assertEquals(max, actual);
    }

    @Test
    void shouldReplaceElementOfArray() throws CustomArrayException {
        int[] initialArray = Arrays.copyOf(superArray.getArray(), superArray.getArray().length);
        int[] expectedArray = {1, -5, 12, 3, -4, 4, 7};
        service.replaceElementOfArray(array, 5, 4);
        int[] actualArray = superArray.getArray();

        Throwable thrown = assertThrows(
                CustomArrayException.class,
                () -> {
                    service.replaceElementOfArray(array, exceptionIndex, 2000);
                }
        );

        assertEquals(String.format("Index %d is out of bounds. Array length: %d", exceptionIndex, superArray.getArray().length), thrown.getMessage());

        assertFalse(Arrays.equals(initialArray, actualArray));
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    void shouldCountAvgNumberOfArray() {
        int actualAvgNum = service.avgNumberOfArray(array);
        assertEquals(avgNum, actualAvgNum);
    }

    @Test
    void shouldCountSumOfArrayElements() {
        int actualSum = service.sumOfArrayElements(array);
        assertEquals(sum, actualSum);
    }

    @Test
    void shouldCountNumberOfPositiveElements() {
        int actualCount = service.numberOfPositiveElements(array);
        assertEquals(positive, actualCount);
    }

    @Test
    void numberOfNegativeElements() {
        int actualCount = service.numberOfNegativeElements(array);
        assertEquals(negative, actualCount);
    }
}