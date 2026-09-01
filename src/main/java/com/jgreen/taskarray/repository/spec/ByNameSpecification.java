package com.jgreen.taskarray.repository.spec;

import java.util.Objects;

import com.jgreen.taskarray.entity.ArrayEntity;
import com.jgreen.taskarray.repository.ArraySpecification;

/**
 * Specification that selects entities by their exact name.
 */
public class ByNameSpecification implements ArraySpecification {

	private final String name;

	public ByNameSpecification(String name) {
		this.name = name;
	}

	@Override
	public boolean isSatisfiedBy(ArrayEntity entity) {
		return Objects.equals(name, entity.getName());
	}

	@Override
	public String toString() {
		return "ByNameSpecification{name='" + name + "'}";
	}
}