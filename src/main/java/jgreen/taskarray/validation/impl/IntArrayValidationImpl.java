package jgreen.taskarray.validation.impl;

import jgreen.taskarray.validation.IntArrayValidation;

public class IntArrayValidationImpl implements IntArrayValidation {
    private static final String INTEGER_REGEX = "^-?\\d+$";
    private static final String DOUBLE_REGEX = "^-?\\d+(\\.\\d+)?$";

    public boolean isValidNumber(String token) {
        if (token == null || token.trim().isEmpty()) return false;
        String cleaned = token.trim();
        return cleaned.matches(INTEGER_REGEX) || cleaned.matches(DOUBLE_REGEX);
    }

    public boolean isInteger(String token) {
        return token != null && token.trim().matches(INTEGER_REGEX);
    }
}
