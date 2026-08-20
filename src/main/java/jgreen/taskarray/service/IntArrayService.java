package jgreen.taskarray.service;

import java.util.OptionalDouble;
import java.util.OptionalInt;

import jgreen.taskarray.entity.IntArray;

public interface IntArrayService {
	OptionalInt min(IntArray intArray);
	
	OptionalInt max(IntArray intArray);
	
	OptionalInt sum(IntArray intArray);
	
	OptionalDouble average(IntArray intArray);
}
