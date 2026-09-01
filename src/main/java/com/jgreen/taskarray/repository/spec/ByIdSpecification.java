package com.jgreen.taskarray.repository.spec;

import com.jgreen.taskarray.entity.ArrayEntity;
import com.jgreen.taskarray.repository.ArraySpecification;

/**
 * Specification that selects entities by their exact id.
 */
public class ByIdSpecification implements ArraySpecification {

	private final long id;

	public ByIdSpecification(long id) {
		this.id = id;
	}

	@Override
	public boolean isSatisfiedBy(ArrayEntity entity) {
		return entity.getId() == id;
	}

	@Override
	public String toString() {
		return "ByIdSpecification{id=" + id + '}';
	}
}