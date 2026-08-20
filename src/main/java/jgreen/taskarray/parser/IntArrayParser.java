package jgreen.taskarray.parser;

import jgreen.taskarray.exception.CustomArrayExecption;

public interface IntArrayParser {
	int[] parse(String line) throws CustomArrayExecption;
}
