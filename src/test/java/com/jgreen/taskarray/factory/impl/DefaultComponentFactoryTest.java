package com.jgreen.taskarray.factory.impl;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.jgreen.taskarray.service.parser.IntArrayParser;
import com.jgreen.taskarray.service.parser.impl.IntArrayParserImpl;
import com.jgreen.taskarray.service.reader.FileReader;
import com.jgreen.taskarray.service.reader.impl.FileReaderImpl;
import com.jgreen.taskarray.service.sorting.impl.BubbleSortArrayServiceImpl;
import com.jgreen.taskarray.service.sorting.impl.SelectionSortArrayServiceImpl;
import com.jgreen.taskarray.service.statistic.ArrayStatisticsProcessor;
import com.jgreen.taskarray.service.statistic.IntArrayService;
import com.jgreen.taskarray.service.statistic.impl.ArrayStatisticProcessorImpl;
import com.jgreen.taskarray.service.statistic.impl.IntArrayServiceImpl;

class DefaultComponentFactoryTest {

    private final DefaultComponentFactory factory = new DefaultComponentFactory();

    @Test
    void createFileReaderShouldReturnFileReaderImpl() {
        // given
        DefaultComponentFactory factoryInstance = factory;

        // when
        FileReader fileReader = factoryInstance.createFileReader();

        // then
        assertNotNull(fileReader);
        assertInstanceOf(FileReaderImpl.class, fileReader);
    }

    @Test
    void createIntArrayParserShouldReturnIntArrayParserImpl() {
        // given
        DefaultComponentFactory factoryInstance = factory;

        // when
        IntArrayParser parser = factoryInstance.createIntArrayParser();

        // then
        assertNotNull(parser);
        assertInstanceOf(IntArrayParserImpl.class, parser);
    }

    @Test
    void createIntArrayServiceShouldReturnIntArrayServiceImpl() {
        // given
        DefaultComponentFactory factoryInstance = factory;

        // when
        IntArrayService service = factoryInstance.createIntArrayService();

        // then
        assertNotNull(service);
        assertInstanceOf(IntArrayServiceImpl.class, service);
    }

    @Test
    void createBubbleSortArrayServiceShouldReturnInstance() {
        // given
        DefaultComponentFactory factoryInstance = factory;

        // when
        BubbleSortArrayServiceImpl service = factoryInstance.createBubbleSortArrayService();

        // then
        assertNotNull(service);
    }

    @Test
    void createSelectionSortArrayServiceShouldReturnInstance() {
        // given
        DefaultComponentFactory factoryInstance = factory;

        // when
        SelectionSortArrayServiceImpl service = factoryInstance.createSelectionSortArrayService();

        // then
        assertNotNull(service);
    }

    @Test
    void createArrayStatisticsProcessorShouldReturnArrayStatisticProcessorImpl() {
        // given
        DefaultComponentFactory factoryInstance = factory;

        // when
        ArrayStatisticsProcessor processor = factoryInstance.createArrayStatisticsProcessor();

        // then
        assertNotNull(processor);
        assertInstanceOf(ArrayStatisticProcessorImpl.class, processor);
    }
}