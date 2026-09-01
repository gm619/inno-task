package com.jgreen.taskarray.entity;

import java.util.Arrays;

/**
 * Mutable wrapper around a primitive {@code int} array.
 *
 * <p>Instances may be created either directly via the public constructors,
 * through the {@link com.jgreen.taskarray.factory.ArrayFactoryMethod factory}
 * pattern. Every instance declares an identity ({@code id}/{@code name}) as
 * required by the task and notifies its {@link EntityChangeListener} whenever
 * an element is modified.</p>
 */
public class IntArrayWrapper implements ArrayEntity {

	private final int[] array;
	private long id;
	private String name;
	private EntityChangeListener changeListener;

	public IntArrayWrapper() {
		array = new int[0];
	}

	public IntArrayWrapper(int[] array) {
		this.array = array == null ? new int[0] : array.clone();
	}

	public int[] getArray() {
		return array.clone();
	}

	public int length() {
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

	// public int getElementInt(int index) {
	// 	return array[index];
	// }

	@Override
	public void setElement(int index, Number value) {
		array[index] = value.intValue();
		if (changeListener != null) {
			changeListener.onEntityChanged(this);
		}
	}

	@Override
	public double[] asDoubleArray() {
		double[] doubles = new double[array.length];
		for (int i = 0; i < array.length; i++) {
			doubles[i] = array[i];
		}
		return doubles;
	}

	@Override
	public void setChangeListener(EntityChangeListener listener) {
		this.changeListener = listener;
	}

	@Override
	public String toString() {
		return "IntArrayWrapper{id=" + id + ", name='" + name + "', " + Arrays.toString(array) + '}';
	}
}
