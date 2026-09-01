package com.jgreen.taskarray.repository;

import com.jgreen.taskarray.entity.ArrayEntity;

/**
 * The <em>Observer</em> role of the Observer pattern used by
 * {@link ArrayRepository} (the <em>Subject</em>).
 *
 * <p>Implementations are notified when an entity is added, updated (an element
 * changed) or removed from the repository.</p>
 */
public interface ArrayObserver {

	/**
	 * Invoked after an entity was added to the repository.
	 *
	 * @param entity the newly added entity
	 */
	void onAdd(ArrayEntity entity);

	/**
	 * Invoked after an element of an entity in the repository changed.
	 *
	 * @param entity the entity whose content changed
	 */
	void onUpdate(ArrayEntity entity);

	/**
	 * Invoked after an entity was removed from the repository.
	 *
	 * @param entity the removed entity
	 */
	void onRemove(ArrayEntity entity);
}