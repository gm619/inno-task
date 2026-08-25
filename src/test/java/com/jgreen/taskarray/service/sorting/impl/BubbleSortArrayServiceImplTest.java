package com.jgreen.taskarray.service.sorting.impl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class BubbleSortArrayServiceImplTest {

    private static final int[] UNSORTED = {5, 1, 4, 2, 8};
    private static final int[] EXPECTED_SORTED = {1, 2, 4, 5, 8};
    private static final int[] ALREADY_SORTED = {1, 2, 3, 4, 5};
    private static final int[] EXPECTED_ALREADY_SORTED = {1, 2, 3, 4, 5};
    private static final int[] WITH_DUPLICATES = {4, 4, 1, 3, 1};
    private static final int[] EXPECTED_WITH_DUPLICATES = {1, 1, 3, 4, 4};

    private final BubbleSortArrayServiceImpl sortService = new BubbleSortArrayServiceImpl();

    @Test
    void sortShouldOrderUnsortedArray() {
        // given
        int[] input = UNSORTED.clone();

        // when
        int[] result = sortService.sort(input);

        // then
        assertArrayEquals(EXPECTED_SORTED, result);
    }

    @Test
    void sortShouldNotModifyInputArray() {
        // given
        int[] input = UNSORTED.clone();
        int[] original = UNSORTED.clone();

        // when
        int[] result = sortService.sort(input);

        // then
        assertArrayEquals(original, input);
        assertArrayEquals(EXPECTED_SORTED, result);
    }

    @Test
    void sortShouldKeepAlreadySortedArray() {
        // given
        int[] input = ALREADY_SORTED.clone();

        // when
        int[] result = sortService.sort(input);

        // then
        assertArrayEquals(EXPECTED_ALREADY_SORTED, result);
    }

    @Test
    void sortShouldHandleDuplicates() {
        // given
        int[] input = WITH_DUPLICATES.clone();

        // when
        int[] result = sortService.sort(input);

        // then
        assertArrayEquals(EXPECTED_WITH_DUPLICATES, result);
    }
}