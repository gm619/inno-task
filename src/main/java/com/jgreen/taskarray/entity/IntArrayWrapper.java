package com.jgreen.taskarray.entity;

import java.util.Arrays;

/**
 * Immutable wrapper around a primitive {@code int} array.
 *
 * <p>Instances may be created either directly via the public constructors,
 * through the {@link ArrayFactoryMethod factory} pattern.</p>
 */
public class IntArrayWrapper {
	private final int[] array;

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
	public String toString() {
		return Arrays.toString(array);
	}
}
