package com.jgreen.taskarray;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jgreen.taskarray.entity.DoubleArrayWrapper;
import com.jgreen.taskarray.entity.IntArrayWrapper;
import com.jgreen.taskarray.exception.CustomArrayExecption;
import com.jgreen.taskarray.factory.ArrayFactoryMethod;
import com.jgreen.taskarray.factory.impl.DoubleArrayFactoryMethodImpl;
import com.jgreen.taskarray.factory.impl.IntArrayFactoryMethodImpl;
import com.jgreen.taskarray.parser.ArrayParser;
import com.jgreen.taskarray.parser.impl.DoubleArrayParseImpl;
import com.jgreen.taskarray.parser.impl.IntArrayParserImpl;
import com.jgreen.taskarray.reader.ArrayReader;
import com.jgreen.taskarray.reader.impl.ArrayReaderImpl;
import com.jgreen.taskarray.sorting.SortArrayService;
import com.jgreen.taskarray.sorting.impl.BubbleSortArrayServiceImpl;
import com.jgreen.taskarray.sorting.impl.SelectionSortArrayServiceImpl;
import com.jgreen.taskarray.statistic.ArrayStatisticService;
import com.jgreen.taskarray.statistic.impl.ArrayStatisticServiceImpl;
import com.jgreen.taskarray.validation.ArrayValidation;
import com.jgreen.taskarray.validation.impl.DoubleArrayValidation;
import com.jgreen.taskarray.validation.impl.IntArrayValidationImpl;

/**
 * Application entry point.
 *
 * <p>Reads data files line by line and, for every line, runs a full processing
 * pipeline: {@link ArrayReader reading} &rarr; {@link ArrayValidation validation}
 * &rarr; {@link ArrayParser parsing} &rarr; entity creation via {@link ArrayFactoryMethod}
 * &rarr; {@link SortArrayService sorting} &rarr; {@link ArrayStatisticService statistics}.</p>
 */
public final class Main {

	private static final Logger logger = LogManager.getLogger(Main.class);

	private static final String INTEGER_DATA_FILE = "src/main/data/int_arrays.txt";
	private static final String DOUBLE_DATA_FILE = "src/main/data/double_arrays.txt";

	// Reading
	private static final ArrayReader reader = new ArrayReaderImpl();

	// Validation
	private static final ArrayValidation intValidation = new IntArrayValidationImpl();
	private static final ArrayValidation doubleValidation = new DoubleArrayValidation();

	// Parsing (validators are injected into the parsers)
	private static final ArrayParser intParser = new IntArrayParserImpl(intValidation);
	private static final ArrayParser doubleParser = new DoubleArrayParseImpl(doubleValidation);

	// Entity factories (Factory Method pattern)
	private static final IntArrayFactoryMethodImpl intFactory = new IntArrayFactoryMethodImpl();
	private static final DoubleArrayFactoryMethodImpl doubleFactory = new DoubleArrayFactoryMethodImpl();

	// Sorting strategies
	private static final List<SortArrayService> sortServices = Arrays.asList(
			new BubbleSortArrayServiceImpl(),
			new SelectionSortArrayServiceImpl());

	// Statistics
	private static final ArrayStatisticService statisticsService = new ArrayStatisticServiceImpl();

	private Main() {
		// Utility entry-point class, prevent instantiation
	}

	public static void main(String[] args) {
		processIntegerFile(INTEGER_DATA_FILE);
		processDoubleFile(DOUBLE_DATA_FILE);
	}

	private static void processIntegerFile(String filePath) {
		List<String> lines;
		try {
			lines = reader.readLines(filePath);
		} catch (IOException | CustomArrayExecption e) {
			logger.error("Unable to read file '{}': {}", filePath, e.getMessage(), e);
			return;
		}
		logger.info("=== Processing integer file '{}' ({} lines) ===", filePath, lines.size());

		for (int i = 0; i < lines.size(); i++) {
			processIntegerLine(lines.get(i), i + 1);
		}
	}

	private static void processIntegerLine(String line, int lineNumber) {
		logger.info("Line #{}: \"{}\"", lineNumber, line);

		int[] parsed;
		try {
			parsed = intParser.parseInt(line);
		} catch (CustomArrayExecption e) {
			logger.info("  -> Skipped ({}).", e.getMessage());
			return;
		}
		if (parsed == null) {
			logger.info("  -> Empty string or only invalid data; nothing to process.");
			return;
		}

		// Entity creation via Factory Method
		IntArrayWrapper array = intFactory.create(parsed);
		logger.info("  IntArrayWrapper created (length={}): {}", array.length(), array);

		sortAndLog(array.getArray());
		logStatistics(statisticsService.intMin(array), statisticsService.intMax(array),
				statisticsService.intSum(array), statisticsService.intAverage(array));
	}

	private static void processDoubleFile(String filePath) {
		List<String> lines;
		try {
			lines = reader.readLines(filePath);
		} catch (IOException | CustomArrayExecption e) {
			logger.error("Unable to read file '{}': {}", filePath, e.getMessage(), e);
			return;
		}
		logger.info("=== Processing double file '{}' ({} lines) ===", filePath, lines.size());

		for (int i = 0; i < lines.size(); i++) {
			processDoubleLine(lines.get(i), i + 1);
		}
	}

	private static void processDoubleLine(String line, int lineNumber) {
		logger.info("Line #{}: \"{}\"", lineNumber, line);

		double[] parsed;
		try {
			parsed = doubleParser.parseDouble(line);
		} catch (CustomArrayExecption e) {
			logger.info("  -> Skipped ({}).", e.getMessage());
			return;
		}
		if (parsed == null) {
			logger.info("  -> Empty string or only invalid data; nothing to process.");
			return;
		}

		// Entity creation via Factory Method
		DoubleArrayWrapper array = doubleFactory.create(parsed);
		logger.info("  DoubleArrayWrapper created (length={}): {}", array.length(), array);

		sortAndLog(array.getArray());
		logDoubleStatistics(statisticsService.doubleMin(array), statisticsService.doubleMax(array),
				statisticsService.doubleSum(array), statisticsService.doubleAverage(array));
	}

	private static void sortAndLog(int[] values) {
		for (SortArrayService sortService : sortServices) {
			int[] sorted = sortService.sort(values);
			logger.info("  {} -> {}", sortService.getClass().getSimpleName(), Arrays.toString(sorted));
		}
	}

	private static void sortAndLog(double[] values) {
		for (SortArrayService sortService : sortServices) {
			double[] sorted = sortService.sort(values);
			logger.info("  {} -> {}", sortService.getClass().getSimpleName(), Arrays.toString(sorted));
		}
	}

	private static void logStatistics(OptionalInt min, OptionalInt max,
			OptionalInt sum, OptionalDouble avg) {
		logger.info("  Statistics: min={}, max={}, sum={}, average={}",
				min.orElseThrow(), max.orElseThrow(), sum.getAsInt(),
				avg.isPresent() ? String.format("%.4f", avg.getAsDouble()) : "N/A");
	}

	private static void logDoubleStatistics(OptionalDouble min, OptionalDouble max,
			OptionalDouble sum, OptionalDouble avg) {
		logger.info("  Statistics: min={}, max={}, sum={}, average={}",
				min.orElseThrow(), max.orElseThrow(), sum.orElseThrow(),
				avg.isPresent() ? String.format("%.4f", avg.getAsDouble()) : "N/A");
	}
}
