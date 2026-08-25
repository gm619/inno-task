package com.jgreen.taskarray.service.sorting.impl;

import com.jgreen.taskarray.service.sorting.SortArrayService;
import com.jgreen.taskarray.util.ArrayUtils;
import java.util.Arrays;

public class BubbleSortArrayServiceImpl implements SortArrayService {
  @Override
  public int[] sort(int[] values) {
    int[] result = Arrays.copyOf(values, values.length);
    for (int outer = 0; outer < result.length - 1; outer++) {
      for (int inner = 0; inner < result.length - 1 - outer; inner++) {
        if (result[inner] > result[inner + 1]) {
          ArrayUtils.swap(result, inner, inner + 1);
        }
      }
    }
    return result;
  }
}
