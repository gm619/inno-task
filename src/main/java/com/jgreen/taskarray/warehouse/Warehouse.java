package com.jgreen.taskarray.warehouse;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.jgreen.taskarray.entity.ArrayEntity;
import com.jgreen.taskarray.entity.ArrayStatistics;
import com.jgreen.taskarray.repository.ArrayObserver;
import com.jgreen.taskarray.repository.ArrayRepository;
import com.jgreen.taskarray.util.ArrayMath;

/**
 * <b>Singleton</b> store that keeps derived statistics (count, sum, average,
 * min, max) for every {@link ArrayEntity} registered in the
 * {@link ArrayRepository}.
 *
 * <p>The statistics are kept in sync with the repository through the Observer
 * pattern: the {@code Warehouse} subscribes to the repository and recalculates
 * the metrics of an entity whenever it is added or updated.</p>
 *
 * <p><b>Important:</b> the {@code Warehouse} itself does <em>not</em>
 * implement the {@link ArrayObserver} interface. Instead it exposes an internal
 * observer <em>adapter</em> (see {@link #asObserver()}) that forwards
 * notifications to the private recalculation logic. This satisfies the task
 * constraint <em>"класс-Warehouse не может имплементировать Observer"</em>.</p>
 */
public final class Warehouse {

	private static final Warehouse INSTANCE = new Warehouse();

	private final Map<Long, ArrayStatistics> statistics = new LinkedHashMap<>();

	/**
	 * Internal observer adapter. The Warehouse does NOT implement
	 * {@link ArrayObserver}; this adapter merely delegates the callbacks to the
	 * private recompute/remove methods.
	 */
	private final ArrayObserver observerAdapter = new ArrayObserver() {
		@Override
		public void onAdd(ArrayEntity entity) {
			recompute(entity);
		}

		@Override
		public void onUpdate(ArrayEntity entity) {
			recompute(entity);
		}

		@Override
		public void onRemove(ArrayEntity entity) {
			statistics.remove(entity.getId());
		}
	};

	private Warehouse() {
	}

	public static Warehouse getInstance() {
		return INSTANCE;
	}

	/**
	 * @return the observer adapter used to subscribe the warehouse to a
	 *         repository (the warehouse itself is not an Observer)
	 */
	public ArrayObserver asObserver() {
		return observerAdapter;
	}

	/**
	 * Recalculates and caches the statistics for the given entity.
	 *
	 * @param entity the entity whose metrics must be refreshed
	 */
	public void recompute(ArrayEntity entity) {
		double sum = ArrayMath.sum(entity);
		double average = ArrayMath.average(entity);
		double min = ArrayMath.min(entity);
		double max = ArrayMath.max(entity);
		statistics.put(entity.getId(),
				new ArrayStatistics(entity.getId(), entity.getName(),
						entity.size(), sum, average, min, max));
	}

	/**
	 * Recalculates statistics for every entity in the supplied collection.
	 *
	 * @param entities entities to refresh
	 */
	public void recomputeAll(Collection<? extends ArrayEntity> entities) {
		for (ArrayEntity entity : entities) {
			recompute(entity);
		}
	}

	/**
	 * @param id entity id
	 * @return cached statistics for the entity, or {@code null} if absent
	 */
	public ArrayStatistics getStatistics(long id) {
		return statistics.get(id);
	}

	/**
	 * @param entity the entity
	 * @return cached statistics for the entity, or {@code null} if absent
	 */
	public ArrayStatistics getStatistics(ArrayEntity entity) {
		return statistics.get(entity.getId());
	}

	/**
	 * @return all currently cached statistics
	 */
	public Collection<ArrayStatistics> getAll() {
		return statistics.values();
	}

	/**
	 * @return the number of cached statistics records
	 */
	public int size() {
		return statistics.size();
	}

	/**
	 * Removes all cached statistics.
	 */
	public void clear() {
		statistics.clear();
	}
}