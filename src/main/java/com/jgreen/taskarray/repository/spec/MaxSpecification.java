package com.jgreen.taskarray.repository.spec;

import com.jgreen.taskarray.entity.ArrayEntity;
import com.jgreen.taskarray.util.ArrayMath;

/**
 * Specification that selects arrays whose maximum element satisfies the
 * comparison against a threshold.
 */
public class MaxSpecification extends NumericSpecification {

	private MaxSpecification(double threshold, ComparisonOperator operator) {
		super(threshold, operator);
	}

	public static MaxSpecification greaterThan(double threshold) {
		return new MaxSpecification(threshold, ComparisonOperator.GREATER);
	}

	public static MaxSpecification lessThan(double threshold) {
		return new MaxSpecification(threshold, ComparisonOperator.LESS);
	}

	public static MaxSpecification equalTo(double threshold) {
		return new MaxSpecification(threshold, ComparisonOperator.EQUAL);
	}

	@Override
	protected double valueOf(ArrayEntity entity) {
		return ArrayMath.max(entity);
	}
}