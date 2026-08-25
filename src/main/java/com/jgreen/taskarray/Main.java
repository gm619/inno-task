package com.jgreen.taskarray;

import com.jgreen.taskarray.factory.ComponentFactory;
import com.jgreen.taskarray.factory.impl.DefaultComponentFactory;
import com.jgreen.taskarray.service.statistic.ArrayStatisticsProcessor;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {

	private static final Logger logger = LogManager.getLogger(Main.class);
	private static final String INTEGER_DATA_FILE = "src/main/data/arrays.txt";

	private Main() {
		// Utility entry-point class, prevent instantiation
	}

	public static void main(String[] args) {
		ComponentFactory factory = new DefaultComponentFactory();
		ArrayStatisticsProcessor processor = factory.createArrayStatisticsProcessor();

		try {
			processor.process(INTEGER_DATA_FILE);
		} catch (IOException e) {
			logger.error("Error reading file '{}': {}", INTEGER_DATA_FILE, e.getMessage());
		}
	}
}
