package com.jgreen.taskarray.service.sorting.impl;

import com.jgreen.taskarray.service.sorting.SortArrayService;
import com.jgreen.taskarray.util.ArrayUtils;
import java.util.Arrays;

public class SelectionSortArrayServiceImpl implements SortArrayService {
  public int[] sort(int[] values) {
    int[] result = Arrays.copyOf(values, values.length);
    for (int current = 0; current < result.length - 1; current++) {
      int minIndex = ArrayUtils.findMinimumIndex(result, current);
      ArrayUtils.swap(result, current, minIndex);
    }
    return result;
  }
}
