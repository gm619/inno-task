package com.jgreen.taskarray.entity;

import java.util.Arrays;

/**
 * Immutable wrapper around a primitive {@code double} array.
 *
 * <p>Instances may be created either directly via the public constructors,
 * through the {@link com.jgreen.taskarray.factory.ArrayFactoryMethod factory}
 * pattern, or using the {@link Builder} (Builder pattern).</p>
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

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private double[] array = new double[0];

    private Builder() {
      // use DoubleArrayWrapper.builder()
    }

    /**
     * Sets the backing values (stored defensively).
     *
     * @param values source array, may be {@code null}
     * @return this builder
     */
    public Builder withArray(double[] values) {
      this.array = values == null ? new double[0] : values.clone();
      return this;
    }

    /**
     * Appends a single value to the array being built.
     *
     * @param value the value to append
     * @return this builder
     */
    public Builder append(double value) {
      double[] extended = Arrays.copyOf(array, array.length + 1);
      extended[array.length] = value;
      this.array = extended;
      return this;
    }

    /**
     * Builds the immutable wrapper.
     *
     * @return a new {@link DoubleArrayWrapper}
     */
    public DoubleArrayWrapper build() {
      return new DoubleArrayWrapper(array);
    }
  }
}
