package com.jgreen.taskarray.parser.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jgreen.taskarray.exception.CustomArrayExecption;
import com.jgreen.taskarray.parser.ArrayParser;
import com.jgreen.taskarray.validation.ArrayValidation;
import com.jgreen.taskarray.validation.impl.DoubleArrayValidation;

public class DoubleArrayParseImpl implements ArrayParser {

  private static final Pattern DOUBLE_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

  private final ArrayValidation validation;

  public DoubleArrayParseImpl() {
    this(new DoubleArrayValidation());
  }

  public DoubleArrayParseImpl(ArrayValidation validation) {
    this.validation = validation;
  }

  @Override
  public double[] parseDouble(String line) throws CustomArrayExecption {
    if (line == null || line.strip().isBlank()) {
      return null;
    }

    List<String> tokens = extractValidTokens(line);

    if (tokens.isEmpty()) {
      throw new CustomArrayExecption("No valid numbers found");
    }

    double[] result = new double[tokens.size()];
    for (int i = 0; i < tokens.size(); i++) {
      result[i] = Double.parseDouble(tokens.get(i));
    }
    return result;
  }

  private List<String> extractValidTokens(String line) {
    Matcher matcher = DOUBLE_PATTERN.matcher(line);
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
  public int[] parseInt(String line) throws CustomArrayExecption {
    throw new UnsupportedOperationException("Unimplemented method 'parseInt'");
  }
}
