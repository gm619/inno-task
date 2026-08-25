package com.jgreen.taskarray;

import java.io.IOException;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;

import com.jgreen.taskarray.entity.IntArrayWrapper;
import com.jgreen.taskarray.exception.CustomArrayExecption;
import com.jgreen.taskarray.parser.IntArrayParser;
import com.jgreen.taskarray.parser.impl.IntArrayParserImpl;
import com.jgreen.taskarray.reader.FileReader;
import com.jgreen.taskarray.reader.impl.FileReaderImpl;
import com.jgreen.taskarray.service.IntArrayService;
import com.jgreen.taskarray.service.impl.IntArrayServiceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static void main(String[] args) {
        final Logger logger = LogManager.getLogger(Main.class);
        final String INTEGER_DATA_FILE = "src/main/data/arrays.txt";

        FileReader fileReader = new FileReaderImpl();
        IntArrayParser parser = new IntArrayParserImpl();
        IntArrayService statisticsService = new IntArrayServiceImpl();

        try {
            List<String> lines = fileReader.readLines(INTEGER_DATA_FILE);
            logger.info("Lines '{}'", lines);
            int lineNumber = 0;
            for (String line : lines) {
                lineNumber++;
                logger.info("String processing #{}: \"{}\"", lineNumber, line);

                int[] parsed;
                try {
                    parsed = parser.parse(line);
                } catch (CustomArrayExecption e) {
                    logger.info("  -> Unable to parse string: {}", e.getMessage());
                    continue;
                }
                if (parsed == null) {
                    logger.info(" Failed to create array (string is empty or contains only invalid data)");
                    continue;
                }

                IntArrayWrapper array = new IntArrayWrapper(parsed);
                logger.info("Integer array '{}': {}", array.getArray(), array.toString());

                OptionalInt min = statisticsService.min(array);
                OptionalInt max = statisticsService.max(array);
                OptionalInt sum = statisticsService.sum(array);
                OptionalDouble avg = statisticsService.average(array);
                logger.info("Min={}, Max={}, Sum={}, Average={}", min, max, sum, avg);
            }
        } catch (IOException e) {
            logger.error("Error reading file: {}", e.getMessage());
        }
    }
}
