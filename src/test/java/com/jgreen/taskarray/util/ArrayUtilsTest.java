package com.jgreen.taskarray.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ArrayUtilsTest {

    private static final int[] ARRAY_FOR_SWAP = {1, 2, 3, 4, 5};
    private static final int[] SWAPPED_EXPECTED = {1, 4, 3, 2, 5};

    private static final int[] VALUES_FOR_MIN = {3, 1, 4, 1, 5};
    private static final int START_INDEX = 0;
    private static final int EXPECTED_MIN_INDEX = 1;

    @Test
    void swapShouldExchangeElementsByIndexes() {
        // given
        int[] input = ARRAY_FOR_SWAP.clone();

        // when
        ArrayUtils.swap(input, 1, 3);

        // then
        assertArrayEquals(SWAPPED_EXPECTED, input);
    }

    @Test
    void findMinimumIndexShouldReturnIndexOfSmallestElement() {
        // given
        int[] values = VALUES_FOR_MIN;

        // when
        int actualMinIndex = ArrayUtils.findMinimumIndex(values, START_INDEX);

        // then
        assertEquals(EXPECTED_MIN_INDEX, actualMinIndex);
    }
}