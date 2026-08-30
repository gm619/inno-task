package com.jgreen.taskarray.factory.impl;

import com.jgreen.taskarray.entity.IntArrayWrapper;
import com.jgreen.taskarray.factory.ArrayFactoryMethod;

public final class IntArrayFactoryMethodImpl extends ArrayFactoryMethod<IntArrayWrapper> {

	@Override
	public IntArrayWrapper createEmpty() {
		return new IntArrayWrapper();
	}

	@Override
	public IntArrayWrapper createCopy(IntArrayWrapper original) {
		if (original == null) {
			throw new NullPointerException("original must not be null");
		}
		return new IntArrayWrapper(original.getArray());
	}

	public IntArrayWrapper create(int[] array) {
		return new IntArrayWrapper(array);
	}
}
