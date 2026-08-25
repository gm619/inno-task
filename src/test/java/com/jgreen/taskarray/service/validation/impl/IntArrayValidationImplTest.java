package com.jgreen.taskarray.service.validation.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class IntArrayValidationImplTest {

    private static final String POSITIVE_INTEGER = "42";
    private static final String NEGATIVE_INTEGER = "-7";
    private static final String SPACED_INTEGER = "  5  ";
    private static final String NULL_TOKEN = null;
    private static final String BLANK_TOKEN = "   ";
    private static final String NON_INTEGER_TOKEN = "abc";
    private static final String DECIMAL_TOKEN = "3.14";

    private final IntArrayValidationImpl validator = new IntArrayValidationImpl();

    @Test
    void isValidNumberShouldAcceptPositiveInteger() {
        // given
        String token = POSITIVE_INTEGER;

        // when
        boolean actual = validator.isValidNumber(token);

        // then
        assertTrue(actual);
    }

    @Test
    void isValidNumberShouldAcceptNegativeInteger() {
        // given
        String token = NEGATIVE_INTEGER;

        // when
        boolean actual = validator.isValidNumber(token);

        // then
        assertTrue(actual);
    }

    @Test
    void isValidNumberShouldTrimSpaces() {
        // given
        String token = SPACED_INTEGER;

        // when
        boolean actual = validator.isValidNumber(token);

        // then
        assertTrue(actual);
    }

    @Test
    void isValidNumberShouldRejectNull() {
        // given
        String token = NULL_TOKEN;

        // when
        boolean actual = validator.isValidNumber(token);

        // then
        assertFalse(actual);
    }

    @Test
    void isValidNumberShouldRejectBlank() {
        // given
        String token = BLANK_TOKEN;

        // when
        boolean actual = validator.isValidNumber(token);

        // then
        assertFalse(actual);
    }

    @Test
    void isValidNumberShouldRejectNonInteger() {
        // given
        String token = NON_INTEGER_TOKEN;

        // when
        boolean actual = validator.isValidNumber(token);

        // then
        assertFalse(actual);
    }

    @Test
    void isIntegerShouldAcceptInteger() {
        // given
        String token = NEGATIVE_INTEGER;

        // when
        boolean actual = validator.isInteger(token);

        // then
        assertTrue(actual);
    }

    @Test
    void isIntegerShouldRejectDecimal() {
        // given
        String token = DECIMAL_TOKEN;

        // when
        boolean actual = validator.isInteger(token);

        // then
        assertFalse(actual);
    }

    @Test
    void isIntegerShouldRejectNull() {
        // given
        String token = NULL_TOKEN;

        // when
        boolean actual = validator.isInteger(token);

        // then
        assertFalse(actual);
    }
}