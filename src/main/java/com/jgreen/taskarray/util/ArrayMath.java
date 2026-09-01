package com.jgreen.taskarray.util;

import com.jgreen.taskarray.entity.ArrayEntity;

/**
 * Stateless helpers for computing common aggregate metrics over an
 * {@link ArrayEntity}. Used by specifications, comparators and the
 * {@code Warehouse}.
 */
public final class ArrayMath {

	private ArrayMath() {
	}

	/**
	 * @param entity the source array entity
	 * @return the sum of all elements (0 for an empty array)
	 */
	public static double sum(ArrayEntity entity) {
		double sum = 0;
		for (double v : entity.asDoubleArray()) {
			sum += v;
		}
		return sum;
	}

	/**
	 * @param entity the source array entity
	 * @return the arithmetic average (0 for an empty array)
	 */
	public static double average(ArrayEntity entity) {
		double[] values = entity.asDoubleArray();
		return values.length == 0 ? 0 : sum(entity) / values.length;
	}

	/**
	 * @param entity the source array entity
	 * @return the minimum element (0 for an empty array)
	 */
	public static double min(ArrayEntity entity) {
		double[] values = entity.asDoubleArray();
		if (values.length == 0) {
			return 0;
		}
		double min = values[0];
		for (int i = 1; i < values.length; i++) {
			if (values[i] < min) {
				min = values[i];
			}
		}
		return min;
	}

	/**
	 * @param entity the source array entity
	 * @return the maximum element (0 for an empty array)
	 */
	public static double max(ArrayEntity entity) {
		double[] values = entity.asDoubleArray();
		if (values.length == 0) {
			return 0;
		}
		double max = values[0];
		for (int i = 1; i < values.length; i++) {
			if (values[i] > max) {
				max = values[i];
			}
		}
		return max;
	}
}