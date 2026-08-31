package com.jgreen.taskarray.validation.impl;

import com.jgreen.taskarray.validation.ArrayValidation;

public class IntArrayValidationImpl implements ArrayValidation {

	private static final String INTEGER_REGEX = "^-?\\d+$";

	@Override
	public boolean isValidNumber(String token) {
		if (token == null || token.isBlank()) {
			return false;
		}
		return token.strip().matches(INTEGER_REGEX);
	}
}
