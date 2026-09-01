package com.jgreen.taskarray.entity;

/**
 * Immutable snapshot of the derived statistics for one {@link ArrayEntity}.
 *
 * <p>Instances are produced by the {@code Warehouse} and cached keyed by the
 * entity id. They represent: count of elements, sum, average, min and max.</p>
 */
public final class ArrayStatistics {

	private final long id;
	private final String name;
	private final int count;
	private final double sum;
	private final double average;
	private final double min;
	private final double max;

	public ArrayStatistics(long id, String name, int count,
			double sum, double average, double min, double max) {
		this.id = id;
		this.name = name;
		this.count = count;
		this.sum = sum;
		this.average = average;
		this.min = min;
		this.max = max;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getCount() {
		return count;
	}

	public double getSum() {
		return sum;
	}

	public double getAverage() {
		return average;
	}

	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}

	@Override
	public String toString() {
		return "ArrayStatistics{id=" + id + ", name='" + name + "', count=" + count
				+ ", sum=" + sum + ", average=" + average
				+ ", min=" + min + ", max=" + max + '}';
	}
}