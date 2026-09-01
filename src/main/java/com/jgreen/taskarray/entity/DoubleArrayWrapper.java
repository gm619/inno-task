package com.jgreen.taskarray.entity;

import java.util.Arrays;

/**
 * Immutable wrapper around a primitive {@code double} array.
 *
 * <p>Instances may be created either directly via the public constructors,
 * through the {@link com.jgreen.taskarray.factory.ArrayFactoryMethod factory}
 * pattern.</p>
 */
public class DoubleArrayWrapper {
  private final double[] array;

  public DoubleArrayWrapper() {
    array = new double[0];
  }

  public DoubleArrayWrapper(double[] array) {
    this.array = array == null ? new double[0] : array.clone();
  }

  public double[] getArray() {
    return array.clone();
  }

  public double length() {
    return array.length;
  }

  @Override
  public String toString() {
    return Arrays.toString(array);
  }
}
