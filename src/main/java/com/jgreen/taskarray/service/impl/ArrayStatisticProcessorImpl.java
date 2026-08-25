package com.jgreen.taskarray.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;

import com.jgreen.taskarray.entity.IntArrayWrapper;
import com.jgreen.taskarray.exception.CustomArrayExecption;
import com.jgreen.taskarray.factory.ComponentFactory;
import com.jgreen.taskarray.parser.IntArrayParser;
import com.jgreen.taskarray.reader.FileReader;
import com.jgreen.taskarray.service.ArrayStatisticsProcessor;
import com.jgreen.taskarray.service.IntArrayService;
import com.jgreen.taskarray.service.SortArrayService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayStatisticProcessorImpl implements ArrayStatisticsProcessor {

	private static final Logger logger = LogManager.getLogger(ArrayStatisticProcessorImpl.class);

	private final FileReader fileReader;
	private final IntArrayParser parser;
	private final IntArrayService statisticsService;
	private final SortArrayService sortService;

	public ArrayStatisticProcessorImpl(ComponentFactory factory) {
		this.fileReader = factory.createFileReader();
		this.parser = factory.createIntArrayParser();
		this.statisticsService = factory.createIntArrayService();
		this.sortService = factory.createSortArrayService();
	}

	@Override
	public void process(String filePath) throws IOException {
		List<String> lines = fileReader.readLines(filePath);
		logger.info("Read '{}' lines from '{}'", lines.size(), filePath);

		for (int i = 0; i < lines.size(); i++) {
			processLine(lines.get(i), i + 1);
		}
	}

	private void processLine(String line, int lineNumber) {
		logger.info("String processing #{}: \"{}\"", lineNumber, line);

		int[] parsed;
		try {
			parsed = parser.parse(line);
		} catch (CustomArrayExecption e) {
			logger.info("  -> Unable to parse string: {}", e.getMessage());
			return;
		}
		if (parsed == null) {
			logger.info(" Failed to create array (string is empty or contains only invalid data)");
			return;
		}

		IntArrayWrapper array = new IntArrayWrapper(parsed);
		logger.info("Integer array '{}': {}", array.getArray(), array);

		int[] sorted = sortService.sort(array.getArray());
		logger.info("Sorted array '{}'", Arrays.toString(sorted));

		OptionalInt min = statisticsService.min(array);
		OptionalInt max = statisticsService.max(array);
		OptionalInt sum = statisticsService.sum(array);
		OptionalDouble avg = statisticsService.average(array);
		logger.info("Min={}, Max={}, Sum={}, Average={}", min, max, sum, avg);
	}
}
