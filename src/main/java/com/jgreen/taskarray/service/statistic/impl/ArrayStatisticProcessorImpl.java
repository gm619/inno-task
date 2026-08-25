package com.jgreen.taskarray.service.statistic.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;

import com.jgreen.taskarray.entity.IntArrayWrapper;
import com.jgreen.taskarray.exception.CustomArrayExecption;
import com.jgreen.taskarray.factory.ComponentFactory;
import com.jgreen.taskarray.service.parser.IntArrayParser;
import com.jgreen.taskarray.service.reader.FileReader;
import com.jgreen.taskarray.service.sorting.impl.BubbleSortArrayServiceImpl;
import com.jgreen.taskarray.service.sorting.impl.SelectionSortArrayServiceImpl;
import com.jgreen.taskarray.service.statistic.ArrayStatisticsProcessor;
import com.jgreen.taskarray.service.statistic.IntArrayService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayStatisticProcessorImpl implements ArrayStatisticsProcessor {

	private static final Logger logger = LogManager.getLogger(ArrayStatisticProcessorImpl.class);

	private final FileReader fileReader;
	private final IntArrayParser parser;
	private final IntArrayService statisticsService;
	private final BubbleSortArrayServiceImpl bubbleSortService;
	private final SelectionSortArrayServiceImpl selectionSortService;

	public ArrayStatisticProcessorImpl(ComponentFactory factory) {
		this.fileReader = factory.createFileReader();
		this.parser = factory.createIntArrayParser();
		this.statisticsService = factory.createIntArrayService();
		this.bubbleSortService = factory.createBubbleSortArrayService();
		this.selectionSortService = factory.createSelectionSortArrayService();
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

		int[] bubbleSorted = bubbleSortService.sort(array.getArray());
		logger.info("Sorted array by bubble algoritm'{}'", Arrays.toString(bubbleSorted));		
		int[] selectionSorted = selectionSortService.sort(array.getArray());
		logger.info("Sorted array by selection algoritm'{}'", Arrays.toString(selectionSorted));	

		OptionalInt min = statisticsService.min(array);
		OptionalInt max = statisticsService.max(array);
		OptionalInt sum = statisticsService.sum(array);
		OptionalDouble avg = statisticsService.average(array);
		logger.info("Min={}, Max={}, Sum={}, Average={}", min, max, sum, avg);
	}
}
