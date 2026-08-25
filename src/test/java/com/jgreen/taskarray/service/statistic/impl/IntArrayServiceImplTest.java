package com.jgreen.taskarray.service.statistic.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.OptionalDouble;
import java.util.OptionalInt;

import org.junit.jupiter.api.Test;

import com.jgreen.taskarray.entity.IntArrayWrapper;

class IntArrayServiceImplTest {

    private static final int[] VALUES = {5, 2, 8, 1, 9};
    private static final int EXPECTED_MIN = 1;
    private static final int EXPECTED_MAX = 9;
    private static final int EXPECTED_SUM = 25;
    private static final double EXPECTED_AVERAGE = 5.0;
    private static final double DELTA = 0.0001;

    private static final int[] EMPTY_VALUES = {};

    private final IntArrayServiceImpl statisticsService = new IntArrayServiceImpl();

    @Test
    void minShouldReturnSmallestElement() {
        // given
        IntArrayWrapper wrapper = new IntArrayWrapper(VALUES);

        // when
        OptionalInt actual = statisticsService.min(wrapper);

        // then
        assertTrue(actual.isPresent());
        assertEquals(EXPECTED_MIN, actual.getAsInt());
    }

    @Test
    void minShouldBeEmptyForEmptyArray() {
        // given
        IntArrayWrapper wrapper = new IntArrayWrapper(EMPTY_VALUES);

        // when
        OptionalInt actual = statisticsService.min(wrapper);

        // then
        assertTrue(actual.isEmpty());
    }

    @Test
    void maxShouldReturnLargestElement() {
        // given
        IntArrayWrapper wrapper = new IntArrayWrapper(VALUES);

        // when
        OptionalInt actual = statisticsService.max(wrapper);

        // then
        assertTrue(actual.isPresent());
        assertEquals(EXPECTED_MAX, actual.getAsInt());
    }

    @Test
    void maxShouldBeEmptyForEmptyArray() {
        // given
        IntArrayWrapper wrapper = new IntArrayWrapper(EMPTY_VALUES);

        // when
        OptionalInt actual = statisticsService.max(wrapper);

        // then
        assertTrue(actual.isEmpty());
    }

    @Test
    void sumShouldReturnTotalOfElements() {
        // given
        IntArrayWrapper wrapper = new IntArrayWrapper(VALUES);

        // when
        OptionalInt actual = statisticsService.sum(wrapper);

        // then
        assertEquals(EXPECTED_SUM, actual.getAsInt());
    }

    @Test
    void sumShouldReturnZeroForEmptyArray() {
        // given
        IntArrayWrapper wrapper = new IntArrayWrapper(EMPTY_VALUES);

        // when
        OptionalInt actual = statisticsService.sum(wrapper);

        // then
        assertEquals(0, actual.getAsInt());
    }

    @Test
    void averageShouldReturnMeanOfElements() {
        // given
        IntArrayWrapper wrapper = new IntArrayWrapper(VALUES);

        // when
        OptionalDouble actual = statisticsService.average(wrapper);

        // then
        assertTrue(actual.isPresent());
        assertEquals(EXPECTED_AVERAGE, actual.getAsDouble(), DELTA);
    }

    @Test
    void averageShouldBeEmptyForEmptyArray() {
        // given
        IntArrayWrapper wrapper = new IntArrayWrapper(EMPTY_VALUES);

        // when
        OptionalDouble actual = statisticsService.average(wrapper);

        // then
        assertTrue(actual.isEmpty());
    }
}