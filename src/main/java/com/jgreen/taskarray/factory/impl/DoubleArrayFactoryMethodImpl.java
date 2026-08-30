package com.jgreen.taskarray.factory.impl;

import com.jgreen.taskarray.entity.DoubleArrayWrapper;
import com.jgreen.taskarray.factory.ArrayFactoryMethod;

public class DoubleArrayFactoryMethodImpl extends ArrayFactoryMethod<DoubleArrayWrapper> {

  @Override
  public DoubleArrayWrapper createEmpty() {
    return new DoubleArrayWrapper();
  }

  @Override
  public DoubleArrayWrapper createCopy(DoubleArrayWrapper original) {
    if (original == null) {
      throw new NullPointerException("original must not be null");
    }
    return new DoubleArrayWrapper(original.getArray());
  }

  public DoubleArrayWrapper create(double[] array) {
    return new DoubleArrayWrapper(array);
  }
}
