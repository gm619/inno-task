package com.jgreen.taskarray.repository.spec;

import com.jgreen.taskarray.entity.ArrayEntity;
import com.jgreen.taskarray.util.ArrayMath;

/**
 * Specification that selects arrays whose sum of elements satisfies the
 * comparison against a threshold.
 */
public class SumSpecification extends NumericSpecification {

	private SumSpecification(double threshold, ComparisonOperator operator) {
		super(threshold, operator);
	}

	public static SumSpecification greaterThan(double threshold) {
		return new SumSpecification(threshold, ComparisonOperator.GREATER);
	}

	public static SumSpecification lessThan(double threshold) {
		return new SumSpecification(threshold, ComparisonOperator.LESS);
	}

	public static SumSpecification equalTo(double threshold) {
		return new SumSpecification(threshold, ComparisonOperator.EQUAL);
	}

	@Override
	protected double valueOf(ArrayEntity entity) {
		return ArrayMath.sum(entity);
	}
}