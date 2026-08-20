package jgreen.taskarray.entity;

import java.util.Arrays;

public class IntArray {
	private final int[] array;

	public IntArray() {
		this.array = new int[0];
	}
	
	public IntArray(int[] array) {
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
