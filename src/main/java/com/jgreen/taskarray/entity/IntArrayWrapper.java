package com.jgreen.taskarray.entity;

import java.util.Arrays;

/**
 * Immutable wrapper around a primitive {@code int} array.
 *
 * <p>Instances may be created either directly via the public constructors,
 * through the {@link ArrayFactoryMethod factory} pattern, or using the
 * {@link Builder} (Builder pattern).</p>
 */
public class IntArrayWrapper {
	private final int[] array;

	public IntArrayWrapper() {
		array = new int[0];
	}

	public IntArrayWrapper(int[] array) {
		this.array = array == null ? new int[0] : array.clone();
	}

	public int[] getArray() {
		return array.clone();
	}

	public int length() {
		return array.length;
	}

	@Override
	public String toString() {
		return Arrays.toString(array);
	}

	/**
	 * Builder pattern entry point.
	 *
	 * @return a new empty builder
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder for {@link IntArrayWrapper}.
	 */
	public static final class Builder {
		private int[] array = new int[0];

		private Builder() {
			// use IntArrayWrapper.builder()
		}

		/**
		 * Sets the backing values (stored defensively).
		 *
		 * @param values source array, may be {@code null}
		 * @return this builder
		 */
		public Builder withArray(int[] values) {
			this.array = values == null ? new int[0] : values.clone();
			return this;
		}

		/**
		 * Appends a single value to the array being built.
		 *
		 * @param value the value to append
		 * @return this builder
		 */
		public Builder append(int value) {
			int[] extended = Arrays.copyOf(array, array.length + 1);
			extended[array.length] = value;
			this.array = extended;
			return this;
		}

		/**
		 * Builds the immutable wrapper.
		 *
		 * @return a new {@link IntArrayWrapper}
		 */
		public IntArrayWrapper build() {
			return new IntArrayWrapper(array);
		}
	}
}
