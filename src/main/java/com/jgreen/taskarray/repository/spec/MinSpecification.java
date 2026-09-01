package com.jgreen.taskarray.repository.spec;

import com.jgreen.taskarray.entity.ArrayEntity;
import com.jgreen.taskarray.util.ArrayMath;

/**
 * Specification that selects arrays whose minimum element satisfies the
 * comparison against a threshold.
 */
public class MinSpecification extends NumericSpecification {

	private MinSpecification(double threshold, ComparisonOperator operator) {
		super(threshold, operator);
	}

	public static MinSpecification greaterThan(double threshold) {
		return new MinSpecification(threshold, ComparisonOperator.GREATER);
	}

	public static MinSpecification lessThan(double threshold) {
		return new MinSpecification(threshold, ComparisonOperator.LESS);
	}

	public static MinSpecification equalTo(double threshold) {
		return new MinSpecification(threshold, ComparisonOperator.EQUAL);
	}

	@Override
	protected double valueOf(ArrayEntity entity) {
		return ArrayMath.min(entity);
	}
}