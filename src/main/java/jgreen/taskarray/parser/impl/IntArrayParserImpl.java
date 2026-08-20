package jgreen.taskarray.parser.impl;

import jgreen.taskarray.parser.IntArrayParser;
import jgreen.taskarray.exception.CustomArrayExecption;

public class IntArrayParserImpl implements IntArrayParser {
	public static final String REGEX = "[,;\\s\\-_/\\\\|]+";
	
	@Override
	public int[] parse(String line) throws CustomArrayExecption {
		if (line.strip().isBlank()) {
			return null;
		}
		
        String trimmed = line.strip();
        if (trimmed.isEmpty()) {
            return null;
        }

        String[] tokens = trimmed.split(REGEX);
        if (tokens.length == 0) {
            throw new CustomArrayExecption("No valid numbers found");
        }

        int[] result = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            try {
                result[i] = Integer.parseInt(tokens[i]);
            } catch (NumberFormatException e) {
                throw new CustomArrayExecption("Invalid number: " + tokens[i]);
            }
        }
        return result;
	}
}
