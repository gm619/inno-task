package com.jgreen.taskarray.service.impl;

import com.jgreen.taskarray.service.SortArrayService;
import java.util.Arrays;

public class SortArrayServiceImpl implements SortArrayService {
  @Override
  public int[] sort(int[] values) {
    int[] result = Arrays.copyOf(values, values.length);
    for (int outer = 0; outer < result.length - 1; outer++) {
      for (int inner = 0; inner < result.length - 1 - outer; inner++) {
        if (result[inner] > result[inner + 1]) {
          swap(result, inner, inner + 1);
        }
      }
    }
    return result;
  }
  
  private void swap(int[] array, int firstIndex, int secondIndex) {
    int temp = array[firstIndex];
    array[firstIndex] = array[secondIndex];
    array[secondIndex] = temp;
  }
}
