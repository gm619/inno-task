package com.jgreen.taskarray.repository.spec;

import com.jgreen.taskarray.entity.ArrayEntity;
import com.jgreen.taskarray.util.ArrayMath;

/**
 * Specification that selects arrays whose average (mean) element value
 * satisfies the comparison against a threshold.
 */
public class AverageSpecification extends NumericSpecification {

	private AverageSpecification(double threshold, ComparisonOperator operator) {
		super(threshold, operator);
	}

	public static AverageSpecification greaterThan(double threshold) {
		return new AverageSpecification(threshold, ComparisonOperator.GREATER);
	}

	public static AverageSpecification lessThan(double threshold) {
		return new AverageSpecification(threshold, ComparisonOperator.LESS);
	}

	public static AverageSpecification equalTo(double threshold) {
		return new AverageSpecification(threshold, ComparisonOperator.EQUAL);
	}

	@Override
	protected double valueOf(ArrayEntity entity) {
		return ArrayMath.average(entity);
	}
}