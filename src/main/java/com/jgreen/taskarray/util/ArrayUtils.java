package com.jgreen.taskarray.util;

// TODO: naming for class
public final class ArrayUtils {

  private ArrayUtils() {
  }

  public static void swap(int[] array, int firstIndex, int secondIndex) {
    int temp = array[firstIndex];
    array[firstIndex] = array[secondIndex];
    array[secondIndex] = temp;
  }

  public static void swap(double[] array, int firstIndex, int secondIndex) {
    double temp = array[firstIndex];
    array[firstIndex] = array[secondIndex];
    array[secondIndex] = temp;
  }

  public static int findMinimumIndex(int[] array, int startIndex) {
    int minIndex = startIndex;
    for (int index = startIndex + 1; index < array.length; index++) {
      if (array[index] < array[minIndex]) {
        minIndex = index;
      }
    }
    return minIndex;
  }

  public static int findMinimumIndex(double[] array, int startIndex) {
    int minIndex = startIndex;
    for (int index = startIndex + 1; index < array.length; index++) {
      if (array[index] < array[minIndex]) {
        minIndex = index;
      }
    }
    return minIndex;
  }
}