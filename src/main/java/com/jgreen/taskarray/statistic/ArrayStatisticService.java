package com.jgreen.taskarray.statistic;

import java.util.OptionalDouble;
import java.util.OptionalInt;

import com.jgreen.taskarray.entity.IntArrayWrapper;
import com.jgreen.taskarray.entity.DoubleArrayWrapper;

public interface ArrayStatisticService {
	OptionalInt intMin(IntArrayWrapper intArray);
	
	OptionalInt intMax(IntArrayWrapper intArray);
	
	OptionalInt intSum(IntArrayWrapper intArray);
	
	OptionalDouble intAverage(IntArrayWrapper intArray);

	OptionalDouble doubleMin(DoubleArrayWrapper intArray);
	
	OptionalDouble doubleMax(DoubleArrayWrapper intArray);

	OptionalDouble doubleSum(DoubleArrayWrapper intArray);

	OptionalDouble doubleAverage(DoubleArrayWrapper intArray);

}
