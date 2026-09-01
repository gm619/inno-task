package com.jgreen.taskarray;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jgreen.taskarray.entity.ArrayEntity;
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
import com.jgreen.taskarray.repository.ArrayRepository;
import com.jgreen.taskarray.repository.spec.AverageSpecification;
import com.jgreen.taskarray.repository.spec.ByIdSpecification;
import com.jgreen.taskarray.repository.spec.ByNameSpecification;
import com.jgreen.taskarray.repository.spec.CountSpecification;
import com.jgreen.taskarray.repository.spec.SumSpecification;
import com.jgreen.taskarray.sorting.EntityComparators;
import com.jgreen.taskarray.sorting.SortArrayService;
import com.jgreen.taskarray.sorting.impl.BubbleSortArrayServiceImpl;
import com.jgreen.taskarray.sorting.impl.SelectionSortArrayServiceImpl;
import com.jgreen.taskarray.statistic.ArrayStatisticService;
import com.jgreen.taskarray.statistic.impl.ArrayStatisticServiceImpl;
import com.jgreen.taskarray.validation.ArrayValidation;
import com.jgreen.taskarray.validation.impl.DoubleArrayValidation;
import com.jgreen.taskarray.validation.impl.IntArrayValidationImpl;
import com.jgreen.taskarray.warehouse.Warehouse;

