package com.jgreen.taskarray.entity;

/**
 * Functional listener used by {@link ArrayEntity} to report element changes.
 *
 * <p>An entity stores exactly one listener (typically supplied by the
 * repository it belongs to). Whenever {@link ArrayEntity#setElement(int, Number)}
 * is called the entity invokes this listener so that observers (like the
 * {@code Warehouse}) can recalculate derived statistics.</p>
 */
@FunctionalInterface
public interface EntityChangeListener {

	/**
	 * Invoked after an element of the given entity has changed.
	 *
	 * @param entity the entity whose element changed
	 */
	void onEntityChanged(ArrayEntity entity);
}