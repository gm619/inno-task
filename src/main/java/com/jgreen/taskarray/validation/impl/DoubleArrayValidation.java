package com.jgreen.taskarray.validation.impl;

import com.jgreen.taskarray.validation.ArrayValidation;

public class DoubleArrayValidation implements ArrayValidation {
  private static final String DOUBLE_REGEX = "^-?\\d+\\.\\d+$";

  @Override
	public boolean isValidNumber(String token) {
		if (token == null || token.isBlank()) {
			return false;
		}
		return token.strip().matches(DOUBLE_REGEX);
	}
}
