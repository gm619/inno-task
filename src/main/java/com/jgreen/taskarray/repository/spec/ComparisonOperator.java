package com.jgreen.taskarray.repository.spec;

/**
 * Comparison operator used by numeric {@code Specification}s.
 */
public enum ComparisonOperator {

	GREATER(">"),
	LESS("<"),
	EQUAL("==");

	private final String symbol;

	ComparisonOperator(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}
}