package com.jgreen.taskarray.repository;

import com.jgreen.taskarray.entity.ArrayEntity;

/**
 * Specification (predicate) used to search objects / groups of objects inside
 * the {@link ArrayRepository}.
 *
 * <p>Specifications can be combined using boolean logic
 * ({@link #and(ArraySpecification) and}, {@link #or(ArraySpecification) or},
 * {@link #not() not}) allowing expressive queries.</p>
 */
@FunctionalInterface
public interface ArraySpecification {

	/**
	 * @param entity candidate entity
	 * @return {@code true} if the entity satisfies this specification
	 */
	boolean isSatisfiedBy(ArrayEntity entity);

	default ArraySpecification and(ArraySpecification other) {
		return entity -> isSatisfiedBy(entity) && other.isSatisfiedBy(entity);
	}

	default ArraySpecification or(ArraySpecification other) {
		return entity -> isSatisfiedBy(entity) || other.isSatisfiedBy(entity);
	}

	default ArraySpecification not() {
		return entity -> !isSatisfiedBy(entity);
	}
}