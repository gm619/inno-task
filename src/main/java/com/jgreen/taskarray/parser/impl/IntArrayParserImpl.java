package com.jgreen.taskarray.parser.impl;

import com.jgreen.taskarray.exception.CustomArrayExecption;
import com.jgreen.taskarray.parser.ArrayParser;
import com.jgreen.taskarray.validation.ArrayValidation;
import com.jgreen.taskarray.validation.impl.IntArrayValidationImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntArrayParserImpl implements ArrayParser {

	private static final Pattern INT_PATTERN = Pattern.compile("-?\\d+");

	private final ArrayValidation validation;

	public IntArrayParserImpl() {
		this(new IntArrayValidationImpl());
	}

	public IntArrayParserImpl(ArrayValidation validation) {
		this.validation = validation;
	}

	@Override
	public int[] parseInt(String line) throws CustomArrayExecption {
		if (line == null || line.strip().isBlank()) {
			return null;
		}

		List<String> tokens = extractValidTokens(line);

		if (tokens.isEmpty()) {
			throw new CustomArrayExecption("No valid numbers found");
		}

		int[] result = new int[tokens.size()];
		for (int i = 0; i < tokens.size(); i++) {
			result[i] = Integer.parseInt(tokens.get(i));
		}
		return result;
	}

	private List<String> extractValidTokens(String line) {
		Matcher matcher = INT_PATTERN.matcher(line);
		List<String> tokens = new ArrayList<>();
		while (matcher.find()) {
			String token = matcher.group();
			if (validation.isValidNumber(token)) {
				tokens.add(token);
			}
		}
		return tokens;
	}

	@Override
	public double[] parseDouble(String line) throws CustomArrayExecption {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'parseDouble'");
	}
}

