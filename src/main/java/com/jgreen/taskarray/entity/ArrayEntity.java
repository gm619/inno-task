package com.jgreen.taskarray.entity;

/**
 * Common contract for every array entity that lives in the
 * {@link com.jgreen.taskarray.repository.ArrayRepository}.
 *
 * <p>Each entity declares an identity ({@code id} and/or {@code name}) as
 * required by the task and exposes read/write access to its elements. Any
 * element modification performed through {@link #setElement(int, Number)}
 * triggers the registered {@link EntityChangeListener} so the surrounding
 * application (e.g. the {@code Warehouse}) can recalculate dependent values.</p>
 */
public interface ArrayEntity {

	/**
	 * @return the unique identifier of this entity
	 */
	long getId();

	/**
	 * Assigns the unique identifier of this entity.
	 *
	 * @param id the new identifier
	 */
	void setId(long id);

	/**
	 * @return the human readable name of this entity
	 */
	String getName();

	/**
	 * Assigns the name of this entity.
	 *
	 * @param name the new name
	 */
	void setName(String name);

	/**
	 * @return the number of elements stored in this entity
	 */
	int size();

	/**
	 * @return the first element of the underlying array (0 when empty)
	 */
	Number getFirst();

	/**
	 * Reads the element at the given index.
	 *
	 * @param index zero based index
	 * @return the element value
	 */
	Number getElement(int index);

	/**
	 * Writes a new value at the given index. If a
	 * {@link EntityChangeListener} is attached it is notified immediately.
	 *
	 * @param index zero based index
	 * @param value new element value
	 */
	void setElement(int index, Number value);

	/**
	 * @return a defensive {@code double[]} view of the underlying data
	 */
	double[] asDoubleArray();

	/**
	 * Registers a listener that is invoked whenever any element changes.
	 * A {@code null} value detaches the listener.
	 *
	 * @param listener the change listener or {@code null}
	 */
	void setChangeListener(EntityChangeListener listener);
}