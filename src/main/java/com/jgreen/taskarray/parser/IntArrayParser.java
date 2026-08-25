package com.jgreen.taskarray.parser;

import com.jgreen.taskarray.exception.CustomArrayExecption;
import java.util.List;

public interface IntArrayParser {
	int[] parse(String line) throws CustomArrayExecption;
	
	List<String> parseTokens(String line);
}
