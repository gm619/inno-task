package com.jgreen.taskarray.repository.spec;

import com.jgreen.taskarray.entity.ArrayEntity;
import com.jgreen.taskarray.repository.ArraySpecification;

/**
 * Base class for numeric specifications. Extracts a single {@code double}
 * metric from an entity and compares it with a threshold using the given
 * {@link ComparisonOperator}.
 */
public abstract class NumericSpecification implements ArraySpecification {

	private final double threshold;
	private final ComparisonOperator operator;

	protected NumericSpecification(double threshold, ComparisonOperator operator) {
		this.threshold = threshold;
		this.operator = operator;
	}

	/**
	 * Extracts the metric that must be compared with the threshold.
	 *
	 * @param entity the candidate entity
	 * @return the metric value
	 */
	protected abstract double valueOf(ArrayEntity entity);

	@Override
	public boolean isSatisfiedBy(ArrayEntity entity) {
		double value = valueOf(entity);
		return switch (operator) {
			case GREATER -> value > threshold;
			case LESS -> value < threshold;
			case EQUAL -> Double.compare(value, threshold) == 0;
		};
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + '{' + operator.getSymbol() + ' ' + threshold + '}';
	}
}