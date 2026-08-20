package jgreen.taskarray.service.impl;

import jgreen.taskarray.entity.IntArray;
import jgreen.taskarray.service.IntArrayService;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.OptionalDouble;

public class IntArrayServiceImpl implements IntArrayService {
	@Override
	public OptionalInt min(IntArray intArray) {
		// Should be in reader or validation
		if (intArray.length() < 1) {
			return OptionalInt.empty();
		}
		
		return Arrays.stream(intArray.getArray()).min();
	}

	@Override
	public OptionalInt max(IntArray intArray) {
		return Arrays.stream(intArray.getArray()).max();
	}
	
	@Override
	public OptionalInt sum(IntArray intArray) {
		return OptionalInt.of(Arrays.stream(intArray.getArray()).sum());
	}
	
	@Override
	public OptionalDouble average(IntArray intArray) {
		return Arrays.stream(intArray.getArray()).average();
	}
}