/**
 * Application entry point.
 *
 * <p>Reads data files line by line and, for every line, runs a full processing
 * pipeline: {@link ArrayReader reading} &rarr; {@link ArrayValidation validation}
 * &rarr; {@link ArrayParser parsing} &rarr; entity creation via
 * {@link ArrayFactoryMethod} &rarr; {@link SortArrayService sorting} &rarr;
 * {@link ArrayStatisticService statistics}.</p>
 *
 * <p>In addition, every created entity is stored in the
 * {@link ArrayRepository repository} (Singleton). The {@link Warehouse}
 * (Singleton) subscribes to the repository through the Observer pattern and
 * keeps derived statistics (sum / average / max / min / count) up to date,
 * including automatic recalculation when an element of a stored array is
 * changed. The repository supports searching by {@link ArraySpecification
 * specifications} and sorting via {@link Comparator}s from
 * {@link EntityComparators}.</p>
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
	private static final List<SortArrayService> sortServices = List.of(
			new BubbleSortArrayServiceImpl(),
			new SelectionSortArrayServiceImpl());

	// Statistics
	private static final ArrayStatisticService statisticsService = new ArrayStatisticServiceImpl();

	// Repository & Warehouse singletons wired through the Observer pattern
	private static final ArrayRepository repository = ArrayRepository.getInstance();
	private static final Warehouse warehouse = Warehouse.getInstance();

	// Sequential id generator for the entities
	private static long idCounter = 0;

	private Main() {
		// Utility entry-point class, prevent instantiation
	}

	public static void main(String[] args) {
		// Warehouse subscribes to the repository via its internal observer adapter.
		// Warehouse itself does NOT implement the Observer interface.
		repository.attach(warehouse.asObserver());

		processIntegerFile(INTEGER_DATA_FILE);
		processDoubleFile(DOUBLE_DATA_FILE);

		demonstrateRepository();
	}

	// ------------------------------------------------------------------
	// File processing (existing pipeline + repository population)
	// ------------------------------------------------------------------

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
		store(array, "int-" + (++idCounter));
		logger.info("  IntArrayWrapper created (length={}): {}", array.length(), array);

		sortAndLog(sortService -> java.util.Arrays.toString(sortService.sort(array.getArray())));
		logStatistics(statisticsService.intMin(array).orElseThrow(),
				statisticsService.intMax(array).orElseThrow(),
				statisticsService.intSum(array).getAsInt(),
				statisticsService.intAverage(array));
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
		store(array, "double-" + (++idCounter));
		logger.info("  DoubleArrayWrapper created (length={}): {}", array.length(), array);

		sortAndLog(sortService -> java.util.Arrays.toString(sortService.sort(array.getArray())));
		logStatistics(statisticsService.doubleMin(array).orElseThrow(),
				statisticsService.doubleMax(array).orElseThrow(),
				statisticsService.doubleSum(array).orElseThrow(),
				statisticsService.doubleAverage(array));
	}

	/**
	 * Assigns an identity to an entity and adds it to the repository.
	 *
	 * @param entity the created entity
	 * @param name   the name to assign
	 */
	private static void store(ArrayEntity entity, String name) {
		entity.setId(idCounter);
		entity.setName(name);
		repository.add(entity);
		logger.info("  Stored in repository (id={}, name='{}').", entity.getId(), entity.getName());
	}

	/**
	 * Sorts the given values with every registered sort strategy and logs results.
	 */
	private static void sortAndLog(Function<SortArrayService, String> sorter) {
		for (SortArrayService sortService : sortServices) {
			logger.info("  {} -> {}", sortService.getClass().getSimpleName(), sorter.apply(sortService));
		}
	}

	/**
	 * Logs computed statistics.
	 */
	private static void logStatistics(Number min, Number max, Number sum, OptionalDouble avg) {
		logger.info("  Statistics: min={}, max={}, sum={}, average={}",
				min, max, sum,
				avg.isPresent() ? String.format("%.4f", avg.getAsDouble()) : "N/A");
	}

	// ------------------------------------------------------------------
	// Demonstrations: repository search (specifications), sorting, observer
	// ------------------------------------------------------------------

	private static void demonstrateRepository() {
		logger.info("===== Repository demo ({} entities) =====", repository.size());

		demonstrateSorting();
		demonstrateSpecifications();
		demonstrateObserver();
	}

	private static void demonstrateSorting() {
		logger.info("--- Sorting (all entities) ---");

		printSorted("by id", EntityComparators.byId());
		printSorted("by name", EntityComparators.byName());
		printSorted("by first element", EntityComparators.byFirstElement());
		printSorted("by number of elements", EntityComparators.bySize());
		printSorted("by sum", EntityComparators.bySum());
	}

	private static void printSorted(String label, Comparator<ArrayEntity> comparator) {
		logger.info("  Sorted {}:", label);
		for (ArrayEntity entity : repository.findAllSorted(comparator)) {
			logger.info("    id={}, name='{}', size={}, first={}",
					entity.getId(), entity.getName(), entity.size(), entity.getFirst());
		}
	}

	private static void demonstrateSpecifications() {
		logger.info("--- Searching by specifications ---");

		logger.info("  Find by id == 2: {}", repository.findBySpecification(new ByIdSpecification(2)));
		logger.info("  Find by name == 'int-3': {}",
				repository.findBySpecification(new ByNameSpecification("int-3")));
		logger.info("  Find where sum > 30: {}",
				repository.findBySpecification(SumSpecification.greaterThan(30)));
		logger.info("  Find where average < 10: {}",
				repository.findBySpecification(AverageSpecification.lessThan(10)));
		logger.info("  Find where count == 3: {}",
				repository.findBySpecification(CountSpecification.equalTo(3)));
		logger.info("  Combined (sum > 5 AND count <= 4): {}",
				repository.findBySpecification(SumSpecification.greaterThan(5)
						.and(CountSpecification.lessThan(5))));
	}

	private static void demonstrateObserver() {
		logger.info("--- Observer pattern: warehouse keeps statistics in sync ---");

		for (ArrayEntity entity : repository.findAll()) {
			logger.info("  Warehouse[{}] = {}", entity.getId(), warehouse.getStatistics(entity.getId()));
		}

		// Change an element of a stored array and observe the warehouse update.
		repository.getById(1).ifPresent(entity -> {
			double oldSum = warehouse.getStatistics(entity.getId()).getSum();
			logger.info("  Before change: warehouse sum for id={} is {}", entity.getId(), oldSum);
			entity.setElement(0, 999);
			double newSum = warehouse.getStatistics(entity.getId()).getSum();
			logger.info("  After setElement(0, 999): warehouse sum for id={} became {}",
					entity.getId(), newSum);
			logger.info("  Updated statistics: {}", warehouse.getStatistics(entity.getId()));
		});
	}
}
