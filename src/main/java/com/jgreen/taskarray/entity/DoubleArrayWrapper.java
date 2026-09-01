package com.jgreen.taskarray.entity;

import java.util.Arrays;

/**
 * Mutable wrapper around a primitive {@code double} array.
 *
 * <p>Instances may be created either directly via the public constructors,
 * through the {@link com.jgreen.taskarray.factory.ArrayFactoryMethod factory}
 * pattern. Every instance declares an identity ({@code id}/{@code name}) as
 * required by the task and notifies its {@link EntityChangeListener} whenever
 * an element is modified.</p>
 */
public class DoubleArrayWrapper implements ArrayEntity {

	private final double[] array;
	private long id;
	private String name;
	private EntityChangeListener changeListener;

	public DoubleArrayWrapper() {
		array = new double[0];
	}

	public DoubleArrayWrapper(double[] array) {
		this.array = array == null ? new double[0] : array.clone();
	}

	public double[] getArray() {
		return array.clone();
	}

	public double length() {
		return array.length;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int size() {
		return array.length;
	}

	@Override
	public Number getFirst() {
		return array.length == 0 ? 0 : array[0];
	}

	@Override
	public Number getElement(int index) {
		return array[index];
	}

	// public double getElementDouble(int index) {
	// 	return array[index];
	// }

	@Override
	public void setElement(int index, Number value) {
		array[index] = value.doubleValue();
		if (changeListener != null) {
			changeListener.onEntityChanged(this);
		}
	}

	@Override
	public double[] asDoubleArray() {
		return array.clone();
	}

	@Override
	public void setChangeListener(EntityChangeListener listener) {
		this.changeListener = listener;
	}

	@Override
	public String toString() {
		return "DoubleArrayWrapper{id=" + id + ", name='" + name + "', " + Arrays.toString(array) + '}';
	}
}
