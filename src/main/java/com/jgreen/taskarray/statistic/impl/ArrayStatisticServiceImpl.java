package com.jgreen.taskarray.statistic.impl;

import com.jgreen.taskarray.entity.DoubleArrayWrapper;
import com.jgreen.taskarray.entity.IntArrayWrapper;
import com.jgreen.taskarray.statistic.ArrayStatisticService;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.OptionalDouble;

public class ArrayStatisticServiceImpl implements ArrayStatisticService {
	@Override
	public OptionalInt intMin(IntArrayWrapper intArray) {	
		return Arrays.stream(intArray.getArray()).min();
	}

	@Override
	public OptionalInt intMax(IntArrayWrapper intArray) {
		return Arrays.stream(intArray.getArray()).max();
	}
	
	@Override
	public OptionalInt intSum(IntArrayWrapper intArray) {
		return OptionalInt.of(Arrays.stream(intArray.getArray()).sum());
	}
	
	@Override
	public OptionalDouble intAverage(IntArrayWrapper intArray) {
		return Arrays.stream(intArray.getArray()).average();
	}

	@Override
	public OptionalDouble doubleMin(DoubleArrayWrapper doubleArray) {
		return Arrays.stream(doubleArray.getArray()).min();
	}

	@Override
	public OptionalDouble doubleMax(DoubleArrayWrapper doubleArray) {
		return Arrays.stream(doubleArray.getArray()).max();
	}
	
	@Override
	public OptionalDouble doubleSum(DoubleArrayWrapper doubleArray) {
		return OptionalDouble.of(Arrays.stream(doubleArray.getArray()).sum());
	}
	
	@Override
	public OptionalDouble doubleAverage(DoubleArrayWrapper doubleArray) {
		return Arrays.stream(doubleArray.getArray()).average();
	}
}
