package com.jgreen.taskarray.service.statistic;

import java.util.OptionalDouble;
import java.util.OptionalInt;

import com.jgreen.taskarray.entity.IntArrayWrapper;

public interface IntArrayService {
	OptionalInt min(IntArrayWrapper intArray);
	
	OptionalInt max(IntArrayWrapper intArray);
	
	OptionalInt sum(IntArrayWrapper intArray);
	
	OptionalDouble average(IntArrayWrapper intArray);
}
