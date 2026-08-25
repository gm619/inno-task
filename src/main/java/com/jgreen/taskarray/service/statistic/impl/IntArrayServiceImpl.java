package com.jgreen.taskarray.service.statistic.impl;

import com.jgreen.taskarray.entity.IntArrayWrapper;
import com.jgreen.taskarray.service.statistic.IntArrayService;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.OptionalDouble;

public class IntArrayServiceImpl implements IntArrayService {
	@Override
	public OptionalInt min(IntArrayWrapper intArray) {
		// TODO: Should be in reader or validation
		if (intArray.length() < 1) {
			return OptionalInt.empty();
		}
		
		return Arrays.stream(intArray.getArray()).min();
	}

	@Override
	public OptionalInt max(IntArrayWrapper intArray) {
		return Arrays.stream(intArray.getArray()).max();
	}
	
	@Override
	public OptionalInt sum(IntArrayWrapper intArray) {
		return OptionalInt.of(Arrays.stream(intArray.getArray()).sum());
	}
	
	@Override
	public OptionalDouble average(IntArrayWrapper intArray) {
		return Arrays.stream(intArray.getArray()).average();
	}
}
