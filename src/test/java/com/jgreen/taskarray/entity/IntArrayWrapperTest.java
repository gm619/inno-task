package com.jgreen.taskarray.entity;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.Test;

class IntArrayWrapperTest {

    private static final int[] SOURCE_ARRAY = {1, 2, 3, 4, 5};
    private static final int EXPECTED_LENGTH = 5;
    private static final String EXPECTED_TO_STRING = "[1, 2, 3, 4, 5]";

    @Test
    void defaultConstructorShouldCreateEmptyWrapper() {
        // given
        IntArrayWrapper wrapper = new IntArrayWrapper();

        // when
        int length = wrapper.length();

        // then
        assertEquals(0, length);
    }

    @Test
    void arrayConstructorShouldStoreElements() {
        // given
        IntArrayWrapper wrapper = new IntArrayWrapper(SOURCE_ARRAY);

        // when
        int[] stored = wrapper.getArray();

        // then
        assertArrayEquals(SOURCE_ARRAY, stored);
    }

    @Test
    void getArrayShouldReturnCopyNotSameReference() {
        // given
        IntArrayWrapper wrapper = new IntArrayWrapper(SOURCE_ARRAY);

        // when
        int[] firstCopy = wrapper.getArray();
        int[] secondCopy = wrapper.getArray();

        // then
        assertNotSame(firstCopy, secondCopy);
    }

    @Test
    void constructorShouldDefensivelyCopyInput() {
        // given
        int[] input = SOURCE_ARRAY.clone();
        IntArrayWrapper wrapper = new IntArrayWrapper(input);

        // when
        input[0] = 99;
        int[] stored = wrapper.getArray();

        // then
        assertArrayEquals(SOURCE_ARRAY, stored);
    }

    @Test
    void lengthShouldReturnNumberOfElements() {
        // given
        IntArrayWrapper wrapper = new IntArrayWrapper(SOURCE_ARRAY);

        // when
        int actualLength = wrapper.length();

        // then
        assertEquals(EXPECTED_LENGTH, actualLength);
    }

    @Test
    void toStringShouldRenderArrayRepresentation() {
        // given
        IntArrayWrapper wrapper = new IntArrayWrapper(SOURCE_ARRAY);

        // when
        String actual = wrapper.toString();

        // then
        assertEquals(EXPECTED_TO_STRING, actual);
    }
}