package com.jgreen.taskarray.entity;

import java.util.Arrays;

public class IntArrayWrapper {
	private final int[] array;

	public IntArrayWrapper() {
		this.array = new int[0];
	}
	
	public IntArrayWrapper(int[] array) {
		this.array = array.clone();
	}
	
	public int[] getArray() {
		return this.array.clone();
	}
	
	public int length() {
		return this.array.length;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(this.array);
	}
}
