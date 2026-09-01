package com.jgreen.taskarray.sorting;

import java.util.Comparator;

import com.jgreen.taskarray.entity.ArrayEntity;
import com.jgreen.taskarray.util.ArrayMath;

/**
 * Factory of {@link Comparator}s used to sort sets of {@link ArrayEntity}
 * objects by different criteria: id, name, first element, number of elements,
 * sum, average, min or max.
 */
public final class EntityComparators {

	private EntityComparators() {
	}

	public static Comparator<ArrayEntity> byId() {
		return Comparator.comparingLong(ArrayEntity::getId);
	}

	public static Comparator<ArrayEntity> byIdDescending() {
		return byId().reversed();
	}

	public static Comparator<ArrayEntity> byName() {
		return Comparator.comparing(ArrayEntity::getName, Comparator.nullsLast(String::compareTo));
	}

	public static Comparator<ArrayEntity> byFirstElement() {
		return Comparator.comparingDouble(entity -> entity.getFirst().doubleValue());
	}

	public static Comparator<ArrayEntity> bySize() {
		return Comparator.comparingInt(ArrayEntity::size);
	}

	public static Comparator<ArrayEntity> bySum() {
		return Comparator.comparingDouble(ArrayMath::sum);
	}

	public static Comparator<ArrayEntity> byAverage() {
		return Comparator.comparingDouble(ArrayMath::average);
	}

	public static Comparator<ArrayEntity> byMin() {
		return Comparator.comparingDouble(ArrayMath::min);
	}

	public static Comparator<ArrayEntity> byMax() {
		return Comparator.comparingDouble(ArrayMath::max);
	}
}