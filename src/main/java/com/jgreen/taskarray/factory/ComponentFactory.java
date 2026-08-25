package com.jgreen.taskarray.factory;

import com.jgreen.taskarray.parser.IntArrayParser;
import com.jgreen.taskarray.reader.FileReader;
import com.jgreen.taskarray.service.ArrayStatisticsProcessor;
import com.jgreen.taskarray.service.IntArrayService;
import com.jgreen.taskarray.service.SortArrayService;

public abstract class ComponentFactory {

    public abstract FileReader createFileReader();

    public abstract IntArrayParser createIntArrayParser();

    public abstract IntArrayService createIntArrayService();

    public abstract SortArrayService createSortArrayService();

    public abstract ArrayStatisticsProcessor createArrayStatisticsProcessor();
}