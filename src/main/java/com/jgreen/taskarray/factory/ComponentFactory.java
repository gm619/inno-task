package com.jgreen.taskarray.factory;

import com.jgreen.taskarray.service.parser.IntArrayParser;
import com.jgreen.taskarray.service.reader.FileReader;
import com.jgreen.taskarray.service.sorting.impl.BubbleSortArrayServiceImpl;
import com.jgreen.taskarray.service.sorting.impl.SelectionSortArrayServiceImpl;
import com.jgreen.taskarray.service.statistic.ArrayStatisticsProcessor;
import com.jgreen.taskarray.service.statistic.IntArrayService;

public abstract class ComponentFactory {

    public abstract FileReader createFileReader();

    public abstract IntArrayParser createIntArrayParser();

    public abstract IntArrayService createIntArrayService();

    public abstract BubbleSortArrayServiceImpl createBubbleSortArrayService();
    
    public abstract SelectionSortArrayServiceImpl createSelectionSortArrayService();

    public abstract ArrayStatisticsProcessor createArrayStatisticsProcessor();
}