package com.jgreen.taskarray.repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.jgreen.taskarray.entity.ArrayEntity;
import com.jgreen.taskarray.entity.EntityChangeListener;

/**
 * Object-repository that stores every created {@link ArrayEntity}.
 *
 * <p><b>Singleton.</b> A plain (non thread-safe) eager instance is used as the
 * task forbids thread-safe variants. The repository also acts as the
 * <em>Subject</em> of the Observer pattern: it keeps a list of
 * {@link ArrayObserver observers} and notifies them on
 * add / update / remove.</p>
 *
 * <p>When an entity is added, the repository registers an
 * {@link EntityChangeListener} on it. As a result any element modification
 * performed later is automatically propagated to the observers (for example to
 * the {@code Warehouse}, which recalculates the statistics).</p>
 */
public final class ArrayRepository {

	private static final ArrayRepository INSTANCE = new ArrayRepository();

	private final Map<Long, ArrayEntity> entities = new LinkedHashMap<>();
	private final List<ArrayObserver> observers = new ArrayList<>();

	private ArrayRepository() {
	}

	public static ArrayRepository getInstance() {
		return INSTANCE;
	}

	// ------------------------------------------------------------------
	// Observer (Subject) management
	// ------------------------------------------------------------------

	public void attach(ArrayObserver observer) {
		if (observer != null && !observers.contains(observer)) {
			observers.add(observer);
		}
	}

	public void detach(ArrayObserver observer) {
		observers.remove(observer);
	}

	// ------------------------------------------------------------------
	// CRUD
	// ------------------------------------------------------------------

	/**
	 * Adds an entity to the repository. Duplicate or non-positive ids are
	 * rejected.
	 *
	 * @param entity the entity to add, must have a positive unique id
	 * @return the added entity (fluent API)
	 */
	public ArrayEntity add(ArrayEntity entity) {
		long id = entity.getId();
		if (id <= 0) {
			throw new IllegalArgumentException("Entity id must be positive, but was " + id);
		}
		if (entities.containsKey(id)) {
			throw new IllegalArgumentException("Duplicate entity id: " + id);
		}
		entity.setChangeListener(this::onEntityChanged);
		entities.put(id, entity);
		notifyOnAdd(entity);
		return entity;
	}

	/**
	 * Removes the entity with the given id.
	 *
	 * @param id the id of the entity to remove
	 * @return {@code true} if an entity was removed
	 */
	public boolean remove(long id) {
		ArrayEntity removed = entities.remove(id);
		if (removed == null) {
			return false;
		}
		removed.setChangeListener(null);
		notifyOnRemove(removed);
		return true;
	}

	/**
	 * @param id the searched id
	 * @return the entity with the given id, or empty
	 */
	public Optional<ArrayEntity> getById(long id) {
		return Optional.ofNullable(entities.get(id));
	}

	/**
	 * Searches entities by exact name match.
	 *
	 * @param name the searched name
	 * @return list of entities with the given name
	 */
	public List<ArrayEntity> findByName(String name) {
		return entities.values().stream()
				.filter(entity -> java.util.Objects.equals(name, entity.getName()))
				.toList();
	}

	/**
	 * @return all entities currently stored (insertion order)
	 */
	public List<ArrayEntity> findAll() {
		return new ArrayList<>(entities.values());
	}

	/**
	 * @return the number of stored entities
	 */
	public int size() {
		return entities.size();
	}

	/**
	 * Removes every stored entity. Useful to reset the singleton between tests.
	 */
	public void clear() {
		List<ArrayEntity> snapshot = findAll();
		entities.clear();
		for (ArrayEntity entity : snapshot) {
			entity.setChangeListener(null);
			notifyOnRemove(entity);
		}
	}

	/**
	 * Searches entities that satisfy the given specification.
	 *
	 * @param specification the search predicate
	 * @return list of matching entities
	 */
	public List<ArrayEntity> findBySpecification(ArraySpecification specification) {
		return entities.values().stream()
				.filter(specification::isSatisfiedBy)
				.toList();
	}

	/**
	 * Returns all entities sorted with the supplied comparator.
	 *
	 * @param comparator ordering rule
	 * @return a new sorted list (does not mutate the repository)
	 */
	public List<ArrayEntity> findAllSorted(Comparator<ArrayEntity> comparator) {
		List<ArrayEntity> sorted = findAll();
		sorted.sort(comparator);
		return sorted;
	}

	// ------------------------------------------------------------------
	// Change propagation
	// ------------------------------------------------------------------

	private void onEntityChanged(ArrayEntity entity) {
		notifyOnUpdate(entity);
	}

	private void notifyOnAdd(ArrayEntity entity) {
		for (ArrayObserver observer : observers) {
			observer.onAdd(entity);
		}
	}

	private void notifyOnUpdate(ArrayEntity entity) {
		for (ArrayObserver observer : observers) {
			observer.onUpdate(entity);
		}
	}

	private void notifyOnRemove(ArrayEntity entity) {
		for (ArrayObserver observer : observers) {
			observer.onRemove(entity);
		}
	}
}