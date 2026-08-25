package com.jgreen.taskarray.factory.impl;

import com.jgreen.taskarray.factory.ComponentFactory;
import com.jgreen.taskarray.parser.IntArrayParser;
import com.jgreen.taskarray.parser.impl.IntArrayParserImpl;
import com.jgreen.taskarray.reader.FileReader;
import com.jgreen.taskarray.reader.impl.FileReaderImpl;
import com.jgreen.taskarray.service.ArrayStatisticsProcessor;
import com.jgreen.taskarray.service.IntArrayService;
import com.jgreen.taskarray.service.SortArrayService;
import com.jgreen.taskarray.service.impl.ArrayStatisticProcessorImpl;
import com.jgreen.taskarray.service.impl.IntArrayServiceImpl;
import com.jgreen.taskarray.service.impl.SortArrayServiceImpl;

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
    public SortArrayService createSortArrayService() {
        return new SortArrayServiceImpl();
    }

    @Override
    public ArrayStatisticsProcessor createArrayStatisticsProcessor() {
        return new ArrayStatisticProcessorImpl(this);
    }
}