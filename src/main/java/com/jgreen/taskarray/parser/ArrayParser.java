package com.jgreen.taskarray.parser;

import com.jgreen.taskarray.exception.CustomArrayExecption;

public interface ArrayParser {
	abstract int[] parseInt(String line) throws CustomArrayExecption;

	abstract double[] parseDouble(String line) throws CustomArrayExecption;
}
