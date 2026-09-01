package com.jgreen.taskarray.repository.spec;

import com.jgreen.taskarray.entity.ArrayEntity;

/**
 * Specification that selects arrays whose number of elements (count) satisfies
 * the comparison against a threshold.
 */
public class CountSpecification extends NumericSpecification {

	private CountSpecification(double threshold, ComparisonOperator operator) {
		super(threshold, operator);
	}

	public static CountSpecification greaterThan(double threshold) {
		return new CountSpecification(threshold, ComparisonOperator.GREATER);
	}

	public static CountSpecification lessThan(double threshold) {
		return new CountSpecification(threshold, ComparisonOperator.LESS);
	}

	public static CountSpecification equalTo(double threshold) {
		return new CountSpecification(threshold, ComparisonOperator.EQUAL);
	}

	@Override
	protected double valueOf(ArrayEntity entity) {
		return entity.size();
	}
}