package com.jgreen.taskarray.service.parser.impl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.jgreen.taskarray.exception.CustomArrayExecption;

class IntArrayParserImplTest {

    private static final String BLANK_LINE = "   ";
    private static final String VALID_LINE = "3, 1 -4; 5";
    private static final int[] EXPECTED_VALID_ARRAY = {3, 1, 4, 5};

    private static final String INVALID_LINE = "{empty string}";

    private static final String TOKENS_LINE = "  1, 2 ; 3  ";
    private static final List<String> EXPECTED_TOKENS = List.of("1", "2", "3");

    private final IntArrayParserImpl parser = new IntArrayParserImpl();

    @Test
    void parseShouldReturnNullForBlankLine() throws CustomArrayExecption {
        // given
        String line = BLANK_LINE;

        // when
        int[] result = parser.parse(line);

        // then
        assertNull(result);
    }

    @Test
    void parseShouldReturnParsedArrayForValidLine() throws CustomArrayExecption {
        // given
        String line = VALID_LINE;

        // when
        int[] result = parser.parse(line);

        // then
        assertArrayEquals(EXPECTED_VALID_ARRAY, result);
    }

    @Test
    void parseShouldThrowExceptionForInvalidLine() {
        // given
        String line = INVALID_LINE;

        // when
        CustomArrayExecption exception = assertThrows(
                CustomArrayExecption.class,
                () -> parser.parse(line));

        // then
        assertTrue(exception.getMessage().contains("No valid numbers found"));
    }

    @Test
    void parseTokensShouldReturnNonEmptyTokens() {
        // given
        String line = TOKENS_LINE;

        // when
        List<String> tokens = parser.parseTokens(line);

        // then
        assertEquals(EXPECTED_TOKENS, tokens);
    }
}