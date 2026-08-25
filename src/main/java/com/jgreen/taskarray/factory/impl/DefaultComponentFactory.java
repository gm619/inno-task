package com.jgreen.taskarray.factory.impl;

import com.jgreen.taskarray.factory.ComponentFactory;
import com.jgreen.taskarray.service.parser.IntArrayParser;
import com.jgreen.taskarray.service.parser.impl.IntArrayParserImpl;
import com.jgreen.taskarray.service.reader.FileReader;
import com.jgreen.taskarray.service.reader.impl.FileReaderImpl;
import com.jgreen.taskarray.service.sorting.impl.BubbleSortArrayServiceImpl;
import com.jgreen.taskarray.service.sorting.impl.SelectionSortArrayServiceImpl;
import com.jgreen.taskarray.service.statistic.ArrayStatisticsProcessor;
import com.jgreen.taskarray.service.statistic.impl.ArrayStatisticProcessorImpl;
import com.jgreen.taskarray.service.statistic.IntArrayService;
import com.jgreen.taskarray.service.statistic.impl.IntArrayServiceImpl;

public class DefaultComponentFactory extends ComponentFactory {

    @Override
    public FileReader createFileReader() {
        return new FileReaderImpl();
    }

    @Override
    public IntArrayParser createIntArrayParser() {
        return new IntArrayParserImpl();
    }

    @Override
    public IntArrayService createIntArrayService() {
        return new IntArrayServiceImpl();
    }

    @Override
    public BubbleSortArrayServiceImpl createBubbleSortArrayService() {
        return new BubbleSortArrayServiceImpl();
    }

    @Override
    public SelectionSortArrayServiceImpl createSelectionSortArrayService() {
        return new SelectionSortArrayServiceImpl();
    }

    @Override
    public ArrayStatisticsProcessor createArrayStatisticsProcessor() {
        return new ArrayStatisticProcessorImpl(this);
    }
}